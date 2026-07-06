package io.github.ronjunevaldoz.tailwind.showcase.sections

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.ronjunevaldoz.tailwind.modifiers.aspectSquare
import io.github.ronjunevaldoz.tailwind.modifiers.aspectVideo
import io.github.ronjunevaldoz.tailwind.modifiers.bgBlue500
import io.github.ronjunevaldoz.tailwind.modifiers.gap4

/** aspectSquare (1:1) and aspectVideo (16:9), both driven by a fixed width. */
@Suppress("ktlint:standard:function-naming")
@Composable
fun AspectRatioShowcase() {
    ShowcaseSection(title = "Aspect ratio — aspectSquare, aspectVideo") {
        Row(horizontalArrangement = gap4()) {
            Box(Modifier.width(48.dp).aspectSquare().bgBlue500())
            Box(Modifier.width(96.dp).aspectVideo().bgBlue500())
        }
    }
}
