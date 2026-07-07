package io.github.ronjunevaldoz.tailwind

/**
 * Marker object for the `tailwind-compose` facade module. This module carries no
 * utility code of its own — it depends on `tailwind-core` (design tokens) and
 * `tailwind-layout`/`tailwind-color`/`tailwind-typography`/`tailwind-effects`
 * (`Modifier`/`TextStyle` extensions, split by utility category) via `api()`, so a
 * single dependency on this artifact gives consumers everything.
 */
object TailwindCompose
