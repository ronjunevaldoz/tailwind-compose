import org.gradle.api.tasks.testing.AbstractTestTask

plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidMultiplatformLibrary) apply false
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.composeCompiler) apply false
    alias(libs.plugins.kotlinJvm) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.ktlint) apply false
    alias(libs.plugins.detekt) apply false
    alias(libs.plugins.vanniktech.publish) apply false
}

allprojects {
    group = providers.gradleProperty("GROUP").get()
    version = providers.gradleProperty("VERSION_NAME").get()

    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    apply(plugin = "io.gitlab.arturbosch.detekt")

    extensions.configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
        version.set("1.3.1")
        android.set(false)
        outputToConsole.set(true)
        filter {
            exclude("**/generated/**")
            exclude("**/build/**")
        }
    }

    extensions.configure<io.gitlab.arturbosch.detekt.extensions.DetektExtension> {
        config.setFrom(rootProject.file("detekt.yml"))
        buildUponDefaultConfig = true
        allRules = false
    }

    // NOTE: no detekt-formatting plugin — Ktlint is this project's single source of
    // truth for formatting. Adding detekt-formatting on top double-covers the same
    // concern with an independently-versioned embedded ktlint, which disagreed with
    // our actual ktlint plugin version and also flamed out on Compose-Resources-generated
    // sources under build/ (which aren't excludable from that ruleset per-task — see
    // detekt.yml's per-rule excludes for the non-formatting rules that DO need them).

    tasks.withType<AbstractTestTask>().configureEach {
        testLogging {
            events("passed", "skipped", "failed")
            showStandardStreams = false
        }
    }
}

// ─── Maven Central publishing ───────────────────────────────────────────────
// Only the modules listed in publishedModuleDescriptions below are published.
// showcase:* modules are an internal demo app and must never carry publishing config.
val publishedModuleDescriptions =
    mapOf(
        "tailwind-core" to "Design tokens (spacing, color, typography, radius) for tailwind-compose.",
        "tailwind-layout" to "Tailwind-style spacing, sizing, flex, grid, aspect-ratio, and " +
            "responsive-breakpoint Modifier extensions for Compose Multiplatform.",
        "tailwind-color" to "Tailwind-style background/text color, gradient, and dark-mode " +
            "Modifier/TextStyle extensions for Compose Multiplatform.",
        "tailwind-typography" to "Tailwind-style font-size, line-height, font-weight, tracking, " +
            "and font-family TextStyle extensions for Compose Multiplatform.",
        "tailwind-effects" to "Tailwind-style border, box-shadow, opacity, filter, transition, " +
            "and 3D transform Modifier extensions for Compose Multiplatform.",
        "tailwind-compose" to "Tailwind CSS-inspired utilities for Compose Multiplatform " +
            "(facade module — depends on tailwind-core + tailwind-layout/color/typography/effects).",
        "tailwind-style" to "Tailwind-style ring, border, shadow, color, opacity, sizing, transform, " +
            "transition, typography, and z-index utilities built on the real Compose Styles API " +
            "(androidx.compose.foundation.style), not yet added to a release.yml publish step.",
    )

subprojects {
    val moduleDescription = publishedModuleDescriptions[name] ?: return@subprojects

    apply(plugin = "com.vanniktech.maven.publish")

    extensions.configure<com.vanniktech.maven.publish.MavenPublishBaseExtension> {
        publishToMavenCentral(com.vanniktech.maven.publish.SonatypeHost.CENTRAL_PORTAL, automaticRelease = true)

        // Only sign when an in-memory GPG key is actually available (release.yml sets
        // ORG_GRADLE_PROJECT_signingInMemoryKey, which Gradle exposes here as this
        // project property) -- otherwise publishToMavenLocal fails on every dev machine
        // with "no configured signatory" even though local smoke-testing doesn't need a
        // real signature. The real Maven Central publish in CI still gets signed, since
        // that's exactly when this property is set.
        if (providers.gradleProperty("signingInMemoryKey").isPresent) {
            signAllPublications()
        }

        coordinates(
            groupId = providers.gradleProperty("GROUP").get(),
            artifactId = this@subprojects.name,
            version = providers.gradleProperty("VERSION_NAME").get(),
        )

        pom {
            name.set(this@subprojects.name)
            description.set(moduleDescription)
            url.set("https://github.com/ronjunevaldoz/tailwind-compose")
            inceptionYear.set("2026")

            licenses {
                license {
                    name.set("Apache-2.0")
                    url.set("https://www.apache.org/licenses/LICENSE-2.0")
                }
            }

            developers {
                developer {
                    id.set("ronjunevaldoz")
                    name.set("Ron June Valdoz")
                    url.set("https://github.com/ronjunevaldoz")
                }
            }

            scm {
                url.set("https://github.com/ronjunevaldoz/tailwind-compose")
                connection.set("scm:git:git://github.com/ronjunevaldoz/tailwind-compose.git")
                developerConnection.set("scm:git:ssh://git@github.com/ronjunevaldoz/tailwind-compose.git")
            }
        }
    }
}
