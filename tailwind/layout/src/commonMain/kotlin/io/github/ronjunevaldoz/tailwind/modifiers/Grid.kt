package io.github.ronjunevaldoz.tailwind.modifiers

import androidx.compose.foundation.lazy.grid.GridCells

/**
 * Tailwind's `grid-cols-*` utilities, producing a [GridCells] for use with
 * `LazyVerticalGrid(columns = gridCols3()) { ... }`. Compose's grid model
 * (`LazyVerticalGrid`/`LazyHorizontalGrid`) is a scrolling, item-based grid rather
 * than CSS Grid's arbitrary track-sizing/placement system, so this covers the
 * common fixed-column-count case only — `grid-cols-none`, explicit
 * `grid-template-columns` track lists, and `grid-row`/`grid-column` item placement
 * span utilities aren't included; they'd need a custom `Layout`, not a `GridCells`
 * preset.
 */
fun gridCols1(): GridCells = GridCells.Fixed(1)

fun gridCols2(): GridCells = GridCells.Fixed(2)

fun gridCols3(): GridCells = GridCells.Fixed(3)

fun gridCols4(): GridCells = GridCells.Fixed(4)

fun gridCols5(): GridCells = GridCells.Fixed(5)

fun gridCols6(): GridCells = GridCells.Fixed(6)

fun gridCols7(): GridCells = GridCells.Fixed(7)

fun gridCols8(): GridCells = GridCells.Fixed(8)

fun gridCols9(): GridCells = GridCells.Fixed(9)

fun gridCols10(): GridCells = GridCells.Fixed(10)

fun gridCols11(): GridCells = GridCells.Fixed(11)

fun gridCols12(): GridCells = GridCells.Fixed(12)
