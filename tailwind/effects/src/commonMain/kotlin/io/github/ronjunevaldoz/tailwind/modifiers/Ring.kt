package io.github.ronjunevaldoz.tailwind.modifiers

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.inset
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Tailwind's `ring-*` utilities (CSS `box-shadow: 0 0 0 <width> <color>`) — an
 * outline ring painted *outside* the element's own bounds, unlike [Modifier.border]
 * which strokes inside them. Only the current (v4) scale is implemented: `ring`
 * (bare) is `1px` here, not v3's `3px` default, and there is no `ring-offset-*`
 * variant — v4 removed offset rings entirely (confirmed against the live docs, not
 * assumed from the v3 scale). `ring-inset` is also not included; nothing in this
 * library needs an inward-drawn ring yet.
 *
 * Compose has no `box-shadow`-style primitive that paints outside layout bounds
 * without affecting measurement, so the ring is drawn by stroking a shape outline
 * *expanded* by [Dp] on each side, offset into the negative margin via
 * [androidx.compose.ui.graphics.drawscope.inset] with a negative delta (which grows,
 * rather than shrinks, the draw area) — the same "paint beyond my own bounds"
 * technique Compose's own [androidx.compose.ui.draw.shadow] uses.
 */
fun Modifier.ring0(
    color: Color,
    shape: Shape = RectangleShape,
): Modifier = this.ring(0.dp, color, shape)

fun Modifier.ring(
    color: Color,
    shape: Shape = RectangleShape,
): Modifier = this.ring(1.dp, color, shape)

fun Modifier.ring2(
    color: Color,
    shape: Shape = RectangleShape,
): Modifier = this.ring(2.dp, color, shape)

fun Modifier.ring4(
    color: Color,
    shape: Shape = RectangleShape,
): Modifier = this.ring(4.dp, color, shape)

fun Modifier.ring8(
    color: Color,
    shape: Shape = RectangleShape,
): Modifier = this.ring(8.dp, color, shape)

private fun Modifier.ring(
    width: Dp,
    color: Color,
    shape: Shape,
): Modifier =
    this.drawWithCache {
        val strokePx = width.toPx()
        val expandedOutline =
            shape.createOutline(
                Size(size.width + strokePx, size.height + strokePx),
                layoutDirection,
                this,
            )
        onDrawWithContent {
            drawContent()
            inset(-strokePx / 2f) {
                drawOutline(expandedOutline, color = color, style = Stroke(strokePx))
            }
        }
    }
