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
 * technique for full-content color filters in Compose. `hue-rotate-*` (Tailwind's
 * remaining numeric-scale filter) is not included; it needs a rotation matrix rather
 * than the scale/translate ones below, deferred until something actually needs it.
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

/**
 * Tailwind's `brightness-*` filter scale (CSS `brightness(<amount>%)`), a plain RGB
 * channel scale with alpha untouched — canonical stops per Tailwind v4's `utilities.ts`
 * `suggest('brightness', ...)` list.
 */
private fun brightnessMatrix(amount: Float): ColorMatrix =
    ColorMatrix().apply { setToScale(amount, amount, amount, 1f) }

fun Modifier.brightness0(): Modifier = colorMatrixFilter(brightnessMatrix(0.00f))

fun Modifier.brightness50(): Modifier = colorMatrixFilter(brightnessMatrix(0.50f))

fun Modifier.brightness75(): Modifier = colorMatrixFilter(brightnessMatrix(0.75f))

fun Modifier.brightness90(): Modifier = colorMatrixFilter(brightnessMatrix(0.90f))

fun Modifier.brightness95(): Modifier = colorMatrixFilter(brightnessMatrix(0.95f))

fun Modifier.brightness100(): Modifier = colorMatrixFilter(brightnessMatrix(1.00f))

fun Modifier.brightness105(): Modifier = colorMatrixFilter(brightnessMatrix(1.05f))

fun Modifier.brightness110(): Modifier = colorMatrixFilter(brightnessMatrix(1.10f))

fun Modifier.brightness125(): Modifier = colorMatrixFilter(brightnessMatrix(1.25f))

fun Modifier.brightness150(): Modifier = colorMatrixFilter(brightnessMatrix(1.50f))

fun Modifier.brightness200(): Modifier = colorMatrixFilter(brightnessMatrix(2.00f))

/**
 * Tailwind's `contrast-*` filter scale (CSS `contrast(<amount>%)`): `(channel - 0.5) *
 * amount + 0.5` in 0..1 space, i.e. a scale around the midpoint rather than around zero
 * — expressed here in Compose's 0..255 [ColorMatrix] space as a scale plus translate.
 */
private fun contrastMatrix(amount: Float): ColorMatrix {
    val translate = 127.5f * (1 - amount)
    return ColorMatrix(
        floatArrayOf(
            amount,
            0f,
            0f,
            0f,
            translate,
            0f,
            amount,
            0f,
            0f,
            translate,
            0f,
            0f,
            amount,
            0f,
            translate,
            0f,
            0f,
            0f,
            1f,
            0f,
        ),
    )
}

fun Modifier.contrast0(): Modifier = colorMatrixFilter(contrastMatrix(0.00f))

fun Modifier.contrast50(): Modifier = colorMatrixFilter(contrastMatrix(0.50f))

fun Modifier.contrast75(): Modifier = colorMatrixFilter(contrastMatrix(0.75f))

fun Modifier.contrast100(): Modifier = colorMatrixFilter(contrastMatrix(1.00f))

fun Modifier.contrast125(): Modifier = colorMatrixFilter(contrastMatrix(1.25f))

fun Modifier.contrast150(): Modifier = colorMatrixFilter(contrastMatrix(1.50f))

fun Modifier.contrast200(): Modifier = colorMatrixFilter(contrastMatrix(2.00f))

/**
 * Tailwind's `saturate-*` filter scale (CSS `saturate(<amount>%)`). [ColorMatrix
 * .setToSaturation] already implements the exact same formula (0 = grayscale, 1 =
 * identity, >1 = supersaturated) — the same matrix [grayscale] uses with `sat = 0f`.
 */
fun Modifier.saturate0(): Modifier = colorMatrixFilter(ColorMatrix().apply { setToSaturation(0.00f) })

fun Modifier.saturate50(): Modifier = colorMatrixFilter(ColorMatrix().apply { setToSaturation(0.50f) })

fun Modifier.saturate100(): Modifier = colorMatrixFilter(ColorMatrix().apply { setToSaturation(1.00f) })

fun Modifier.saturate150(): Modifier = colorMatrixFilter(ColorMatrix().apply { setToSaturation(1.50f) })

fun Modifier.saturate200(): Modifier = colorMatrixFilter(ColorMatrix().apply { setToSaturation(2.00f) })

private fun Modifier.colorMatrixFilter(matrix: ColorMatrix): Modifier =
    this.drawWithContent {
        val paint = Paint().apply { colorFilter = ColorFilter.colorMatrix(matrix) }
        drawContext.canvas.saveLayer(Rect(Offset.Zero, size), paint)
        drawContent()
        drawContext.canvas.restore()
    }
