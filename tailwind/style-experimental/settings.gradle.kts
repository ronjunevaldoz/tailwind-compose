rootProject.name = "tailwind-style-experimental"

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
        // Checked first, so a locally-published tailwind-core (./gradlew :tailwind-core:
        // publishToMavenLocal from the outer build) wins over the real, already-published
        // Maven Central 0.1.1 -- tried Gradle's automatic composite-build dependency
        // substitution first (this module is included via includeBuild in the outer
        // settings.gradle.kts specifically to isolate its Compose Multiplatform plugin
        // version), but it does not fire for this direction: includeBuild's
        // dependencySubstitution block can only replace external deps with projects that
        // live *inside* the included build, not the other way around, and Gradle's implicit/
        // automatic matching never kicked in even after clearing the dependency cache and
        // rerunning with --refresh-dependencies. Requires a manual publishToMavenLocal step
        // whenever tailwind-core changes locally -- documented in this module's build.gradle.kts.
        mavenLocal()
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
