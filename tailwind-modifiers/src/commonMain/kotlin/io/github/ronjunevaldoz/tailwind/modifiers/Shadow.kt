package io.github.ronjunevaldoz.tailwind.modifiers

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import io.github.ronjunevaldoz.tailwind.core.TwShadow

/**
 * Tailwind's `shadow-*` elevation utilities (see [TwShadow] for the approximation
 * caveat). Like [Modifier.clip]/[Modifier.background], these must precede the
 * modifiers whose bounds they should wrap (e.g. `Modifier.shadowLg().roundedLg().bgWhite()`).
 */
fun Modifier.shadowNone(): Modifier = this.shadow(TwShadow.none)

fun Modifier.shadowSm(): Modifier = this.shadow(TwShadow.sm)

fun Modifier.shadow(): Modifier = this.shadow(TwShadow.base)

fun Modifier.shadowMd(): Modifier = this.shadow(TwShadow.md)

fun Modifier.shadowLg(): Modifier = this.shadow(TwShadow.lg)

fun Modifier.shadowXl(): Modifier = this.shadow(TwShadow.xl)

fun Modifier.shadowXl2(): Modifier = this.shadow(TwShadow.xl2)
