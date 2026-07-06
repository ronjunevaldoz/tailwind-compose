package io.github.ronjunevaldoz.tailwind.modifiers

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import io.github.ronjunevaldoz.tailwind.core.TwFontSize
import io.github.ronjunevaldoz.tailwind.core.TwFontWeight
import io.github.ronjunevaldoz.tailwind.core.TwLineHeight
import io.github.ronjunevaldoz.tailwind.core.TwTracking

/**
 * Tailwind's `text-*` font-size utilities. Each also sets the paired default
 * line-height, matching Tailwind's own behavior (e.g. `text-lg` sets both
 * `font-size` and `line-height`).
 */
fun TextStyle.textXs(): TextStyle = copy(fontSize = TwFontSize.xs, lineHeight = TwLineHeight.xs)

fun TextStyle.textSm(): TextStyle = copy(fontSize = TwFontSize.sm, lineHeight = TwLineHeight.sm)

fun TextStyle.textBase(): TextStyle = copy(fontSize = TwFontSize.base, lineHeight = TwLineHeight.base)

fun TextStyle.textLg(): TextStyle = copy(fontSize = TwFontSize.lg, lineHeight = TwLineHeight.lg)

fun TextStyle.textXl(): TextStyle = copy(fontSize = TwFontSize.xl, lineHeight = TwLineHeight.xl)

fun TextStyle.textXl2(): TextStyle = copy(fontSize = TwFontSize.xl2, lineHeight = TwLineHeight.xl2)

fun TextStyle.textXl3(): TextStyle = copy(fontSize = TwFontSize.xl3, lineHeight = TwLineHeight.xl3)

fun TextStyle.textXl4(): TextStyle = copy(fontSize = TwFontSize.xl4, lineHeight = TwLineHeight.xl4)

fun TextStyle.textXl5(): TextStyle = copy(fontSize = TwFontSize.xl5, lineHeight = TwLineHeight.xl5)

fun TextStyle.textXl6(): TextStyle = copy(fontSize = TwFontSize.xl6, lineHeight = TwLineHeight.xl6)

fun TextStyle.textXl7(): TextStyle = copy(fontSize = TwFontSize.xl7, lineHeight = TwLineHeight.xl7)

fun TextStyle.textXl8(): TextStyle = copy(fontSize = TwFontSize.xl8, lineHeight = TwLineHeight.xl8)

fun TextStyle.textXl9(): TextStyle = copy(fontSize = TwFontSize.xl9, lineHeight = TwLineHeight.xl9)

/** Tailwind's `font-*` weight utilities. */
fun TextStyle.fontThin(): TextStyle = copy(fontWeight = TwFontWeight.thin)

fun TextStyle.fontExtraLight(): TextStyle = copy(fontWeight = TwFontWeight.extraLight)

fun TextStyle.fontLight(): TextStyle = copy(fontWeight = TwFontWeight.light)

fun TextStyle.fontNormal(): TextStyle = copy(fontWeight = TwFontWeight.normal)

fun TextStyle.fontMedium(): TextStyle = copy(fontWeight = TwFontWeight.medium)

fun TextStyle.fontSemiBold(): TextStyle = copy(fontWeight = TwFontWeight.semiBold)

fun TextStyle.fontBold(): TextStyle = copy(fontWeight = TwFontWeight.bold)

fun TextStyle.fontExtraBold(): TextStyle = copy(fontWeight = TwFontWeight.extraBold)

fun TextStyle.fontBlack(): TextStyle = copy(fontWeight = TwFontWeight.black)

/** Tailwind's `tracking-*` letter-spacing utilities. */
fun TextStyle.trackingTighter(): TextStyle = copy(letterSpacing = TwTracking.tighter)

fun TextStyle.trackingTight(): TextStyle = copy(letterSpacing = TwTracking.tight)

fun TextStyle.trackingNormal(): TextStyle = copy(letterSpacing = TwTracking.normal)

fun TextStyle.trackingWide(): TextStyle = copy(letterSpacing = TwTracking.wide)

fun TextStyle.trackingWider(): TextStyle = copy(letterSpacing = TwTracking.wider)

fun TextStyle.trackingWidest(): TextStyle = copy(letterSpacing = TwTracking.widest)

/** Tailwind's `text-{left,center,right,justify}` alignment utilities. */
fun TextStyle.textAlignLeft(): TextStyle = copy(textAlign = TextAlign.Left)

fun TextStyle.textAlignCenter(): TextStyle = copy(textAlign = TextAlign.Center)

fun TextStyle.textAlignRight(): TextStyle = copy(textAlign = TextAlign.Right)

fun TextStyle.textAlignJustify(): TextStyle = copy(textAlign = TextAlign.Justify)
