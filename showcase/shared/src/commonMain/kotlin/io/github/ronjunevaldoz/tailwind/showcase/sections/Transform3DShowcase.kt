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
import io.github.ronjunevaldoz.tailwind.modifiers.perspectiveNormal
import io.github.ronjunevaldoz.tailwind.modifiers.rotateX45
import io.github.ronjunevaldoz.tailwind.modifiers.rotateY45
import io.github.ronjunevaldoz.tailwind.modifiers.rotateZ45
import io.github.ronjunevaldoz.tailwind.style.bgStyle
import io.github.ronjunevaldoz.tailwind.style.rotateXStyle
import io.github.ronjunevaldoz.tailwind.style.rotateYStyle
import io.github.ronjunevaldoz.tailwind.style.rotateZStyle

/** rotateX45/rotateY45/rotateZ45, each with perspectiveNormal applied. */
@OptIn(ExperimentalFoundationStyleApi::class)
@Suppress("ktlint:standard:function-naming")
@Composable
fun Transform3DShowcase() {
    ShowcaseSection(
        title = "3D Transforms — rotateX45, rotateY45, rotateZ45",
        code =
            """
            Row(horizontalArrangement = gap4()) {
                Box(Modifier.size(50.dp).perspectiveNormal().rotateX45().bgBlue500())
                Box(Modifier.size(50.dp).perspectiveNormal().rotateY45().bgBlue500())
                Box(Modifier.size(50.dp).perspectiveNormal().rotateZ45().bgBlue500())
            }
            """.trimIndent(),
        styleCode =
            """
            // tailwind-style -- rotateXStyle()/rotateYStyle()/rotateZStyle(), the Style API's
            // own RotationScope. No separate perspective call -- unlike the Modifier version's
            // graphicsLayer-based rotation, RotationScope applies its own perspective.
            Row(horizontalArrangement = gap4()) {
                listOf(Style.rotateXStyle(45f), Style.rotateYStyle(45f), Style.rotateZStyle(45f)).forEach { rotate ->
                    Box(
                        Modifier.size(50.dp)
                            .styleable(style = Style.bgStyle(TwColors.blue500).then(rotate)),
                    )
                }
            }
            """.trimIndent(),
        styleContent = {
            Row(horizontalArrangement = gap4()) {
                listOf(Style.rotateXStyle(45f), Style.rotateYStyle(45f), Style.rotateZStyle(45f)).forEach { rotate ->
                    Box(
                        Modifier
                            .size(50.dp)
                            .styleable(style = Style.bgStyle(TwColors.blue500).then(rotate)),
                    )
                }
            }
        },
    ) {
        Row(horizontalArrangement = gap4()) {
            Box(
                Modifier
                    .size(50.dp)
                    .perspectiveNormal()
                    .rotateX45()
                    .bgBlue500(),
            )
            Box(
                Modifier
                    .size(50.dp)
                    .perspectiveNormal()
                    .rotateY45()
                    .bgBlue500(),
            )
            Box(
                Modifier
                    .size(50.dp)
                    .perspectiveNormal()
                    .rotateZ45()
                    .bgBlue500(),
            )
        }
    }
}
