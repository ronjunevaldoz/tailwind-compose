package io.github.ronjunevaldoz.tailwind.modifiers

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle

/**
 * Whether the system is currently in dark mode — the same signal Tailwind's `dark:`
 * variant reacts to by default (the `media` dark-mode strategy). Thin wrapper over
 * [isSystemInDarkTheme] so call sites read `if (isTwDarkTheme())` instead of mixing
 * Compose Foundation calls into tailwind-compose usage.
 */
@Composable
fun isTwDarkTheme(): Boolean = isSystemInDarkTheme()

/**
 * Tailwind's `dark:` variant for [Modifier] chains — applies [block] only when
 * [isTwDarkTheme] is true, otherwise passes the chain through unchanged:
 *
 * ```
 * Modifier.bgWhite().twDark { bgSlate900() }
 * ```
 *
 * Chain [block] the same way you would any other `bg*()`/`rounded*()`/`opacity*()`
 * call — the "effect modifiers go first" ordering rule (see the README) still applies
 * to whatever `block` produces.
 */
@Composable
fun Modifier.twDark(block: Modifier.() -> Modifier): Modifier = if (isTwDarkTheme()) block() else this

/**
 * Tailwind's `dark:` variant for [TextStyle] chains — applies [block] only when
 * [isTwDarkTheme] is true, otherwise passes the style through unchanged:
 *
 * ```
 * TextStyle().textSlate900().twDark { textSlate50() }
 * ```
 */
@Composable
fun TextStyle.twDark(block: TextStyle.() -> TextStyle): TextStyle = if (isTwDarkTheme()) block() else this
