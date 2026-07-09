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

/** Tailwind's `min-w-*`/`min-h-*` utilities via [androidx.compose.foundation.style.MinSizeScope]. */
@ExperimentalFoundationStyleApi
fun Style.minWidthStyle(value: Dp): Style = this.then(Style { minWidth(value) })

@ExperimentalFoundationStyleApi
fun Style.minHeightStyle(value: Dp): Style = this.then(Style { minHeight(value) })

/** Tailwind's `max-w-*`/`max-h-*` utilities via [androidx.compose.foundation.style.MaxSizeScope]. */
@ExperimentalFoundationStyleApi
fun Style.maxWidthStyle(value: Dp): Style = this.then(Style { maxWidth(value) })

@ExperimentalFoundationStyleApi
fun Style.maxHeightStyle(value: Dp): Style = this.then(Style { maxHeight(value) })
