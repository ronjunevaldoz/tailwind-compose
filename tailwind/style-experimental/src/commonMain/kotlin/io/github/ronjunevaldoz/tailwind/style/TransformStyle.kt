package io.github.ronjunevaldoz.tailwind.style

import androidx.compose.foundation.style.ExperimentalFoundationStyleApi
import androidx.compose.foundation.style.Style
import androidx.compose.foundation.style.then
import androidx.compose.ui.unit.Dp
import io.github.ronjunevaldoz.tailwind.core.TwSpacing

/**
 * Tailwind's `scale-*` uniform-scale utility via
 * [androidx.compose.foundation.style.ScaleScope.scaleX]/`scaleY`. Percentage-based, matching
 * [io.github.ronjunevaldoz.tailwind.modifiers.scale100] et al.'s convention (`1.5f` == `scale-150`)
 * rather than the 0..1 fraction [androidx.compose.foundation.style.AlphaScope.alpha] uses.
 */
@ExperimentalFoundationStyleApi
fun Style.scaleStyle(value: Float): Style =
    this.then(
        Style {
            scaleX(value)
            scaleY(value)
        },
    )

@ExperimentalFoundationStyleApi
fun Style.scaleStyle50(): Style = scaleStyle(0.50f)

@ExperimentalFoundationStyleApi
fun Style.scaleStyle100(): Style = scaleStyle(1.00f)

@ExperimentalFoundationStyleApi
fun Style.scaleStyle150(): Style = scaleStyle(1.50f)

/**
 * Tailwind's `rotate-x-*`/`rotate-y-*`/`rotate-z-*` 3D rotation utilities via
 * [androidx.compose.foundation.style.RotationScope]. Representative 45/90/180-degree presets per
 * axis, matching [io.github.ronjunevaldoz.tailwind.modifiers.rotateX45] et al.'s "representative
 * subset, not the full ~54-combination scale" precedent.
 */
@ExperimentalFoundationStyleApi
fun Style.rotateXStyle(degrees: Float): Style = this.then(Style { rotationX(degrees) })

@ExperimentalFoundationStyleApi
fun Style.rotateYStyle(degrees: Float): Style = this.then(Style { rotationY(degrees) })

@ExperimentalFoundationStyleApi
fun Style.rotateZStyle(degrees: Float): Style = this.then(Style { rotationZ(degrees) })

/**
 * Tailwind's `translate-x-*`/`translate-y-*` utilities via
 * [androidx.compose.foundation.style.TranslationScope] -- backed by the same [TwSpacing] scale as
 * padding/margin/gap, matching [io.github.ronjunevaldoz.tailwind.modifiers.translateX1] et al.'s
 * precedent (Tailwind's own `translate-*` resolves against the `--spacing` theme scale). Takes a
 * [Dp] rather than a raw pixel [Float] like the underlying `TranslationScope` does -- translated
 * to px at the call site via [Dp.value] is a common enough mismatch to hide here.
 */
@ExperimentalFoundationStyleApi
fun Style.translateXStyle(value: Dp): Style = this.then(Style { translationX(value.value) })

@ExperimentalFoundationStyleApi
fun Style.translateYStyle(value: Dp): Style = this.then(Style { translationY(value.value) })

@ExperimentalFoundationStyleApi
fun Style.translateXStyle4(): Style = translateXStyle(TwSpacing.scale4)

@ExperimentalFoundationStyleApi
fun Style.translateYStyle4(): Style = translateYStyle(TwSpacing.scale4)
