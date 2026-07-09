package io.github.ronjunevaldoz.tailwind.style

import androidx.compose.foundation.style.ExperimentalFoundationStyleApi
import androidx.compose.foundation.style.Style
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
 * Apply via `Modifier.styleable(style = ringStyle(color))`, not as a plain `Modifier` chain
 * call -- `Style` objects are opaque descriptions applied through `styleable()`, per the
 * Style API's own design (see [Style]'s KDoc).
 */
@ExperimentalFoundationStyleApi
fun ringStyle(
    color: Color,
    width: Dp = 1.dp,
): Style =
    Style {
        dropShadow(Shadow(radius = 0.dp, spread = width, color = color))
    }

@ExperimentalFoundationStyleApi
fun ringStyle0(color: Color): Style = ringStyle(color, 0.dp)

@ExperimentalFoundationStyleApi
fun ringStyle2(color: Color): Style = ringStyle(color, 2.dp)

@ExperimentalFoundationStyleApi
fun ringStyle4(color: Color): Style = ringStyle(color, 4.dp)

@ExperimentalFoundationStyleApi
fun ringStyle8(color: Color): Style = ringStyle(color, 8.dp)
