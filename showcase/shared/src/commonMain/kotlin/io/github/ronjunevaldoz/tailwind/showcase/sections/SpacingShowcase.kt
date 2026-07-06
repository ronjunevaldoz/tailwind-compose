package io.github.ronjunevaldoz.tailwind.showcase.sections

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.ronjunevaldoz.tailwind.modifiers.bgBlue500
import io.github.ronjunevaldoz.tailwind.modifiers.bgSlate200
import io.github.ronjunevaldoz.tailwind.modifiers.gap4
import io.github.ronjunevaldoz.tailwind.modifiers.p1
import io.github.ronjunevaldoz.tailwind.modifiers.p2
import io.github.ronjunevaldoz.tailwind.modifiers.p4
import io.github.ronjunevaldoz.tailwind.modifiers.p8

/**
 * p1/p2/p4/p8 — each wraps the same inner square, so growing padding is visible.
 * bg*() must precede p*() in the chain so the background covers the padded area
 * too, not just the inner content (see Opacity.kt's KDoc for the same rule).
 */
@Suppress("ktlint:standard:function-naming")
@Composable
fun SpacingShowcase() {
    ShowcaseSection(title = "Spacing — p1, p2, p4, p8") {
        Row(horizontalArrangement = gap4()) {
            listOf(Modifier.p1(), Modifier.p2(), Modifier.p4(), Modifier.p8()).forEach { padding ->
                Box(Modifier.bgSlate200().then(padding)) {
                    Box(Modifier.size(16.dp).bgBlue500())
                }
            }
        }
    }
}
