package io.github.ronjunevaldoz.tailwind.modifiers

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

/**
 * Tailwind's `bg-gradient-to-*` direction utilities, producing a [Brush] for use
 * with Compose's existing `Modifier.background(brush: Brush)` overload — unlike
 * every other utility in this library, a gradient inherently needs multiple color
 * inputs, so these take a color list rather than being zero-arg calls (`Color` is a
 * Kotlin value class, which the compiler prohibits as a vararg element type):
 *
 * ```
 * Modifier.background(bgGradientToR(listOf(TwColors.blue500, TwColors.purple500)))
 * ```
 *
 * Only the four cardinal directions are included. Tailwind's diagonal corner
 * variants (`to-tr`/`to-br`/`to-bl`/`to-tl`) would need [Brush.linearGradient]'s
 * explicit `start`/`end` offsets rather than a direction preset, deferred to keep
 * this file's scope to the straightforward cases. Tailwind's `from-*`/`via-*`/`to-*`
 * stop-color utilities aren't separate calls here for the same reason — pass the
 * full color list directly instead.
 */
fun bgGradientToR(colors: List<Color>): Brush = Brush.horizontalGradient(colors)

fun bgGradientToL(colors: List<Color>): Brush = Brush.horizontalGradient(colors.reversed())

fun bgGradientToB(colors: List<Color>): Brush = Brush.verticalGradient(colors)

fun bgGradientToT(colors: List<Color>): Brush = Brush.verticalGradient(colors.reversed())
