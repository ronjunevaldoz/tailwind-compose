package io.github.ronjunevaldoz.tailwind.style

import androidx.compose.foundation.style.ExperimentalFoundationStyleApi
import androidx.compose.foundation.style.Style
import androidx.compose.foundation.style.then
import androidx.compose.ui.unit.Dp
import io.github.ronjunevaldoz.tailwind.core.TwSpacing

/** Tailwind's `w-*`/`h-*` utilities via [androidx.compose.foundation.style.SizeScope]. */
@ExperimentalFoundationStyleApi
fun Style.widthStyle(value: Dp): Style = this.then(Style { width(value) })

@ExperimentalFoundationStyleApi
fun Style.heightStyle(value: Dp): Style = this.then(Style { height(value) })

@ExperimentalFoundationStyleApi
fun Style.widthStyle4(): Style = widthStyle(TwSpacing.scale4)

@ExperimentalFoundationStyleApi
fun Style.heightStyle4(): Style = heightStyle(TwSpacing.scale4)

// min-w-*/min-h-*/max-w-*/max-h-* (MinSizeScope/MaxSizeScope) live in
// tailwind-style-experimental instead of here: verified via a real test run (not just a
// compile check) that minWidth(value)/maxWidth(value) compile fine against 1.11.1 but don't
// actually enforce the constraint -- a 10.dp width with minWidthStyle(40.dp) still measured
// 10.dp. That's a genuine behavioral gap in 1.11.1's Style API, not a missing-symbol issue,
// so these stay on 1.12.0-beta01 until 1.11.1 (or whatever stable line ships next) fixes it.
