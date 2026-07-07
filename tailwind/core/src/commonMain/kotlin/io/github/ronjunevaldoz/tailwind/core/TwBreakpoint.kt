package io.github.ronjunevaldoz.tailwind.core

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Tailwind's default `sm:`/`md:`/`lg:`/`xl:`/`2xl:` breakpoints, as exact dp equivalents of
 * Tailwind v4.3.2's `--breakpoint-*` values (40rem/48rem/64rem/80rem/96rem at the project's
 * 1rem == 16dp convention). These are `min-width` thresholds -- Tailwind's cascade is
 * mobile-first, so multiple breakpoints are simultaneously "active" at any width above `sm`.
 * See `twResponsive()` in tailwind-modifiers for how a single value is resolved from these.
 */
object TwBreakpoint {
    val sm: Dp = 640.dp
    val md: Dp = 768.dp
    val lg: Dp = 1024.dp
    val xl: Dp = 1280.dp
    val xl2: Dp = 1536.dp
}
