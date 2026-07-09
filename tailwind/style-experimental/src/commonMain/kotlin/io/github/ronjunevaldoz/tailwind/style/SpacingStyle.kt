package io.github.ronjunevaldoz.tailwind.style

import androidx.compose.foundation.style.ExperimentalFoundationStyleApi
import androidx.compose.foundation.style.Style
import androidx.compose.foundation.style.contentPadding
import androidx.compose.foundation.style.externalPadding
import androidx.compose.foundation.style.then
import androidx.compose.ui.unit.Dp
import io.github.ronjunevaldoz.tailwind.core.TwSpacing

/**
 * Tailwind's `p-*` padding utilities via
 * [androidx.compose.foundation.style.ContentPaddingScope.contentPadding] -- the space between a
 * [androidx.compose.foundation.style.BorderScope] border (if any) and content, matching CSS
 * `padding`/Compose's own `Modifier.padding`.
 */
@ExperimentalFoundationStyleApi
fun Style.paddingStyle(value: Dp): Style = this.then(Style { contentPadding(value) })

@ExperimentalFoundationStyleApi
fun Style.paddingStyle1(): Style = paddingStyle(TwSpacing.scale1)

@ExperimentalFoundationStyleApi
fun Style.paddingStyle2(): Style = paddingStyle(TwSpacing.scale2)

@ExperimentalFoundationStyleApi
fun Style.paddingStyle4(): Style = paddingStyle(TwSpacing.scale4)

@ExperimentalFoundationStyleApi
fun Style.paddingStyle8(): Style = paddingStyle(TwSpacing.scale8)

/**
 * Tailwind's `m-*` margin utilities via
 * [androidx.compose.foundation.style.ExternalPaddingScope.externalPadding] -- the space between
 * the component's own edge and its border (if any), matching CSS `margin`/Compose's own outer
 * `Modifier.padding` (applied before the border/background in a manual chain).
 */
@ExperimentalFoundationStyleApi
fun Style.marginStyle(value: Dp): Style = this.then(Style { externalPadding(value) })

@ExperimentalFoundationStyleApi
fun Style.marginStyle1(): Style = marginStyle(TwSpacing.scale1)

@ExperimentalFoundationStyleApi
fun Style.marginStyle2(): Style = marginStyle(TwSpacing.scale2)

@ExperimentalFoundationStyleApi
fun Style.marginStyle4(): Style = marginStyle(TwSpacing.scale4)

@ExperimentalFoundationStyleApi
fun Style.marginStyle8(): Style = marginStyle(TwSpacing.scale8)
