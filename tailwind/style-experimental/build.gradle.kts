import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

// A composite build (see ../../settings.gradle.kts's includeBuild), not a regular subproject
// -- own settings.gradle.kts, own plugin classpath. That's what makes it possible to apply
// org.jetbrains.compose at 1.12.0-beta01 here while every other module in the outer build
// stays on 1.11.1: within a single Gradle build, a plugin ID resolves to one version for
// every project once anything applies it via `apply false` at the root (which the outer
// build's org.jetbrains.compose alias already does, at 1.11.1) -- a second, differently
// versioned request for that same ID from a subproject fails outright.
//
// No Android target: Compose 1.12.0-beta01's Android artifacts (androidx.compose.ui:ui-android,
// androidx.compose.runtime:runtime-saveable-android) require AGP 9.1.0+ and compileSdk 37+ --
// this project (both outer and this module) is pinned to AGP 9.0.1/compileSdk 36. JVM/desktop
// already fully exercises and verifies the real Style API (see RingStyleTest.kt's passing pixel
// assertions); adding Android here would mean a second, unrelated version bump with its own
// compatibility cascade, out of scope for proving the Style API itself works.
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
            // whatever 0.1.1 is already live on Maven Central.
            api("io.github.ronjunevaldoz:tailwind-core:0.1.1")
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
