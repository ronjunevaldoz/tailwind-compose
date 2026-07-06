package io.github.ronjunevaldoz.tailwind.showcase.sections

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.ronjunevaldoz.tailwind.core.TwColors
import io.github.ronjunevaldoz.tailwind.modifiers.border4
import io.github.ronjunevaldoz.tailwind.modifiers.gap4
import io.github.ronjunevaldoz.tailwind.modifiers.rounded
import io.github.ronjunevaldoz.tailwind.modifiers.roundedFull
import io.github.ronjunevaldoz.tailwind.modifiers.roundedLg
import io.github.ronjunevaldoz.tailwind.modifiers.roundedNone

/** rounded corners (none/default/lg/full) applied to a bordered square. */
@Suppress("ktlint:standard:function-naming")
@Composable
fun BorderShowcase() {
    ShowcaseSection(title = "Borders — roundedNone, rounded, roundedLg, roundedFull") {
        Row(horizontalArrangement = gap4()) {
            listOf(Modifier.roundedNone(), Modifier.rounded(), Modifier.roundedLg(), Modifier.roundedFull())
                .forEach { shape ->
                    Box(shape.size(40.dp).border4(TwColors.blue500))
                }
        }
    }
}
