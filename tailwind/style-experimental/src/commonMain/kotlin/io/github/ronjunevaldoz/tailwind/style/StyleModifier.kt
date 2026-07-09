package io.github.ronjunevaldoz.tailwind.style

import androidx.compose.foundation.style.ExperimentalFoundationStyleApi
import androidx.compose.foundation.style.Style
import androidx.compose.foundation.style.StyleState
import androidx.compose.foundation.style.styleable
import androidx.compose.ui.Modifier

/**
 * Applies any [Style] -- this module's own [io.github.ronjunevaldoz.tailwind.style.ringStyle] or
 * a future custom one -- directly in a `Modifier` chain, the same way every other
 * tailwind-compose utility chains (`Modifier.border4(color)`, `Modifier.shadowLg()`, ...),
 * instead of the Style API's own `Modifier.styleable(style = someStyle)` call shape.
 *
 * This is the one place that talks to [androidx.compose.foundation.style.styleable] directly --
 * every custom style in this module follows the same two-layer shape (see `RingStyle.kt` for a
 * worked example):
 * 1. An extension **on [Style] itself**, not a bare top-level function -- `Style` carries a
 *    `companion object : Style` (the empty/no-op default), so `Style.xxxStyle(...)` reads as
 *    "start from the Style entry point," and composes onto an existing style via [Style.then]:
 *    `someStyle.xxxStyle(...)`.
 * 2. A `Modifier.xxx(...)` convenience extension built on this [style] function, for the common
 *    case with no further composition, so nothing outside this module needs to import [Style]
 *    or [StyleState] just to use one.
 */
@ExperimentalFoundationStyleApi
fun Modifier.style(
    style: Style,
    styleState: StyleState? = null,
): Modifier = this.styleable(styleState, style)
