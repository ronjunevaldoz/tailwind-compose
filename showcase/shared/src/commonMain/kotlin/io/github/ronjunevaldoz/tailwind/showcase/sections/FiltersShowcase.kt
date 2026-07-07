package io.github.ronjunevaldoz.tailwind.showcase.sections

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.github.ronjunevaldoz.tailwind.core.TwColors
import io.github.ronjunevaldoz.tailwind.modifiers.bgBlue500
import io.github.ronjunevaldoz.tailwind.modifiers.blurSm
import io.github.ronjunevaldoz.tailwind.modifiers.fontMono
import io.github.ronjunevaldoz.tailwind.modifiers.gap2
import io.github.ronjunevaldoz.tailwind.modifiers.gap4
import io.github.ronjunevaldoz.tailwind.modifiers.grayscale
import io.github.ronjunevaldoz.tailwind.modifiers.invert
import io.github.ronjunevaldoz.tailwind.modifiers.p4
import io.github.ronjunevaldoz.tailwind.modifiers.sepia
import io.github.ronjunevaldoz.tailwind.modifiers.textSlate500
import io.github.ronjunevaldoz.tailwind.modifiers.textXs

private val DEMO_WIDTH = 220.dp

/** blurSm/grayscale/invert/sepia over the same flat blue fill. */
@Suppress("ktlint:standard:function-naming")
@Composable
fun FiltersShowcase() {
    ShowcaseSection(
        title = "Filters — blurSm, grayscale, invert, sepia",
        code =
            """
            Row(
                modifier = Modifier.horizontalScroll(rememberScrollState()),
                horizontalArrangement = gap4(),
            ) {
                Box(Modifier.size(40.dp).blurSm().bgBlue500())
                Box(Modifier.size(40.dp).grayscale().bgBlue500())
                Box(Modifier.size(40.dp).invert().bgBlue500())
                Box(Modifier.size(40.dp).sepia().bgBlue500())
            }
            """.trimIndent(),
    ) {
        // Narrower than the row's natural content width so the scroll is actually
        // demonstrated, not just theoretically present.
        Row(
            modifier =
                Modifier
                    .width(DEMO_WIDTH)
                    .dotGridBackground()
                    .horizontalScroll(rememberScrollState())
                    .p4(),
            horizontalArrangement = gap4(),
        ) {
            FilterSwatch("blurSm", Modifier.size(40.dp).blurSm().bgBlue500())
            FilterSwatch("grayscale", Modifier.size(40.dp).grayscale().bgBlue500())
            FilterSwatch("invert", Modifier.size(40.dp).invert().bgBlue500())
            FilterSwatch("sepia", Modifier.size(40.dp).sepia().bgBlue500())
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
private fun FilterSwatch(
    label: String,
    swatchModifier: Modifier,
) {
    Column(verticalArrangement = gap2()) {
        Text(
            label,
            style =
                MaterialTheme.typography.bodySmall
                    .textXs()
                    .fontMono()
                    .textSlate500(),
        )
        Box(swatchModifier)
    }
}

private const val DOT_GRID_SPACING_DP = 12
private const val DOT_RADIUS_DP = 1

/**
 * Decorative dotted-grid backdrop, matching the pattern Tailwind's own showcase pages use
 * behind demo content. Not a Tailwind utility itself (no `dot-grid` class exists) -- kept
 * local to this file rather than the published library, which only ships things that map
 * to an actual Tailwind concept.
 */
private fun Modifier.dotGridBackground(
    dotColor: Color = TwColors.slate300,
    spacing: Dp = DOT_GRID_SPACING_DP.dp,
    dotRadius: Dp = DOT_RADIUS_DP.dp,
): Modifier =
    drawBehind {
        val spacingPx = spacing.toPx()
        val radiusPx = dotRadius.toPx()
        var y = spacingPx / 2
        while (y < size.height) {
            var x = spacingPx / 2
            while (x < size.width) {
                drawCircle(color = dotColor, radius = radiusPx, center = Offset(x, y))
                x += spacingPx
            }
            y += spacingPx
        }
    }
