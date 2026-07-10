import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

// The stable half of the Style-API port: everything here compiles against Compose
// Multiplatform 1.11.1 (this outer build's own version -- see gradle/libs.versions.toml),
// so it's a regular subproject with a full target set including Android, not an isolated
// composite build. tailwind/style-experimental holds the remaining Style-API surface that
// genuinely isn't in 1.11.1 yet (colorFilter, contentPadding/externalPadding) and depends on
// this module's published artifact for everything else, the same way it already depends on
// tailwind-core.
plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidMultiplatformLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

kotlin {
    iosArm64()
    iosSimulatorArm64()

    jvm()

    js {
        browser()
    }

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
    }

    androidLibrary {
        namespace = "io.github.ronjunevaldoz.tailwind.style"
        compileSdk =
            libs.versions.android.compileSdk
                .get()
                .toInt()
        minSdk =
            libs.versions.android.minSdk
                .get()
                .toInt()

        compilerOptions {
            jvmTarget = JvmTarget.JVM_11
        }
        withHostTest {}
    }

    sourceSets {
        commonMain.dependencies {
            api(projects.tailwindCore)
            implementation(libs.compose.ui)
            implementation(libs.compose.foundation)
            implementation(compose.animation)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        jvmTest.dependencies {
            implementation(compose.desktop.uiTestJUnit4)
            implementation(compose.desktop.currentOs)
        }
    }
}
