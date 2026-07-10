package io.github.ronjunevaldoz.tailwind.style

import androidx.compose.foundation.style.ExperimentalFoundationStyleApi
import androidx.compose.foundation.style.Style
import androidx.compose.foundation.style.then
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.unit.Dp
import io.github.ronjunevaldoz.tailwind.core.TwShadow

/**
 * Tailwind's `shadow-*` utilities. [androidx.compose.foundation.style.ShadowScope] has no
 * "elevation" concept the way [io.github.ronjunevaldoz.tailwind.modifiers.shadow] does (Android's
 * ambient/spot shadow model, a single [Dp] driving both blur and offset together) -- it only
 * offers `dropShadow`/`innerShadow`, CSS box-shadow's own model (independent radius/spread/
 * offset/color). [radius] here is used as the blur radius, not elevation -- a different but
 * comparably-weighted visual analogue, not a unit conversion of [io.github.ronjunevaldoz.tailwind
 * .core.TwShadow]'s existing elevation values.
 */
@ExperimentalFoundationStyleApi
fun Style.shadowStyle(
    radius: Dp,
    color: Color = Color.Black.copy(alpha = 0.25f),
): Style = this.then(Style { dropShadow(Shadow(radius = radius, color = color)) })

@ExperimentalFoundationStyleApi
fun Style.shadowStyleXs(): Style = shadowStyle(TwShadow.xs)

@ExperimentalFoundationStyleApi
fun Style.shadowStyleMd(): Style = shadowStyle(TwShadow.md)

@ExperimentalFoundationStyleApi
fun Style.shadowStyleLg(): Style = shadowStyle(TwShadow.lg)

@ExperimentalFoundationStyleApi
fun Style.shadowStyleXl(): Style = shadowStyle(TwShadow.xl)
