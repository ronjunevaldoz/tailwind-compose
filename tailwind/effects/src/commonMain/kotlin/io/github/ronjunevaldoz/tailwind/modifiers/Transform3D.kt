package io.github.ronjunevaldoz.tailwind.modifiers

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import io.github.ronjunevaldoz.tailwind.core.TwSpacing

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

/**
 * Tailwind's `scale-*` utility (uniform X+Y scale) via [Modifier.graphicsLayer]'s
 * `scaleX`/`scaleY`. Unlike rotate/translate above and below, this is the *complete*
 * canonical scale straight from Tailwind v4's `utilities.ts` `suggest('scale', ...)`
 * list -- a finite 11-step percentage set, not a representative subset of a larger
 * one. Per-axis `scale-x-*`/`scale-y-*` exist in Tailwind too but are omitted here;
 * add them if something actually needs independent-axis scaling.
 */
fun Modifier.scale0(): Modifier =
    this.graphicsLayer {
        scaleX = 0.00f
        scaleY = 0.00f
    }

fun Modifier.scale50(): Modifier =
    this.graphicsLayer {
        scaleX = 0.50f
        scaleY = 0.50f
    }

fun Modifier.scale75(): Modifier =
    this.graphicsLayer {
        scaleX = 0.75f
        scaleY = 0.75f
    }

fun Modifier.scale90(): Modifier =
    this.graphicsLayer {
        scaleX = 0.90f
        scaleY = 0.90f
    }

fun Modifier.scale95(): Modifier =
    this.graphicsLayer {
        scaleX = 0.95f
        scaleY = 0.95f
    }

fun Modifier.scale100(): Modifier =
    this.graphicsLayer {
        scaleX = 1.00f
        scaleY = 1.00f
    }

fun Modifier.scale105(): Modifier =
    this.graphicsLayer {
        scaleX = 1.05f
        scaleY = 1.05f
    }

fun Modifier.scale110(): Modifier =
    this.graphicsLayer {
        scaleX = 1.10f
        scaleY = 1.10f
    }

fun Modifier.scale125(): Modifier =
    this.graphicsLayer {
        scaleX = 1.25f
        scaleY = 1.25f
    }

fun Modifier.scale150(): Modifier =
    this.graphicsLayer {
        scaleX = 1.50f
        scaleY = 1.50f
    }

fun Modifier.scale200(): Modifier =
    this.graphicsLayer {
        scaleX = 2.00f
        scaleY = 2.00f
    }

/**
 * Tailwind's `translate-x-*`/`translate-y-*` utilities via [Modifier.graphicsLayer]'s
 * `translationX`/`translationY`. Backed by the same [TwSpacing] scale as
 * padding/margin/gap -- Tailwind's own `translate-*` resolves against the
 * `--spacing` theme scale, not a bespoke one (confirmed in `utilities.ts`, where
 * `translate` is a `spacingUtility` call, same family as `margin`). Representative
 * subset (1/2/4/8 steps, both signs) rather than the full 35-step scale, matching
 * this file's existing rotate-scope precedent. Fraction-based values
 * (`translate-x-1/2`, `translate-x-full`) aren't included -- those are relative to
 * the element's own measured size, a different mechanism than a fixed [TwSpacing] Dp.
 */
fun Modifier.translateX1(): Modifier = this.graphicsLayer { translationX = TwSpacing.scale1.toPx() }

fun Modifier.translateX2(): Modifier = this.graphicsLayer { translationX = TwSpacing.scale2.toPx() }

fun Modifier.translateX4(): Modifier = this.graphicsLayer { translationX = TwSpacing.scale4.toPx() }

fun Modifier.translateX8(): Modifier = this.graphicsLayer { translationX = TwSpacing.scale8.toPx() }

fun Modifier.translateXNeg1(): Modifier = this.graphicsLayer { translationX = -TwSpacing.scale1.toPx() }

fun Modifier.translateXNeg2(): Modifier = this.graphicsLayer { translationX = -TwSpacing.scale2.toPx() }

fun Modifier.translateXNeg4(): Modifier = this.graphicsLayer { translationX = -TwSpacing.scale4.toPx() }

fun Modifier.translateXNeg8(): Modifier = this.graphicsLayer { translationX = -TwSpacing.scale8.toPx() }

fun Modifier.translateY1(): Modifier = this.graphicsLayer { translationY = TwSpacing.scale1.toPx() }

fun Modifier.translateY2(): Modifier = this.graphicsLayer { translationY = TwSpacing.scale2.toPx() }

fun Modifier.translateY4(): Modifier = this.graphicsLayer { translationY = TwSpacing.scale4.toPx() }

fun Modifier.translateY8(): Modifier = this.graphicsLayer { translationY = TwSpacing.scale8.toPx() }

fun Modifier.translateYNeg1(): Modifier = this.graphicsLayer { translationY = -TwSpacing.scale1.toPx() }

fun Modifier.translateYNeg2(): Modifier = this.graphicsLayer { translationY = -TwSpacing.scale2.toPx() }

fun Modifier.translateYNeg4(): Modifier = this.graphicsLayer { translationY = -TwSpacing.scale4.toPx() }

fun Modifier.translateYNeg8(): Modifier = this.graphicsLayer { translationY = -TwSpacing.scale8.toPx() }
