package io.github.ronjunevaldoz.tailwind.modifiers

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha

/**
 * Tailwind's `opacity-*` utilities, in 5% steps from 0 to 100.
 *
 * Like [androidx.compose.ui.draw.clip], this must come *before* background/border
 * modifiers in the chain to affect them, e.g. `Modifier.opacity50().background(color)`
 * — not `Modifier.background(color).opacity50()`, which leaves the background opaque.
 */
fun Modifier.opacity0(): Modifier = this.alpha(0.00f)

fun Modifier.opacity5(): Modifier = this.alpha(0.05f)

fun Modifier.opacity10(): Modifier = this.alpha(0.10f)

fun Modifier.opacity15(): Modifier = this.alpha(0.15f)

fun Modifier.opacity20(): Modifier = this.alpha(0.20f)

fun Modifier.opacity25(): Modifier = this.alpha(0.25f)

fun Modifier.opacity30(): Modifier = this.alpha(0.30f)

fun Modifier.opacity35(): Modifier = this.alpha(0.35f)

fun Modifier.opacity40(): Modifier = this.alpha(0.40f)

fun Modifier.opacity45(): Modifier = this.alpha(0.45f)

fun Modifier.opacity50(): Modifier = this.alpha(0.50f)

fun Modifier.opacity55(): Modifier = this.alpha(0.55f)

fun Modifier.opacity60(): Modifier = this.alpha(0.60f)

fun Modifier.opacity65(): Modifier = this.alpha(0.65f)

fun Modifier.opacity70(): Modifier = this.alpha(0.70f)

fun Modifier.opacity75(): Modifier = this.alpha(0.75f)

fun Modifier.opacity80(): Modifier = this.alpha(0.80f)

fun Modifier.opacity85(): Modifier = this.alpha(0.85f)

fun Modifier.opacity90(): Modifier = this.alpha(0.90f)

fun Modifier.opacity95(): Modifier = this.alpha(0.95f)

fun Modifier.opacity100(): Modifier = this.alpha(1.00f)
