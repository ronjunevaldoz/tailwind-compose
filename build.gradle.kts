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
// Only the three public library modules are published. showcase:* modules are
// an internal demo app and must never carry publishing config.
val publishedModuleDescriptions =
    mapOf(
        "tailwind-core" to "Design tokens (spacing, color, typography, radius) for tailwind-compose.",
        "tailwind-modifiers" to "Tailwind-style Modifier extension functions for Compose Multiplatform.",
        "tailwind-compose" to "Tailwind CSS-inspired utilities for Compose Multiplatform " +
            "(facade module — depends on tailwind-core + tailwind-modifiers).",
    )

subprojects {
    val moduleDescription = publishedModuleDescriptions[name] ?: return@subprojects

    apply(plugin = "com.vanniktech.maven.publish")

    extensions.configure<com.vanniktech.maven.publish.MavenPublishBaseExtension> {
        publishToMavenCentral(com.vanniktech.maven.publish.SonatypeHost.CENTRAL_PORTAL)
        signAllPublications()

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
