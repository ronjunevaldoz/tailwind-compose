package io.github.ronjunevaldoz.tailwind.showcase.sections

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import io.github.ronjunevaldoz.tailwind.core.TwColors
import io.github.ronjunevaldoz.tailwind.modifiers.bg
import io.github.ronjunevaldoz.tailwind.modifiers.border
import io.github.ronjunevaldoz.tailwind.modifiers.fontMono
import io.github.ronjunevaldoz.tailwind.modifiers.textSm
import io.github.ronjunevaldoz.tailwind.modifiers.textXs

private val CELL_WIDTH = 56.dp
private val CELL_HEIGHT = 40.dp
private val LABEL_WIDTH = 48.dp

/**
 * Full 26x11 palette grid, one bordered cell per swatch (`border()` from Border.kt —
 * the same 1.dp grid lines Tailwind's own palette docs use between swatches). Tap a
 * swatch to show its exact OKLCH source triple below the grid: every color in
 * `PALETTE_MATRIX` is OKLCH-derived, not a hand-picked hex value (see `PaletteSwatch.kt`).
 */
@Suppress("ktlint:standard:function-naming")
@Composable
fun PaletteMatrixShowcase() {
    ShowcaseSection(title = "Color — full palette matrix (26 hues x 11 shades)") {
        var selected by remember { mutableStateOf<PaletteSwatch?>(null) }
        val shadeCount = PALETTE_MATRIX.first().size

        Text(
            text =
                selected?.let { "${it.hue}-${it.shade}: ${it.oklch}" }
                    ?: "Tap a swatch to see the OKLCH value it was derived from",
            style =
                MaterialTheme.typography.bodyMedium
                    .textSm()
                    .fontMono(),
        )
        Spacer(Modifier.height(8.dp))
        Row {
            // Shade labels — fixed outside the horizontal scroll, like a sticky first column.
            Column {
                Spacer(Modifier.width(LABEL_WIDTH).height(CELL_HEIGHT))
                repeat(shadeCount) { shadeIndex ->
                    val shade = PALETTE_MATRIX.first()[shadeIndex].shade
                    Box(Modifier.width(LABEL_WIDTH).height(CELL_HEIGHT), contentAlignment = Alignment.CenterEnd) {
                        Text(shade.toString(), style = MaterialTheme.typography.bodySmall.textXs())
                    }
                }
            }
            Column(Modifier.horizontalScroll(rememberScrollState())) {
                Row {
                    PALETTE_MATRIX.forEach { hueColumn ->
                        Box(Modifier.width(CELL_WIDTH).height(CELL_HEIGHT), contentAlignment = Alignment.Center) {
                            Text(hueColumn.first().hue, style = MaterialTheme.typography.bodySmall.textXs())
                        }
                    }
                }
                repeat(shadeCount) { shadeIndex ->
                    Row {
                        PALETTE_MATRIX.forEach { hueColumn ->
                            val swatch = hueColumn[shadeIndex]
                            Box(
                                Modifier
                                    .bg(swatch.color)
                                    .border(TwColors.white)
                                    .width(CELL_WIDTH)
                                    .height(CELL_HEIGHT)
                                    .semantics { contentDescription = "${swatch.hue}-${swatch.shade} swatch" }
                                    .clickable { selected = swatch },
                            )
                        }
                    }
                }
            }
        }
    }
}
