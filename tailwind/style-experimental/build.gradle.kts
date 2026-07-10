import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

// A composite build (see ../../settings.gradle.kts's includeBuild), not a regular subproject
// -- own settings.gradle.kts, own plugin classpath. That's what makes it possible to apply
// org.jetbrains.compose at 1.12.0-beta01 here while every other module in the outer build
// stays on 1.11.1: within a single Gradle build, a plugin ID resolves to one version for
// every project once anything applies it via `apply false` at the root (which the outer
// build's org.jetbrains.compose alias already does, at 1.11.1) -- a second, differently
// versioned request for that same ID from a subproject fails outright.
//
// This module used to hold the *entire* Style-API port. Verified directly (not just asserted)
// that its Android artifacts need compileSdk 37+ (by actually adding `androidLibrary {
// compileSdk = 36 }` here and running `:style-experimental:assemble` -- `checkAndroidMainAarMetadata`
// hard-failed because every androidx.compose.*:*-android:1.12.0-beta01 artifact declares AAR
// metadata `minCompileSdk = 37`, and Android SDK Platform 37 doesn't exist in Google's SDK
// repository at all yet, so there was nothing a compileSdk bump could even point at). Rather
// than block the whole Style-API port on that upstream release-sequencing gap, it's split:
// everything that already compiles against stable Compose Multiplatform 1.11.1 (Ring, Border,
// Shadow, Color, Opacity, Sizing, Transform, Transition, Typography, ZIndex) moved to the
// regular :tailwind-style subproject, which DOES have an Android target (1.11.1's Android
// artifacts don't carry that minCompileSdk requirement). Only FilterStyle.kt (`colorFilter`)
// and SpacingStyle.kt (`contentPadding`/`externalPadding`) remain here, since those two
// StyleScope properties are genuinely new in 1.12.0-beta01 and don't exist in 1.11.1 -- this
// module now depends on :tailwind-style's published artifact for everything else, the same
// way it already depends on tailwind-core.
plugins {
    id("org.jetbrains.kotlin.multiplatform") version "2.4.0"
    id("org.jetbrains.compose") version "1.12.0-beta01"
    id("org.jetbrains.kotlin.plugin.compose") version "2.4.0"
}

group = "io.github.ronjunevaldoz"

kotlin {
    iosArm64()
    iosSimulatorArm64()

    jvm()

    js {
        browser()
        binaries.executable()
    }

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
        binaries.executable()
    }

    sourceSets {
        commonMain.dependencies {
            // Resolved from mavenLocal() (see ../../settings.gradle.kts's includeBuild and
            // this module's own settings.gradle.kts repo order) -- run
            // `./gradlew :tailwind-core:publishToMavenLocal` from the outer build first to
            // pick up local tailwind-core changes; otherwise this silently falls back to
            // whatever 0.1.1 is already live on Maven Central. tailwind-core carries no
            // Style-API/compose-foundation types of its own (just Color/Dp/TextStyle token
            // values), so unlike :tailwind-style below, depending on it doesn't risk mixing
            // two Compose Multiplatform versions' binary-incompatible classes at runtime.
            api("io.github.ronjunevaldoz:tailwind-core:0.1.1")
            // Deliberately NOT depending on :tailwind-style here, even though it's a published
            // sibling artifact and would compile fine: tailwind-style's classes are compiled
            // against Compose Multiplatform 1.11.1's androidx.compose.foundation.style.Style/
            // StyleScope, which have a different binary shape than 1.12.0-beta01's (that's
            // exactly why FilterStyle/SpacingStyle need 1.12.0-beta01 in the first place).
            // Confirmed empirically: adding that dependency compiled cleanly but every test
            // touching a tailwind-style function crashed at runtime with
            // `java.lang.AbstractMethodError` at Style.kt -- two incompatible versions of the
            // same Style-API classes can't coexist in one JVM process, matching the exact
            // Skiko WebAssembly LinkError this module's own demo/App.kt already documents for
            // the web target. FilterStyle.kt/SpacingStyle.kt call StyleScope's own
            // `background`/`width`/`height` properties directly instead of routing through
            // tailwind-style's bgStyle/widthStyle wrappers, for the same reason.
            implementation(compose.foundation)
            implementation(compose.ui)
        }
        commonTest.dependencies {
            implementation(kotlin("test"))
        }
        jvmTest.dependencies {
            implementation(compose.desktop.uiTestJUnit4)
            implementation(compose.desktop.currentOs)
        }
    }
}
