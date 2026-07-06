package io.github.ronjunevaldoz.tailwind.showcase.sections

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import io.github.ronjunevaldoz.tailwind.core.TwColors
import io.github.ronjunevaldoz.tailwind.core.TwRadius
import io.github.ronjunevaldoz.tailwind.modifiers.border4
import io.github.ronjunevaldoz.tailwind.modifiers.gap4

/**
 * rounded corners (none/default/lg/full) applied to a bordered square. The same
 * [RoundedCornerShape] is passed to both `clip()` and `border4()` — see border4's
 * KDoc for why that's more robust than relying on clip() alone.
 */
@Suppress("ktlint:standard:function-naming")
@Composable
fun BorderShowcase() {
    ShowcaseSection(
        title = "Borders — roundedNone, rounded, roundedLg, roundedFull",
        code =
            """
            Row(horizontalArrangement = gap4()) {
                listOf(TwRadius.none, TwRadius.base, TwRadius.lg, TwRadius.full).forEach { radius ->
                    val shape = RoundedCornerShape(radius)
                    Box(Modifier.clip(shape).size(40.dp).border4(TwColors.blue500, shape))
                }
            }
            """.trimIndent(),
    ) {
        Row(horizontalArrangement = gap4()) {
            listOf(TwRadius.none, TwRadius.base, TwRadius.lg, TwRadius.full).forEach { radius ->
                val shape = RoundedCornerShape(radius)
                Box(Modifier.clip(shape).size(40.dp).border4(TwColors.blue500, shape))
            }
        }
    }
}
