package io.github.ronjunevaldoz.tailwind.modifiers

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.ui.Modifier

/** Tailwind's `aspect-*` utilities. */
fun Modifier.aspectSquare(): Modifier = this.aspectRatio(1f)

fun Modifier.aspectVideo(): Modifier = this.aspectRatio(16f / 9f)

/**
 * Tailwind's `aspect-auto` resets to the default (content-driven) sizing. There is
 * no Compose modifier to "undo" a prior [Modifier.aspectRatio], so this is a no-op
 * provided only for API discoverability/parity with Tailwind's utility class.
 */
fun Modifier.aspectAuto(): Modifier = this
