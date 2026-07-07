package io.github.ronjunevaldoz.tailwind.modifiers

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.unit.dp

/**
 * Tailwind's `blur-*` filter utilities over [Modifier.blur]. `blur-none` is `0.dp`
 * (a no-op, included for API completeness/discoverability matching the Tailwind class).
 */
fun Modifier.blurNone(): Modifier = this.blur(0.dp)

fun Modifier.blurSm(): Modifier = this.blur(4.dp)

fun Modifier.blur(): Modifier = this.blur(8.dp)

fun Modifier.blurMd(): Modifier = this.blur(12.dp)

fun Modifier.blurLg(): Modifier = this.blur(16.dp)

fun Modifier.blurXl(): Modifier = this.blur(24.dp)

fun Modifier.blurXl2(): Modifier = this.blur(40.dp)

fun Modifier.blurXl3(): Modifier = this.blur(64.dp)

/**
 * Tailwind's boolean `grayscale`/`invert`/`sepia` filter utilities. Compose has no
 * built-in equivalent to these (unlike `blur`, which is a first-class modifier), so
 * each is implemented as a [ColorMatrix] applied via a layer paint — the standard
 * technique for full-content color filters in Compose. `brightness-*`, `contrast-*`,
 * `saturate-*`, and `hue-rotate-*` (Tailwind's numeric-scale filters) are not
 * included; they're the same technique with different matrix values, deferred to
 * keep this file's scope to the boolean on/off filters.
 */
fun Modifier.grayscale(): Modifier = colorMatrixFilter(ColorMatrix().apply { setToSaturation(0f) })

private val invertMatrix =
    ColorMatrix(
        floatArrayOf(
            -1f,
            0f,
            0f,
            0f,
            255f,
            0f,
            -1f,
            0f,
            0f,
            255f,
            0f,
            0f,
            -1f,
            0f,
            255f,
            0f,
            0f,
            0f,
            1f,
            0f,
        ),
    )

fun Modifier.invert(): Modifier = colorMatrixFilter(invertMatrix)

private val sepiaMatrix =
    ColorMatrix(
        floatArrayOf(
            0.393f,
            0.769f,
            0.189f,
            0f,
            0f,
            0.349f,
            0.686f,
            0.168f,
            0f,
            0f,
            0.272f,
            0.534f,
            0.131f,
            0f,
            0f,
            0f,
            0f,
            0f,
            1f,
            0f,
        ),
    )

fun Modifier.sepia(): Modifier = colorMatrixFilter(sepiaMatrix)

private fun Modifier.colorMatrixFilter(matrix: ColorMatrix): Modifier =
    this.drawWithContent {
        val paint = Paint().apply { colorFilter = ColorFilter.colorMatrix(matrix) }
        drawContext.canvas.saveLayer(Rect(Offset.Zero, size), paint)
        drawContent()
        drawContext.canvas.restore()
    }
