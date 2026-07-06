package io.github.ronjunevaldoz.tailwind.showcase.sections

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.ronjunevaldoz.tailwind.modifiers.bgBlue500
import io.github.ronjunevaldoz.tailwind.modifiers.blurSm
import io.github.ronjunevaldoz.tailwind.modifiers.gap4
import io.github.ronjunevaldoz.tailwind.modifiers.grayscale
import io.github.ronjunevaldoz.tailwind.modifiers.invert
import io.github.ronjunevaldoz.tailwind.modifiers.sepia

/** blurSm/grayscale/invert/sepia over the same flat blue fill. */
@Suppress("ktlint:standard:function-naming")
@Composable
fun FiltersShowcase() {
    ShowcaseSection(title = "Filters — blurSm, grayscale, invert, sepia") {
        Row(horizontalArrangement = gap4()) {
            Box(Modifier.size(40.dp).blurSm().bgBlue500())
            Box(Modifier.size(40.dp).grayscale().bgBlue500())
            Box(Modifier.size(40.dp).invert().bgBlue500())
            Box(Modifier.size(40.dp).sepia().bgBlue500())
        }
    }
}
