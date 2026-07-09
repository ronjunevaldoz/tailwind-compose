package io.github.ronjunevaldoz.tailwind.style

import androidx.compose.foundation.style.ExperimentalFoundationStyleApi
import androidx.compose.foundation.style.Style
import androidx.compose.foundation.style.then

/**
 * Tailwind's `z-*` stacking-order utilities via
 * [androidx.compose.foundation.style.ZIndexScope.zIndex] -- no `tailwind-effects` Modifier
 * equivalent exists yet (it was never ported there), so this is the first `z-*` implementation
 * anywhere in tailwind-compose, not a Style-API port of an existing one.
 */
@ExperimentalFoundationStyleApi
fun Style.zIndexStyle(value: Float): Style = this.then(Style { zIndex(value) })

@ExperimentalFoundationStyleApi
fun Style.zIndexStyle0(): Style = zIndexStyle(0f)

@ExperimentalFoundationStyleApi
fun Style.zIndexStyle10(): Style = zIndexStyle(10f)

@ExperimentalFoundationStyleApi
fun Style.zIndexStyle50(): Style = zIndexStyle(50f)
