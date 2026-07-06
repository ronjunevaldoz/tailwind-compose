package io.github.ronjunevaldoz.tailwind

/**
 * Marker object for the `tailwind-compose` facade module. This module carries no
 * utility code of its own — it depends on `tailwind-core` (design tokens) and
 * `tailwind-modifiers` (`Modifier`/`TextStyle` extensions) via `api()`, so a single
 * dependency on this artifact gives consumers everything.
 */
object TailwindCompose
