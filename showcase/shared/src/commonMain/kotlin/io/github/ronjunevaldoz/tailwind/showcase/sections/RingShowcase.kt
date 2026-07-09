package io.github.ronjunevaldoz.tailwind.showcase.sections

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.ronjunevaldoz.tailwind.core.TwColors
import io.github.ronjunevaldoz.tailwind.modifiers.bgWhite
import io.github.ronjunevaldoz.tailwind.modifiers.gap8
import io.github.ronjunevaldoz.tailwind.modifiers.ring
import io.github.ronjunevaldoz.tailwind.modifiers.ring2
import io.github.ronjunevaldoz.tailwind.modifiers.ring4
import io.github.ronjunevaldoz.tailwind.modifiers.ring8

/**
 * ring/ring2/ring4/ring8 — an outline painted outside the box's own bounds (see
 * Ring.kt's KDoc), so each swatch needs surrounding padding wider than the ring
 * itself or the rings would visually collide with their neighbors in the Row.
 */
@Suppress("ktlint:standard:function-naming")
@Composable
fun RingShowcase() {
    ShowcaseSection(
        title = "Ring — ring, ring2, ring4, ring8",
        code =
            """
            Row(horizontalArrangement = gap8()) {
                listOf(Modifier.ring(TwColors.blue500), Modifier.ring2(TwColors.blue500),
                    Modifier.ring4(TwColors.blue500), Modifier.ring8(TwColors.blue500))
                    .forEach { ring ->
                        Box(Modifier.padding(8.dp).size(40.dp).then(ring).bgWhite())
                    }
            }
            """.trimIndent(),
        styleCode =
            """
            // tailwind-style-experimental -- Style.ringStyle(), a Style extension
            // (see tailwind/style-experimental's own module for why this can't
            // live-render here: a different, pre-release Compose Multiplatform version).
            Row(horizontalArrangement = gap8()) {
                listOf(Style.ringStyle(TwColors.blue500), Style.ringStyle2(TwColors.blue500),
                    Style.ringStyle4(TwColors.blue500), Style.ringStyle8(TwColors.blue500))
                    .forEach { ring ->
                        Box(
                            Modifier.padding(8.dp).size(40.dp)
                                .styleable(style = Style.bgStyle(Color.White).then(ring)),
                        )
                    }
            }
            """.trimIndent(),
    ) {
        Row(horizontalArrangement = gap8()) {
            listOf(
                Modifier.ring(TwColors.blue500),
                Modifier.ring2(TwColors.blue500),
                Modifier.ring4(TwColors.blue500),
                Modifier.ring8(TwColors.blue500),
            ).forEach { ring ->
                Box(
                    Modifier
                        .padding(8.dp)
                        .size(40.dp)
                        .then(ring)
                        .bgWhite(),
                )
            }
        }
    }
}
