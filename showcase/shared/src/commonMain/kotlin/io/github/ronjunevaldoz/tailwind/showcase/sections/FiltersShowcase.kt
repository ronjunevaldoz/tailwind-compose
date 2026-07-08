package io.github.ronjunevaldoz.tailwind.showcase.sections

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.github.ronjunevaldoz.shared.generated.resources.Res
import io.github.ronjunevaldoz.shared.generated.resources.filter
import io.github.ronjunevaldoz.tailwind.core.TwColors
import io.github.ronjunevaldoz.tailwind.modifiers.blurXs
import io.github.ronjunevaldoz.tailwind.modifiers.brightness150
import io.github.ronjunevaldoz.tailwind.modifiers.contrast150
import io.github.ronjunevaldoz.tailwind.modifiers.fontMedium
import io.github.ronjunevaldoz.tailwind.modifiers.fontMono
import io.github.ronjunevaldoz.tailwind.modifiers.gap2
import io.github.ronjunevaldoz.tailwind.modifiers.gap4
import io.github.ronjunevaldoz.tailwind.modifiers.grayscale
import io.github.ronjunevaldoz.tailwind.modifiers.p2
import io.github.ronjunevaldoz.tailwind.modifiers.p4
import io.github.ronjunevaldoz.tailwind.modifiers.saturate200
import io.github.ronjunevaldoz.tailwind.modifiers.sepia
import io.github.ronjunevaldoz.tailwind.modifiers.textSlate500
import io.github.ronjunevaldoz.tailwind.modifiers.textSlate900
import io.github.ronjunevaldoz.tailwind.modifiers.textXs
import org.jetbrains.compose.resources.painterResource

private val IMAGE_SIZE = 220.dp
private val CHIP_ROW_WIDTH = 220.dp

/**
 * One shared photo in one container -- the filter row itself has no background/image of its
 * own (`FilterChip` is plain text, "transparent"), it just picks which single filter modifier
 * is currently applied to the one [Image] above. Matches Tailwind's own tailwindcss.com
 * marketing-page filters demo: one photo, filter names next to/over it, not N independently
 * filtered thumbnails side by side.
 */
private enum class FilterOption(
    val label: String,
    val apply: Modifier.() -> Modifier,
) {
    NONE("none", { this }),
    BLUR_XS("blurXs", { blurXs() }),
    BRIGHTNESS_150("brightness150", { brightness150() }),
    GRAYSCALE("grayscale", { grayscale() }),
    CONTRAST_150("contrast150", { contrast150() }),
    SATURATE_200("saturate200", { saturate200() }),
    SEPIA("sepia", { sepia() }),
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun FiltersShowcase() {
    ShowcaseSection(
        title = "Filters — blurXs, brightness150, grayscale, contrast150, saturate200, sepia",
        code =
            """
            var selected by remember { mutableStateOf(FilterOption.BLUR_XS) }

            Image(
                painterResource(Res.drawable.filter),
                null,
                Modifier.size(220.dp).run(selected.apply),
                contentScale = ContentScale.Crop,
            )
            Row(
                modifier = Modifier.horizontalScroll(rememberScrollState()),
                horizontalArrangement = gap2(),
            ) {
                // one tappable, transparent label per option -- tapping re-filters the image above
                Text("blurXs", modifier = Modifier.clickable { selected = FilterOption.BLUR_XS })
                Text("grayscale", modifier = Modifier.clickable { selected = FilterOption.GRAYSCALE })
                // ...
            }
            """.trimIndent(),
    ) {
        FilterCarousel()
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun FilterCarousel() {
    var selected by remember { mutableStateOf(FilterOption.BLUR_XS) }

    Column(verticalArrangement = gap4()) {
        Box(modifier = Modifier.dotGridBackground().p4()) {
            Image(
                painter = painterResource(Res.drawable.filter),
                contentDescription = "Photo with ${selected.label} filter applied",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(IMAGE_SIZE).run(selected.apply),
            )
        }

        Row(
            modifier =
                Modifier
                    .width(CHIP_ROW_WIDTH)
                    .horizontalScroll(rememberScrollState()),
            horizontalArrangement = gap2(),
        ) {
            FilterOption.entries.forEach { option ->
                FilterChip(
                    option = option,
                    isSelected = option == selected,
                    onClick = { selected = option },
                )
            }
        }
    }
}

/** A plain, background-less ("transparent") tappable label -- the filter row has no visual
 * content of its own, it only ever selects which filter is applied to the shared [Image]
 * in [FilterCarousel].
 */
@Suppress("ktlint:standard:function-naming")
@Composable
private fun FilterChip(
    option: FilterOption,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    val baseStyle =
        MaterialTheme.typography.bodySmall
            .textXs()
            .fontMono()
    Text(
        text = option.label,
        style = if (isSelected) baseStyle.textSlate900().fontMedium() else baseStyle.textSlate500(),
        modifier = Modifier.clickable(onClick = onClick).p2(),
    )
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
