package io.github.ronjunevaldoz.tailwind.modifiers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.Alignment

/**
 * Tailwind's `justify-*` (main-axis) and `items-*` (cross-axis) flex alignment
 * utilities. CSS decouples these names from the physical axis — `justify-center`
 * always means "center on the main axis" whether `flex-row` or `flex-col`. Compose's
 * `Arrangement`/`Alignment` types are axis-specific, so the unsuffixed name is the
 * `Row`-oriented case (Tailwind's default `flex-row`) and the `*Vertical`/`*Horizontal`
 * suffix picks the `Column`-oriented equivalent.
 *
 * `items-baseline` and `items-stretch` have no direct Compose `Alignment` equivalent
 * (baseline alignment is a per-child `Modifier.alignByBaseline()`; stretch is
 * Compose's constraint-driven default, not an alignment value) and are intentionally
 * not included.
 */
fun justifyStart(): Arrangement.Horizontal = Arrangement.Start

fun justifyCenter(): Arrangement.Horizontal = Arrangement.Center

fun justifyEnd(): Arrangement.Horizontal = Arrangement.End

fun justifyBetween(): Arrangement.Horizontal = Arrangement.SpaceBetween

fun justifyAround(): Arrangement.Horizontal = Arrangement.SpaceAround

fun justifyEvenly(): Arrangement.Horizontal = Arrangement.SpaceEvenly

/** Column-oriented main-axis equivalents of the `justify*()` functions above. */
fun justifyStartVertical(): Arrangement.Vertical = Arrangement.Top

fun justifyCenterVertical(): Arrangement.Vertical = Arrangement.Center

fun justifyEndVertical(): Arrangement.Vertical = Arrangement.Bottom

fun justifyBetweenVertical(): Arrangement.Vertical = Arrangement.SpaceBetween

fun justifyAroundVertical(): Arrangement.Vertical = Arrangement.SpaceAround

fun justifyEvenlyVertical(): Arrangement.Vertical = Arrangement.SpaceEvenly

/** `Row`-oriented cross-axis alignment (`Row`'s `verticalAlignment`). */
fun itemsStart(): Alignment.Vertical = Alignment.Top

fun itemsCenter(): Alignment.Vertical = Alignment.CenterVertically

fun itemsEnd(): Alignment.Vertical = Alignment.Bottom

/** Column-oriented cross-axis alignment (`Column`'s `horizontalAlignment`). */
fun itemsStartHorizontal(): Alignment.Horizontal = Alignment.Start

fun itemsCenterHorizontal(): Alignment.Horizontal = Alignment.CenterHorizontally

fun itemsEndHorizontal(): Alignment.Horizontal = Alignment.End
