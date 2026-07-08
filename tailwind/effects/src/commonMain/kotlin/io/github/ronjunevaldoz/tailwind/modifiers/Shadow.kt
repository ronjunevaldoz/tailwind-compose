package io.github.ronjunevaldoz.tailwind.modifiers

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import io.github.ronjunevaldoz.tailwind.core.TwShadow

/**
 * Tailwind's `shadow-*` elevation utilities (see [TwShadow] for the approximation
 * caveat). Like [Modifier.clip]/[Modifier.background], these must precede the
 * modifiers whose bounds they should wrap (e.g. `Modifier.shadowLg().roundedLg().bgWhite()`).
 *
 * Pass the same [Shape] used for the accompanying `rounded*()`/`clip()` call as [shape] --
 * otherwise the shadow casts a rectangular halo under rounded content, visible as extra
 * shadow bleeding past the rounded corners (mirrors the `shape` param on [Modifier.border]).
 */
fun Modifier.shadowNone(shape: Shape = RectangleShape): Modifier = this.shadow(TwShadow.none, shape)

/** v4's `shadow-2xs` -- new in v4, smaller than [shadowXs]/the deprecated [shadowSm]. */
fun Modifier.shadowXs2(shape: Shape = RectangleShape): Modifier = this.shadow(TwShadow.xs2, shape)

/** v4's `shadow-xs`. Was `shadowSm()` in v3 (see [shadowSm]'s deprecation note). */
fun Modifier.shadowXs(shape: Shape = RectangleShape): Modifier = this.shadow(TwShadow.xs, shape)

@Deprecated("Tailwind v3 naming -- this is v4's shadow-xs.", ReplaceWith("this.shadowXs(shape)"))
fun Modifier.shadowSm(shape: Shape = RectangleShape): Modifier = this.shadow(TwShadow.sm, shape)

@Deprecated(
    "Tailwind v3's bare/DEFAULT shadow -- v4 calls this shadow-sm, but that name is " +
        "reserved by the deprecated shadowSm() (v3's different value) until it's removed.",
)
fun Modifier.shadow(shape: Shape = RectangleShape): Modifier = this.shadow(TwShadow.base, shape)

fun Modifier.shadowMd(shape: Shape = RectangleShape): Modifier = this.shadow(TwShadow.md, shape)

fun Modifier.shadowLg(shape: Shape = RectangleShape): Modifier = this.shadow(TwShadow.lg, shape)

fun Modifier.shadowXl(shape: Shape = RectangleShape): Modifier = this.shadow(TwShadow.xl, shape)

fun Modifier.shadowXl2(shape: Shape = RectangleShape): Modifier = this.shadow(TwShadow.xl2, shape)
