package io.github.ronjunevaldoz.tailwind.showcase.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.ronjunevaldoz.tailwind.core.TwColors
import io.github.ronjunevaldoz.tailwind.modifiers.gap1
import io.github.ronjunevaldoz.tailwind.modifiers.gap4

private const val HUES_PER_ROW = 9

private val blueRamp =
    listOf(
        TwColors.blue50,
        TwColors.blue100,
        TwColors.blue200,
        TwColors.blue300,
        TwColors.blue400,
        TwColors.blue500,
        TwColors.blue600,
        TwColors.blue700,
        TwColors.blue800,
        TwColors.blue900,
        TwColors.blue950,
    )

private val everyHueAt500 =
    listOf(
        TwColors.slate500,
        TwColors.gray500,
        TwColors.zinc500,
        TwColors.neutral500,
        TwColors.stone500,
        TwColors.mauve500,
        TwColors.mist500,
        TwColors.olive500,
        TwColors.taupe500,
        TwColors.red500,
        TwColors.orange500,
        TwColors.amber500,
        TwColors.yellow500,
        TwColors.lime500,
        TwColors.green500,
        TwColors.emerald500,
        TwColors.teal500,
        TwColors.cyan500,
        TwColors.sky500,
        TwColors.blue500,
        TwColors.indigo500,
        TwColors.violet500,
        TwColors.purple500,
        TwColors.fuchsia500,
        TwColors.pink500,
        TwColors.rose500,
    )

/**
 * Two views into the palette: a full-scale ramp for one hue (shows the OKLCH-based
 * perceptual progression from 50 to 950), and one swatch per hue at 500 (shows breadth).
 */
@Suppress("ktlint:standard:function-naming")
@Composable
fun ColorShowcase() {
    ShowcaseSection(title = "Color — blue-50 through blue-950") {
        Row(horizontalArrangement = gap1()) {
            blueRamp.forEach { color -> Box(Modifier.size(24.dp).background(color)) }
        }
    }
    ShowcaseSection(title = "Color — every hue at shade 500") {
        Column {
            everyHueAt500.chunked(HUES_PER_ROW).forEach { rowColors ->
                Row(horizontalArrangement = gap4()) {
                    rowColors.forEach { color -> Box(Modifier.size(24.dp).background(color)) }
                }
            }
        }
    }
}
