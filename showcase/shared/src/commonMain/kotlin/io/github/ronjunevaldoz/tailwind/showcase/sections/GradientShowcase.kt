package io.github.ronjunevaldoz.tailwind.showcase.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.ronjunevaldoz.tailwind.core.TwColors
import io.github.ronjunevaldoz.tailwind.modifiers.bgGradientToB
import io.github.ronjunevaldoz.tailwind.modifiers.bgGradientToR
import io.github.ronjunevaldoz.tailwind.modifiers.gap4

/** bgGradientToR/bgGradientToB — the two cardinal-direction gradients. */
@Suppress("ktlint:standard:function-naming")
@Composable
fun GradientShowcase() {
    ShowcaseSection(
        title = "Gradients — bgGradientToR, bgGradientToB",
        code =
            """
            Box(Modifier.size(80.dp).background(bgGradientToR(listOf(TwColors.blue500, TwColors.purple500))))
            Box(Modifier.size(80.dp).background(bgGradientToB(listOf(TwColors.pink500, TwColors.amber500))))
            """.trimIndent(),
    ) {
        Row(horizontalArrangement = gap4()) {
            Box(
                Modifier
                    .size(80.dp)
                    .background(bgGradientToR(listOf(TwColors.blue500, TwColors.purple500))),
            )
            Box(
                Modifier
                    .size(80.dp)
                    .background(bgGradientToB(listOf(TwColors.pink500, TwColors.amber500))),
            )
        }
    }
}
