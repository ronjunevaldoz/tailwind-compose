package io.github.ronjunevaldoz.tailwind.modifiers

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer

/**
 * Tailwind's `rotate-x-*`/`rotate-y-*`/`rotate-z-*` 3D transform utilities via
 * [Modifier.graphicsLayer]'s `rotationX`/`rotationY`/`rotationZ`. Tailwind's full
 * rotate scale has ~9 steps per axis plus negative variants (54 combinations); only
 * 45/90/180 degrees per axis are included here to keep this file's scope
 * representative rather than exhaustive.
 */
fun Modifier.rotateX45(): Modifier = this.graphicsLayer { rotationX = 45f }

fun Modifier.rotateX90(): Modifier = this.graphicsLayer { rotationX = 90f }

fun Modifier.rotateX180(): Modifier = this.graphicsLayer { rotationX = 180f }

fun Modifier.rotateY45(): Modifier = this.graphicsLayer { rotationY = 45f }

fun Modifier.rotateY90(): Modifier = this.graphicsLayer { rotationY = 90f }

fun Modifier.rotateY180(): Modifier = this.graphicsLayer { rotationY = 180f }

fun Modifier.rotateZ45(): Modifier = this.graphicsLayer { rotationZ = 45f }

fun Modifier.rotateZ90(): Modifier = this.graphicsLayer { rotationZ = 90f }

fun Modifier.rotateZ180(): Modifier = this.graphicsLayer { rotationZ = 180f }

/**
 * Tailwind's `perspective-*` utilities via [Modifier.graphicsLayer]'s
 * `cameraDistance`. CSS `perspective` is in pixels (smaller = more dramatic
 * distortion) while Compose's `cameraDistance` is in different, inversely-related
 * units (smaller = *more* distortion, larger = less) — these are named presets
 * approximating Tailwind's near/normal/distant feel, not a unit conversion.
 * `8f` is Compose's own default, used here for `perspectiveNormal()`.
 */
fun Modifier.perspectiveNear(): Modifier = this.graphicsLayer { cameraDistance = 4f }

fun Modifier.perspectiveNormal(): Modifier = this.graphicsLayer { cameraDistance = 8f }

fun Modifier.perspectiveDistant(): Modifier = this.graphicsLayer { cameraDistance = 16f }
