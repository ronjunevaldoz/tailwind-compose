package io.github.ronjunevaldoz.tailwind.core

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/** Tailwind's default `rounded-*` corner-radius scale. */
object TwRadius {
    val none: Dp = 0.dp
    val sm: Dp = 2.dp
    val base: Dp = 4.dp
    val md: Dp = 6.dp
    val lg: Dp = 8.dp
    val xl: Dp = 12.dp
    val xl2: Dp = 16.dp
    val xl3: Dp = 24.dp

    /** Large enough to fully round any realistic mobile component. */
    val full: Dp = 9999.dp
}
