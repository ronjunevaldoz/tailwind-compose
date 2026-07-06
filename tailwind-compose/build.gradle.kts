import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    // No Compose Multiplatform/Compiler plugins here — this facade module has zero
    // Compose code of its own (see TailwindCompose.kt), it only re-exports
    // tailwind-core/tailwind-modifiers via api(). Applying the Compose Compiler plugin
    // without compose.runtime on this module's own classpath breaks compilation.
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidMultiplatformLibrary)
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
        namespace = "io.github.ronjunevaldoz.tailwind"
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
    }

    sourceSets {
        commonMain.dependencies {
            // Facade module: consumers depend on this single artifact
            // to get both design tokens and Modifier utilities.
            api(projects.tailwindCore)
            api(projects.tailwindModifiers)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}
