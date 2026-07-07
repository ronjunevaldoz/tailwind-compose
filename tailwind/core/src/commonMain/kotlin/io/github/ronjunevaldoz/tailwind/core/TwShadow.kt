package io.github.ronjunevaldoz.tailwind.core

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Tailwind's `shadow-*` scale, approximated as Compose elevation (a single Dp
 * value driving Android's ambient/spot shadow model). CSS `box-shadow` supports
 * arbitrary offset/blur/spread/color per layer, which Compose's [androidx.compose.ui.draw.shadow]
 * has no equivalent for — this is a visual approximation of Tailwind's scale, not
 * a faithful reproduction. `shadow-inner` has no Compose primitive (no inset-shadow
 * support) and is intentionally not included.
 */
object TwShadow {
    val none: Dp = 0.dp
    val sm: Dp = 1.dp
    val base: Dp = 3.dp
    val md: Dp = 6.dp
    val lg: Dp = 10.dp
    val xl: Dp = 15.dp
    val xl2: Dp = 25.dp
}
