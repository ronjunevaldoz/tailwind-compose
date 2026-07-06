package io.github.ronjunevaldoz.tailwind.core

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.LinearEasing

/** Tailwind's `duration-*` scale, in milliseconds. */
object TwDuration {
    const val D75 = 75
    const val D100 = 100
    const val D150 = 150
    const val D200 = 200
    const val D300 = 300
    const val D500 = 500
    const val D700 = 700
    const val D1000 = 1000
}

/**
 * Tailwind's `ease-*` timing functions, as the exact same cubic-bezier curves CSS
 * uses (`ease-in` = `cubic-bezier(0.4, 0, 1, 1)`, etc.), so animations built with
 * these match Tailwind's visual timing rather than Compose's own defaults.
 */
@Suppress("MagicNumber")
object TwEasing {
    val linear: Easing = LinearEasing
    val easeIn: Easing = CubicBezierEasing(0.4f, 0f, 1f, 1f)
    val easeOut: Easing = CubicBezierEasing(0f, 0f, 0.2f, 1f)
    val easeInOut: Easing = CubicBezierEasing(0.4f, 0f, 0.2f, 1f)
}
