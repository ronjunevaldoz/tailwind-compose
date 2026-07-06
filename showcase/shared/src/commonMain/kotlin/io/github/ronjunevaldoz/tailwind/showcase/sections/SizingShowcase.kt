package io.github.ronjunevaldoz.tailwind.showcase.sections

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.ronjunevaldoz.tailwind.modifiers.bgBlue500
import io.github.ronjunevaldoz.tailwind.modifiers.gap4
import io.github.ronjunevaldoz.tailwind.modifiers.size12
import io.github.ronjunevaldoz.tailwind.modifiers.size4
import io.github.ronjunevaldoz.tailwind.modifiers.size6
import io.github.ronjunevaldoz.tailwind.modifiers.size8

/** size4/size6/size8/size12 — a fixed square, one per scale step. */
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
    ) {
        Row(horizontalArrangement = gap4()) {
            listOf(Modifier.size4(), Modifier.size6(), Modifier.size8(), Modifier.size12()).forEach { size ->
                Box(size.bgBlue500())
            }
        }
    }
}
