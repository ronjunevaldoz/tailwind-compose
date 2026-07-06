package io.github.ronjunevaldoz.tailwind.modifiers

import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.github.ronjunevaldoz.tailwind.core.TwRadius

/**
 * Tailwind's `border-*` width utilities. Compose has no separate border-color
 * primitive, so each function takes the color directly (unlike CSS, which
 * composes `border-width` and `border-{color}` independently).
 */
fun Modifier.border0(color: Color): Modifier = this.border(0.dp, color)

fun Modifier.border(color: Color): Modifier = this.border(1.dp, color)

fun Modifier.border2(color: Color): Modifier = this.border(2.dp, color)

fun Modifier.border4(color: Color): Modifier = this.border(4.dp, color)

fun Modifier.border8(color: Color): Modifier = this.border(8.dp, color)

/** Tailwind's `rounded-*` corner-radius utilities. */
fun Modifier.roundedNone(): Modifier = this.clip(RoundedCornerShape(TwRadius.none))

fun Modifier.roundedSm(): Modifier = this.clip(RoundedCornerShape(TwRadius.sm))

fun Modifier.rounded(): Modifier = this.clip(RoundedCornerShape(TwRadius.base))

fun Modifier.roundedMd(): Modifier = this.clip(RoundedCornerShape(TwRadius.md))

fun Modifier.roundedLg(): Modifier = this.clip(RoundedCornerShape(TwRadius.lg))

fun Modifier.roundedXl(): Modifier = this.clip(RoundedCornerShape(TwRadius.xl))

fun Modifier.roundedXl2(): Modifier = this.clip(RoundedCornerShape(TwRadius.xl2))

fun Modifier.roundedXl3(): Modifier = this.clip(RoundedCornerShape(TwRadius.xl3))

fun Modifier.roundedFull(): Modifier = this.clip(RoundedCornerShape(TwRadius.full))
