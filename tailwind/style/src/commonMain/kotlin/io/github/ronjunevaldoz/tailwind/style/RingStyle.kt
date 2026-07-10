package io.github.ronjunevaldoz.tailwind.style

import androidx.compose.foundation.style.ExperimentalFoundationStyleApi
import androidx.compose.foundation.style.Style
import androidx.compose.foundation.style.then
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
 * Every custom [Style] in this module is an *extension on [Style] itself*, not a `Modifier`
 * extension -- `Style` carries a `companion object : Style` (the empty/no-op default), so
 * `Style.ringStyle(color)` reads as "start from the Style entry point and extend it with ring
 * behavior," and chains naturally onto an existing one: `someStyle.ringStyle(color)` folds ring
 * in via [Style.then] rather than requiring callers to combine two separate [Style] values by
 * hand. Apply the result with the Style API's own `Modifier.styleable(style = ...)` --
 * deliberately no `Modifier.ring(...)` shortcut here: a `Modifier` extension that returns a
 * fully-applied `Modifier` can't be `then`-composed with another [Style] the way two [Style]
 * values can, so it would quietly steer callers away from the one thing a [Style] does that a
 * `Modifier` chain doesn't.
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
