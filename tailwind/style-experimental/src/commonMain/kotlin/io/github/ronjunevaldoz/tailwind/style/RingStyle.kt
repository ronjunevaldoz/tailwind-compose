package io.github.ronjunevaldoz.tailwind.style

import androidx.compose.foundation.style.ExperimentalFoundationStyleApi
import androidx.compose.foundation.style.Style
import androidx.compose.foundation.style.then
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Tailwind's `ring-*` utilities (CSS `box-shadow: 0 0 0 <width> <color>`), built on the real
 * Compose [Style] API rather than [io.github.ronjunevaldoz.tailwind.modifiers.ring]'s
 * hand-rolled `drawWithCache`/negative-inset technique.
 *
 * [androidx.compose.foundation.style.ShadowScope.dropShadow] takes a [Shadow] with a `spread`
 * parameter -- CSS box-shadow's actual spread radius -- which is a more faithful, first-party
 * way to express a ring than expanding and stroking a shape outline by hand: `radius = 0.dp`
 * (no blur), `spread = width` (grows the shadow geometry outward by exactly [width], matching
 * Tailwind's `ring-*` scale), `offset = DpOffset.Zero` (centered, not cast to one side).
 *
 * Every custom [Style] in this module is an *extension on [Style] itself*, not a bare top-level
 * function -- `Style` carries a `companion object : Style` (the empty/no-op default), so
 * `Style.ringStyle(color)` reads as "start from the Style entry point and extend it with ring
 * behavior," and chains naturally onto an existing one: `someStyle.ringStyle(color)` folds ring
 * in via [Style.then] rather than requiring callers to combine two separate [Style] values by
 * hand. `Modifier.ring(...)` (below) is the direct-chain convenience form for the common case
 * with no further composition -- `Modifier.size(40.dp).ring(TwColors.blue500)`, same call shape
 * as every other tailwind-compose utility (including
 * [io.github.ronjunevaldoz.tailwind.modifiers.ring] in `tailwind-effects` -- a different
 * package, so no clash, but deliberately the same name: pick whichever module you've pulled in).
 */
@ExperimentalFoundationStyleApi
fun Style.ringStyle(
    color: Color,
    width: Dp = 1.dp,
): Style = this.then(Style { dropShadow(Shadow(radius = 0.dp, spread = width, color = color)) })

@ExperimentalFoundationStyleApi
fun Style.ringStyle0(color: Color): Style = ringStyle(color, 0.dp)

@ExperimentalFoundationStyleApi
fun Style.ringStyle2(color: Color): Style = ringStyle(color, 2.dp)

@ExperimentalFoundationStyleApi
fun Style.ringStyle4(color: Color): Style = ringStyle(color, 4.dp)

@ExperimentalFoundationStyleApi
fun Style.ringStyle8(color: Color): Style = ringStyle(color, 8.dp)

@ExperimentalFoundationStyleApi
fun Modifier.ring(
    color: Color,
    width: Dp = 1.dp,
): Modifier = this.style(Style.ringStyle(color, width))

@ExperimentalFoundationStyleApi
fun Modifier.ring0(color: Color): Modifier = this.style(Style.ringStyle0(color))

@ExperimentalFoundationStyleApi
fun Modifier.ring2(color: Color): Modifier = this.style(Style.ringStyle2(color))

@ExperimentalFoundationStyleApi
fun Modifier.ring4(color: Color): Modifier = this.style(Style.ringStyle4(color))

@ExperimentalFoundationStyleApi
fun Modifier.ring8(color: Color): Modifier = this.style(Style.ringStyle8(color))
