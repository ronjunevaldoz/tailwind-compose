package io.github.ronjunevaldoz.tailwind.style

import androidx.compose.foundation.style.ExperimentalFoundationStyleApi
import androidx.compose.foundation.style.Style
import androidx.compose.foundation.style.then
import androidx.compose.ui.unit.Dp

/**
 * Tailwind's `min-w-*`/`min-h-*`/`max-w-*`/`max-h-*` utilities via
 * [androidx.compose.foundation.style.MinSizeScope]/[androidx.compose.foundation.style.MaxSizeScope].
 * Split out from `tailwind-style`'s `widthStyle`/`heightStyle` (same underlying Tailwind
 * category, `w-*`/`h-*`) because `minWidth(value)`/`maxWidth(value)` compile fine against
 * 1.11.1 but don't actually enforce the constraint there -- confirmed by a real test run, not
 * just a compile check: a 10.dp width with `minWidthStyle(40.dp)` still measured 10.dp on
 * 1.11.1. They work correctly on 1.12.0-beta01, so they stay here until a stable Compose
 * Multiplatform release fixes that gap.
 */
@ExperimentalFoundationStyleApi
fun Style.minWidthStyle(value: Dp): Style = this.then(Style { minWidth(value) })

@ExperimentalFoundationStyleApi
fun Style.minHeightStyle(value: Dp): Style = this.then(Style { minHeight(value) })

@ExperimentalFoundationStyleApi
fun Style.maxWidthStyle(value: Dp): Style = this.then(Style { maxWidth(value) })

@ExperimentalFoundationStyleApi
fun Style.maxHeightStyle(value: Dp): Style = this.then(Style { maxHeight(value) })
