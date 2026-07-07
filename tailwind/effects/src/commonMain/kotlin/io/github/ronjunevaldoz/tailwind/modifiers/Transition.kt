package io.github.ronjunevaldoz.tailwind.modifiers

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.ui.Modifier
import io.github.ronjunevaldoz.tailwind.core.TwDuration
import io.github.ronjunevaldoz.tailwind.core.TwEasing

/**
 * Tailwind's `transition-all duration-*` combo — CSS animates any changed property
 * on an element automatically; Compose has no such general mechanism; the closest
 * single-`Modifier` equivalent is [Modifier.animateContentSize], which animates
 * *size* changes specifically. Animating other properties (color, position, opacity)
 * needs `animateXAsState` at the call site — use [TwDuration]/[TwEasing] tokens with
 * `tween()` there for the same Tailwind-matching timing.
 */
fun Modifier.transitionAllDuration75(): Modifier =
    this.animateContentSize(tween(TwDuration.D75, easing = TwEasing.easeInOut))

fun Modifier.transitionAllDuration150(): Modifier =
    this.animateContentSize(tween(TwDuration.D150, easing = TwEasing.easeInOut))

fun Modifier.transitionAllDuration300(): Modifier =
    this.animateContentSize(tween(TwDuration.D300, easing = TwEasing.easeInOut))

fun Modifier.transitionAllDuration500(): Modifier =
    this.animateContentSize(tween(TwDuration.D500, easing = TwEasing.easeInOut))
