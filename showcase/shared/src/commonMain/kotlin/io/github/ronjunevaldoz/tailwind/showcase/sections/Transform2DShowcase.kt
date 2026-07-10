package io.github.ronjunevaldoz.tailwind.showcase.sections

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.style.ExperimentalFoundationStyleApi
import androidx.compose.foundation.style.Style
import androidx.compose.foundation.style.styleable
import androidx.compose.foundation.style.then
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.ronjunevaldoz.tailwind.core.TwColors
import io.github.ronjunevaldoz.tailwind.core.TwSpacing
import io.github.ronjunevaldoz.tailwind.modifiers.bgBlue500
import io.github.ronjunevaldoz.tailwind.modifiers.gap4
import io.github.ronjunevaldoz.tailwind.modifiers.scale75
import io.github.ronjunevaldoz.tailwind.modifiers.scale110
import io.github.ronjunevaldoz.tailwind.modifiers.translateX8
import io.github.ronjunevaldoz.tailwind.modifiers.translateXNeg8
import io.github.ronjunevaldoz.tailwind.modifiers.translateY8
import io.github.ronjunevaldoz.tailwind.style.bgStyle
import io.github.ronjunevaldoz.tailwind.style.scaleStyle
import io.github.ronjunevaldoz.tailwind.style.translateXStyle
import io.github.ronjunevaldoz.tailwind.style.translateYStyle

/** scale75/scale110/translateX8/translateXNeg8/translateY8, each on its own swatch. */
@OptIn(ExperimentalFoundationStyleApi::class)
@Suppress("ktlint:standard:function-naming")
@Composable
fun Transform2DShowcase() {
    ShowcaseSection(
        title = "2D Transforms — scale75, scale110, translateX8, translateXNeg8, translateY8",
        code =
            """
            Row(horizontalArrangement = gap4()) {
                Box(Modifier.size(50.dp).scale75().bgBlue500())
                Box(Modifier.size(50.dp).scale110().bgBlue500())
                Box(Modifier.size(50.dp).translateX8().bgBlue500())
                Box(Modifier.size(50.dp).translateXNeg8().bgBlue500())
                Box(Modifier.size(50.dp).translateY8().bgBlue500())
            }
            """.trimIndent(),
        styleCode =
            """
            // tailwind-style -- scaleStyle()/translateXStyle()/translateYStyle(), the Style
            // API's own scaleX/scaleY/translationX/translationY properties.
            Row(horizontalArrangement = gap4()) {
                listOf(
                    Style.scaleStyle(0.75f), Style.scaleStyle(1.10f),
                    Style.translateXStyle(TwSpacing.scale8), Style.translateXStyle(-TwSpacing.scale8),
                    Style.translateYStyle(TwSpacing.scale8),
                ).forEach { transform ->
                    Box(
                        Modifier.size(50.dp)
                            .styleable(style = Style.bgStyle(TwColors.blue500).then(transform)),
                    )
                }
            }
            """.trimIndent(),
        styleContent = {
            Row(horizontalArrangement = gap4()) {
                listOf(
                    Style.scaleStyle(0.75f),
                    Style.scaleStyle(1.10f),
                    Style.translateXStyle(TwSpacing.scale8),
                    Style.translateXStyle(-TwSpacing.scale8),
                    Style.translateYStyle(TwSpacing.scale8),
                ).forEach { transform ->
                    Box(
                        Modifier
                            .size(50.dp)
                            .styleable(style = Style.bgStyle(TwColors.blue500).then(transform)),
                    )
                }
            }
        },
    ) {
        Row(horizontalArrangement = gap4()) {
            Box(Modifier.size(50.dp).scale75().bgBlue500())
            Box(Modifier.size(50.dp).scale110().bgBlue500())
            Box(Modifier.size(50.dp).translateX8().bgBlue500())
            Box(Modifier.size(50.dp).translateXNeg8().bgBlue500())
            Box(Modifier.size(50.dp).translateY8().bgBlue500())
        }
    }
}
