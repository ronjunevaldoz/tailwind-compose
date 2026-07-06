package io.github.ronjunevaldoz.tailwind.showcase.sections

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.ronjunevaldoz.tailwind.modifiers.bgBlue500
import io.github.ronjunevaldoz.tailwind.modifiers.gap4
import io.github.ronjunevaldoz.tailwind.modifiers.opacity100
import io.github.ronjunevaldoz.tailwind.modifiers.opacity25
import io.github.ronjunevaldoz.tailwind.modifiers.opacity50
import io.github.ronjunevaldoz.tailwind.modifiers.opacity75

/**
 * opacity25/50/75/100 — opacity*() must precede bg*() in the chain to take
 * effect (see Opacity.kt's KDoc).
 */
@Suppress("ktlint:standard:function-naming")
@Composable
fun OpacityShowcase() {
    ShowcaseSection(title = "Opacity — 25%, 50%, 75%, 100%") {
        Row(horizontalArrangement = gap4()) {
            listOf(Modifier.opacity25(), Modifier.opacity50(), Modifier.opacity75(), Modifier.opacity100())
                .forEach { opacity ->
                    Box(opacity.size(32.dp).bgBlue500())
                }
        }
    }
}
