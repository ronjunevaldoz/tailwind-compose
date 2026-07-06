package io.github.ronjunevaldoz.tailwind.showcase.sections

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

/**
 * Text-only explainers for CSS/Tailwind concepts that have no tailwind-compose
 * utility because there's no Compose equivalent to build one on top of — see
 * docs/tailwind-coverage-matrix.md for the full reasoning behind each.
 */
@Suppress("ktlint:standard:function-naming")
@Composable
fun CssVariablesExplainer() {
    ShowcaseSection(title = "CSS Variables") {
        Text(
            "Tailwind config values compile down to CSS custom properties " +
                "(--color-blue-500, --spacing, ...) so a page's stylesheet can " +
                "reference them at runtime. tailwind-compose has no CSS to " +
                "generate custom properties into — TwColors, TwSpacing, TwRadius, " +
                "etc. already ARE the single source of truth, resolved at compile " +
                "time as ordinary Kotlin constants. Nothing to add here.",
        )
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun CascadeLayersExplainer() {
    ShowcaseSection(title = "Cascade Layers") {
        Text(
            "@layer controls which CSS rules win when two stylesheets " +
                "conflict, by giving each layer an explicit priority. Compose " +
                "has no stylesheet and no cascade — a Modifier chain is just " +
                "function calls executed in the order you write them, so there's " +
                "no specificity conflict for a layering system to resolve. " +
                "Not applicable.",
        )
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun ContainerQueriesExplainer() {
    ShowcaseSection(title = "Container Queries") {
        Text(
            "@container lets an element's styling react to its own containing " +
                "box's size, not just the viewport. Compose's closest equivalent " +
                "is BoxWithConstraints (or WindowSizeClass at the app level) — a " +
                "genuinely different mechanism (read available constraints during " +
                "composition, branch in code) rather than a Modifier utility, so " +
                "it needs its own skill/pattern (kotlin-multiplatform-adaptive-layout) " +
                "instead of a tailwind-compose function.",
        )
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun LogicalPropertiesExplainer() {
    ShowcaseSection(title = "Logical Properties") {
        Text(
            "Already partially covered, not a gap: pl()/pr() in Spacing.kt map to " +
                "Compose's start/end padding (RTL-aware), not literal left/right — " +
                "the same distinction CSS logical properties (padding-inline-start, " +
                "margin-inline-end, ...) make over their physical equivalents.",
        )
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun P3ColorsExplainer() {
    ShowcaseSection(title = "P3 Colors") {
        Text(
            "Tailwind v4 doesn't ship a separate P3 palette — every color is defined " +
                "as a single OKLCH triple, and browsers render OKLCH in the widest gamut " +
                "the display supports (sRGB or P3), automatically. TwColors.kt already " +
                "sources the exact same OKLCH values from Oklch.kt, so the color data " +
                "itself has never been the gap.\n\n" +
                "The gap is Compose's rendering pipeline: Modifier.background() and every " +
                "other path through androidx.compose.ui.graphics.Paint calls " +
                "Color.toArgb(), which does `convert(ColorSpaces.Srgb).value shr 32` — " +
                "gamut-mapping and clipping down to 8-bit sRGB before the pixel is ever " +
                "drawn. That's true on every Compose Multiplatform target (Android, " +
                "Desktop/Skiko, iOS, JS, WasmJs) as of Compose Multiplatform 1.11.1, not " +
                "just platforms without wide-gamut displays. Tagging a Color with " +
                "ColorSpaces.DisplayP3 compiles and carries the metadata, but that " +
                "metadata is discarded before compositing, so it currently buys nothing " +
                "through the standard Modifier surface this library builds on. Not " +
                "something a Modifier-based utility library can work around — it would " +
                "need Compose itself to add a wide-gamut-aware paint path.",
        )
    }
}
