package io.github.ronjunevaldoz.tailwind.showcase.sections

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.style.ExperimentalFoundationStyleApi
import androidx.compose.foundation.style.Style
import androidx.compose.foundation.style.styleable
import androidx.compose.foundation.style.then
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.ronjunevaldoz.tailwind.core.TwColors
import io.github.ronjunevaldoz.tailwind.core.TwSpacing
import io.github.ronjunevaldoz.tailwind.modifiers.bgBlue500
import io.github.ronjunevaldoz.tailwind.modifiers.gap4
import io.github.ronjunevaldoz.tailwind.modifiers.size12
import io.github.ronjunevaldoz.tailwind.modifiers.size4
import io.github.ronjunevaldoz.tailwind.modifiers.size6
import io.github.ronjunevaldoz.tailwind.modifiers.size8
import io.github.ronjunevaldoz.tailwind.style.bgStyle
import io.github.ronjunevaldoz.tailwind.style.heightStyle
import io.github.ronjunevaldoz.tailwind.style.widthStyle

/** size4/size6/size8/size12 — a fixed square, one per scale step. */
@OptIn(ExperimentalFoundationStyleApi::class)
@Suppress("ktlint:standard:function-naming")
@Composable
fun SizingShowcase() {
    ShowcaseSection(
        title = "Sizing — size4, size6, size8, size12",
        code =
            """
            Row(horizontalArrangement = gap4()) {
                listOf(Modifier.size4(), Modifier.size6(), Modifier.size8(), Modifier.size12()).forEach { size ->
                    Box(size.bgBlue500())
                }
            }
            """.trimIndent(),
        styleCode =
            """
            // tailwind-style -- widthStyle()/heightStyle(), the Style API's own width()/height().
            Row(horizontalArrangement = gap4()) {
                listOf(TwSpacing.scale4, TwSpacing.scale6, TwSpacing.scale8, TwSpacing.scale12).forEach { scale ->
                    Box(
                        Modifier.styleable(
                            style = Style.widthStyle(scale).heightStyle(scale).then(Style.bgStyle(TwColors.blue500)),
                        ),
                    )
                }
            }
            """.trimIndent(),
        styleContent = {
            Row(horizontalArrangement = gap4()) {
                listOf(TwSpacing.scale4, TwSpacing.scale6, TwSpacing.scale8, TwSpacing.scale12).forEach { scale ->
                    Box(
                        Modifier.styleable(
                            style = Style.widthStyle(scale).heightStyle(scale).then(Style.bgStyle(TwColors.blue500)),
                        ),
                    )
                }
            }
        },
    ) {
        Row(horizontalArrangement = gap4()) {
            listOf(Modifier.size4(), Modifier.size6(), Modifier.size8(), Modifier.size12()).forEach { size ->
                Box(size.bgBlue500())
            }
        }
    }
}
