package io.github.ronjunevaldoz.tailwind.style

import androidx.compose.foundation.style.ExperimentalFoundationStyleApi
import androidx.compose.foundation.style.Style
import androidx.compose.foundation.style.then

/** Tailwind's `opacity-*` utilities via [androidx.compose.foundation.style.AlphaScope.alpha]. */
@ExperimentalFoundationStyleApi
fun Style.opacityStyle(value: Float): Style = this.then(Style { alpha(value) })

@ExperimentalFoundationStyleApi
fun Style.opacityStyle0(): Style = opacityStyle(0.00f)

@ExperimentalFoundationStyleApi
fun Style.opacityStyle50(): Style = opacityStyle(0.50f)

@ExperimentalFoundationStyleApi
fun Style.opacityStyle100(): Style = opacityStyle(1.00f)
