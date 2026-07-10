package io.github.ronjunevaldoz.tailwind.style

import androidx.compose.foundation.style.ExperimentalFoundationStyleApi
import androidx.compose.foundation.style.Style
import androidx.compose.foundation.style.then
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.github.ronjunevaldoz.tailwind.core.TwRadius

/**
 * Tailwind's `border-*` width utilities via [androidx.compose.foundation.style.BorderScope]
 * (`borderWidth`/`borderColor`) -- unlike [io.github.ronjunevaldoz.tailwind.modifiers.border]'s
 * `shape` parameter, `BorderScope`'s border is always clipped to whatever
 * [androidx.compose.foundation.style.ShapeScope.shape] the same [Style] sets (see [roundedStyle]
 * below), so there's no separate shape argument to thread through here.
 */
@ExperimentalFoundationStyleApi
fun Style.borderStyle(
    color: Color,
    width: Dp = 1.dp,
): Style =
    this.then(
        Style {
            borderWidth(width)
            borderColor(color)
        },
    )

@ExperimentalFoundationStyleApi
fun Style.borderStyle0(color: Color): Style = borderStyle(color, 0.dp)

@ExperimentalFoundationStyleApi
fun Style.borderStyle2(color: Color): Style = borderStyle(color, 2.dp)

@ExperimentalFoundationStyleApi
fun Style.borderStyle4(color: Color): Style = borderStyle(color, 4.dp)

@ExperimentalFoundationStyleApi
fun Style.borderStyle8(color: Color): Style = borderStyle(color, 8.dp)

/**
 * Tailwind's `rounded-*` corner-radius utilities via
 * [androidx.compose.foundation.style.ShapeScope.shape] -- this shape also clips
 * [androidx.compose.foundation.style.BackgroundScope.background] and [borderStyle] in the same
 * [Style], per `ShapeScope.shape`'s own KDoc, so setting it once here covers all three.
 */
@ExperimentalFoundationStyleApi
fun Style.roundedStyle(radius: Dp): Style = this.then(Style { shape(RoundedCornerShape(radius)) })

@ExperimentalFoundationStyleApi
fun Style.roundedStyleNone(): Style = roundedStyle(TwRadius.none)

@ExperimentalFoundationStyleApi
fun Style.roundedStyleXs(): Style = roundedStyle(TwRadius.xs)

@ExperimentalFoundationStyleApi
fun Style.roundedStyleMd(): Style = roundedStyle(TwRadius.md)

@ExperimentalFoundationStyleApi
fun Style.roundedStyleLg(): Style = roundedStyle(TwRadius.lg)

@ExperimentalFoundationStyleApi
fun Style.roundedStyleXl(): Style = roundedStyle(TwRadius.xl)

@ExperimentalFoundationStyleApi
fun Style.roundedStyleFull(): Style = roundedStyle(TwRadius.full)
