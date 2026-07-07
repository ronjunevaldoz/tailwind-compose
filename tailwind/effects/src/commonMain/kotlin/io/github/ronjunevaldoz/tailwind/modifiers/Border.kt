package io.github.ronjunevaldoz.tailwind.modifiers

import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import io.github.ronjunevaldoz.tailwind.core.TwRadius

/**
 * Tailwind's `border-*` width utilities. Compose has no separate border-color
 * primitive, so each function takes the color directly (unlike CSS, which
 * composes `border-width` and `border-{color}` independently).
 *
 * [shape] defaults to [RectangleShape], matching Tailwind's sharp corners by default.
 * When pairing a border with a `rounded*()` clip, a preceding `clip()` in the same
 * modifier chain does visually round the border too — Compose's `clip()` clips
 * everything in that layer, not just modifiers after it — but that only holds as
 * long as no intervening modifier (e.g. `graphicsLayer`) starts a new layer. Passing
 * the same [Shape] explicitly here is the robust way to guarantee the border always
 * matches, independent of chain order or layer boundaries:
 *
 * ```
 * val shape = RoundedCornerShape(TwRadius.lg)
 * Modifier.clip(shape).border4(color, shape)
 * ```
 */
fun Modifier.border0(
    color: Color,
    shape: Shape = RectangleShape,
): Modifier = this.border(0.dp, color, shape)

fun Modifier.border(
    color: Color,
    shape: Shape = RectangleShape,
): Modifier = this.border(1.dp, color, shape)

fun Modifier.border2(
    color: Color,
    shape: Shape = RectangleShape,
): Modifier = this.border(2.dp, color, shape)

fun Modifier.border4(
    color: Color,
    shape: Shape = RectangleShape,
): Modifier = this.border(4.dp, color, shape)

fun Modifier.border8(
    color: Color,
    shape: Shape = RectangleShape,
): Modifier = this.border(8.dp, color, shape)

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
