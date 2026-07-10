package io.github.ronjunevaldoz.tailwind.style

import androidx.compose.foundation.style.ExperimentalFoundationStyleApi
import androidx.compose.foundation.style.Style
import androidx.compose.foundation.style.then
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit
import io.github.ronjunevaldoz.tailwind.core.TwFontFamily
import io.github.ronjunevaldoz.tailwind.core.TwFontSize
import io.github.ronjunevaldoz.tailwind.core.TwFontWeight
import io.github.ronjunevaldoz.tailwind.core.TwLineHeight
import io.github.ronjunevaldoz.tailwind.core.TwTracking

/**
 * Tailwind's `text-*` font-size utilities via
 * [androidx.compose.foundation.style.FontSizeScope.fontSize] -- unlike
 * [io.github.ronjunevaldoz.tailwind.modifiers.textXs]'s `TextStyle`-returning function family
 * (this project's own, unrelated "Style API" naming -- see `docs/tailwind-coverage-matrix.md`),
 * these apply directly to a styleable region via the real Style API, not to a `Text`
 * composable's own `style:` parameter.
 */
@ExperimentalFoundationStyleApi
fun Style.fontSizeStyle(value: TextUnit): Style = this.then(Style { fontSize(value) })

@ExperimentalFoundationStyleApi
fun Style.fontSizeStyleSm(): Style = fontSizeStyle(TwFontSize.sm)

@ExperimentalFoundationStyleApi
fun Style.fontSizeStyleBase(): Style = fontSizeStyle(TwFontSize.base)

@ExperimentalFoundationStyleApi
fun Style.fontSizeStyleLg(): Style = fontSizeStyle(TwFontSize.lg)

@ExperimentalFoundationStyleApi
fun Style.fontSizeStyleXl(): Style = fontSizeStyle(TwFontSize.xl)

/** Tailwind's `leading-*` line-height utilities via [androidx.compose.foundation.style.LineHeightScope]. */
@ExperimentalFoundationStyleApi
fun Style.lineHeightStyle(value: TextUnit): Style = this.then(Style { lineHeight(value) })

@ExperimentalFoundationStyleApi
fun Style.lineHeightStyleBase(): Style = lineHeightStyle(TwLineHeight.base)

/** Tailwind's `font-*` weight utilities via [androidx.compose.foundation.style.FontWeightScope]. */
@ExperimentalFoundationStyleApi
fun Style.fontWeightStyle(value: FontWeight): Style = this.then(Style { fontWeight(value) })

@ExperimentalFoundationStyleApi
fun Style.fontWeightStyleNormal(): Style = fontWeightStyle(TwFontWeight.normal)

@ExperimentalFoundationStyleApi
fun Style.fontWeightStyleMedium(): Style = fontWeightStyle(TwFontWeight.medium)

@ExperimentalFoundationStyleApi
fun Style.fontWeightStyleBold(): Style = fontWeightStyle(TwFontWeight.bold)

/** Tailwind's `tracking-*` letter-spacing utilities via [androidx.compose.foundation.style.LetterSpacingScope]. */
@ExperimentalFoundationStyleApi
fun Style.letterSpacingStyle(value: TextUnit): Style = this.then(Style { letterSpacing(value) })

@ExperimentalFoundationStyleApi
fun Style.letterSpacingStyleTight(): Style = letterSpacingStyle(TwTracking.tight)

@ExperimentalFoundationStyleApi
fun Style.letterSpacingStyleWide(): Style = letterSpacingStyle(TwTracking.wide)

/** Tailwind's `text-{align}` utilities via [androidx.compose.foundation.style.TextAlignScope]. */
@ExperimentalFoundationStyleApi
fun Style.textAlignStyle(value: TextAlign): Style = this.then(Style { textAlign(value) })

/** Tailwind's `underline`/`line-through` utilities via [androidx.compose.foundation.style.TextDecorationScope]. */
@ExperimentalFoundationStyleApi
fun Style.textDecorationStyle(value: TextDecoration): Style = this.then(Style { textDecoration(value) })

/** Tailwind's `font-sans`/`font-serif`/`font-mono` utilities via [androidx.compose.foundation.style.FontFamilyScope]. */
@ExperimentalFoundationStyleApi
fun Style.fontFamilyStyle(value: FontFamily): Style = this.then(Style { fontFamily(value) })

@ExperimentalFoundationStyleApi
fun Style.fontFamilyStyleSans(): Style = fontFamilyStyle(TwFontFamily.sans)

@ExperimentalFoundationStyleApi
fun Style.fontFamilyStyleSerif(): Style = fontFamilyStyle(TwFontFamily.serif)

@ExperimentalFoundationStyleApi
fun Style.fontFamilyStyleMono(): Style = fontFamilyStyle(TwFontFamily.mono)
