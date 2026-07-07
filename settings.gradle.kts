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

// The six published library modules live under tailwind/<name> on disk (no redundant
// tailwind- prefix once nested), but keep their original (flat) Gradle project paths --
// via projectDir below -- so every projects.tailwindXxx type-safe accessor, :tailwind-xxx
// task reference, and published artifactId elsewhere in the build stays unchanged.
include(":tailwind-core")
include(":tailwind-layout")
include(":tailwind-color")
include(":tailwind-typography")
include(":tailwind-effects")
include(":tailwind-compose")

project(":tailwind-core").projectDir = file("tailwind/core")
project(":tailwind-layout").projectDir = file("tailwind/layout")
project(":tailwind-color").projectDir = file("tailwind/color")
project(":tailwind-typography").projectDir = file("tailwind/typography")
project(":tailwind-effects").projectDir = file("tailwind/effects")
project(":tailwind-compose").projectDir = file("tailwind/compose")

include(":showcase:shared")
include(":showcase:androidApp")
include(":showcase:desktopApp")
