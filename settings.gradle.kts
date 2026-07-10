rootProject.name = "tailwind-compose-root"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

// The five published library modules live under tailwind/<name> on disk (no redundant
// tailwind- prefix once nested), but keep their original (flat) Gradle project paths --
// via projectDir below -- so every projects.tailwindXxx type-safe accessor, :tailwind-xxx
// task reference, and published artifactId elsewhere in the build stays unchanged.
include(":tailwind-core")
include(":tailwind-layout")
include(":tailwind-color")
include(":tailwind-typography")
include(":tailwind-effects")
include(":tailwind-compose")
include(":tailwind-style")

project(":tailwind-core").projectDir = file("tailwind/core")
project(":tailwind-layout").projectDir = file("tailwind/layout")
project(":tailwind-color").projectDir = file("tailwind/color")
project(":tailwind-typography").projectDir = file("tailwind/typography")
project(":tailwind-effects").projectDir = file("tailwind/effects")
project(":tailwind-compose").projectDir = file("tailwind/compose")
project(":tailwind-style").projectDir = file("tailwind/style")

// Not part of the stable module graph above: pinned to a pre-release Compose Multiplatform
// version (1.12.0-beta01) for the handful of androidx.compose.foundation.style properties
// (colorFilter, contentPadding, externalPadding) not yet in the 1.11.1 stable line this
// project otherwise targets -- see tailwind/style-experimental/build.gradle.kts. Everything
// else in the Style-API port that's already 1.11.1-compatible lives in the regular
// :tailwind-style subproject above instead, which this module depends on as a published
// artifact. A regular `include()` subproject can't mix Compose versions: the root's
// `apply false` on org.jetbrains.compose locks that plugin ID to 1.11.1 build-wide, and a
// subproject requesting a different version of the same plugin ID fails to resolve.
// `includeBuild` (a real composite build, its own settings.gradle.kts/plugin classpath) is
// the only way to get genuine version isolation for just this narrower leftover surface.
includeBuild("tailwind/style-experimental")

include(":showcase:shared")
include(":showcase:androidApp")
include(":showcase:desktopApp")
