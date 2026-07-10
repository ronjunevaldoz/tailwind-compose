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
import io.github.ronjunevaldoz.tailwind.modifiers.bgBlue500
import io.github.ronjunevaldoz.tailwind.modifiers.gap4
import io.github.ronjunevaldoz.tailwind.modifiers.opacity100
import io.github.ronjunevaldoz.tailwind.modifiers.opacity25
import io.github.ronjunevaldoz.tailwind.modifiers.opacity50
import io.github.ronjunevaldoz.tailwind.modifiers.opacity75
import io.github.ronjunevaldoz.tailwind.style.bgStyle
import io.github.ronjunevaldoz.tailwind.style.opacityStyle
import io.github.ronjunevaldoz.tailwind.style.opacityStyle100
import io.github.ronjunevaldoz.tailwind.style.opacityStyle50

/**
 * opacity25/50/75/100 — opacity*() must precede bg*() in the chain to take
 * effect (see Opacity.kt's KDoc).
 */
@OptIn(ExperimentalFoundationStyleApi::class)
@Suppress("ktlint:standard:function-naming")
@Composable
fun OpacityShowcase() {
    ShowcaseSection(
        title = "Opacity — 25%, 50%, 75%, 100%",
        code =
            """
            Row(horizontalArrangement = gap4()) {
                listOf(Modifier.opacity25(), Modifier.opacity50(), Modifier.opacity75(), Modifier.opacity100())
                    .forEach { opacity ->
                        Box(opacity.size(32.dp).bgBlue500())
                    }
            }
            """.trimIndent(),
        styleCode =
            """
            // tailwind-style -- opacityStyle*(), the Style API's own alpha() property.
            Row(horizontalArrangement = gap4()) {
                listOf(Style.opacityStyle(0.25f), Style.opacityStyle50(), Style.opacityStyle(0.75f), Style.opacityStyle100())
                    .forEach { opacity ->
                        Box(
                            Modifier.size(32.dp)
                                .styleable(style = Style.bgStyle(TwColors.blue500).then(opacity)),
                        )
                    }
            }
            """.trimIndent(),
        styleContent = {
            Row(horizontalArrangement = gap4()) {
                listOf(
                    Style.opacityStyle(0.25f),
                    Style.opacityStyle50(),
                    Style.opacityStyle(0.75f),
                    Style.opacityStyle100(),
                ).forEach { opacity ->
                    Box(
                        Modifier
                            .size(32.dp)
                            .styleable(style = Style.bgStyle(TwColors.blue500).then(opacity)),
                    )
                }
            }
        },
    ) {
        Row(horizontalArrangement = gap4()) {
            listOf(Modifier.opacity25(), Modifier.opacity50(), Modifier.opacity75(), Modifier.opacity100())
                .forEach { opacity ->
                    Box(opacity.size(32.dp).bgBlue500())
                }
        }
    }
}
