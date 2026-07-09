package io.github.ronjunevaldoz.tailwind.style

import androidx.compose.foundation.style.ExperimentalFoundationStyleApi
import androidx.compose.foundation.style.Style
import androidx.compose.foundation.style.then
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

/**
 * Tailwind's `bg-*` background-color utilities via
 * [androidx.compose.foundation.style.BackgroundScope.background]. Parameterized on [Color] rather
 * than one function per [io.github.ronjunevaldoz.tailwind.core.TwColors] entry (26 hues x 11
 * shades would mean ~286 near-identical one-line functions) -- call with a token directly:
 * `Style.bgStyle(TwColors.blue500)`.
 */
@ExperimentalFoundationStyleApi
fun Style.bgStyle(color: Color): Style = this.then(Style { background(color) })

/**
 * Tailwind's `bg-gradient-to-*` utilities via `BackgroundScope.background(Brush)` -- the same
 * overload [io.github.ronjunevaldoz.tailwind.modifiers.bgGradientToR] et al. build a [Brush] for
 * (`Brush.horizontalGradient`/`verticalGradient`), applied through the Style API directly rather
 * than a separate `Modifier.background(brush)` call.
 */
@ExperimentalFoundationStyleApi
fun Style.bgStyle(brush: Brush): Style = this.then(Style { background(brush) })

/**
 * Tailwind's `text-{color}` utilities via
 * [androidx.compose.foundation.style.ContentColorScope.contentColor] -- "primarily affecting
 * text color" and inherited by child text components per its own KDoc, the direct Style-API
 * analogue of setting a text color, not [androidx.compose.foundation.style.ForegroundScope]
 * (which overlays a color/brush on top of already-drawn content, meant for translucent tints).
 */
@ExperimentalFoundationStyleApi
fun Style.textColorStyle(color: Color): Style = this.then(Style { contentColor(color) })
