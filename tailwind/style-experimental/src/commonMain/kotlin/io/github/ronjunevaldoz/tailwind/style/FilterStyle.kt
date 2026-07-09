package io.github.ronjunevaldoz.tailwind.style

import androidx.compose.foundation.style.ExperimentalFoundationStyleApi
import androidx.compose.foundation.style.Style
import androidx.compose.foundation.style.then
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix

/**
 * Tailwind's boolean/scale color filter utilities via
 * [androidx.compose.foundation.style.ColorFilterScope.colorFilter] -- the same [ColorMatrix]
 * values as [io.github.ronjunevaldoz.tailwind.modifiers.grayscale] et al. in `tailwind-effects`,
 * but applied through the Style API's own `colorFilter` property directly instead of a manual
 * `drawWithContent` + `saveLayer` layer paint -- the Style API removes the need for that
 * hand-rolled technique entirely.
 */
@ExperimentalFoundationStyleApi
fun Style.grayscaleStyle(): Style = colorMatrixStyle(ColorMatrix().apply { setToSaturation(0f) })

private val invertMatrix =
    ColorMatrix(
        floatArrayOf(
            -1f, 0f, 0f, 0f, 255f,
            0f, -1f, 0f, 0f, 255f,
            0f, 0f, -1f, 0f, 255f,
            0f, 0f, 0f, 1f, 0f,
        ),
    )

@ExperimentalFoundationStyleApi
fun Style.invertStyle(): Style = colorMatrixStyle(invertMatrix)

private val sepiaMatrix =
    ColorMatrix(
        floatArrayOf(
            0.393f, 0.769f, 0.189f, 0f, 0f,
            0.349f, 0.686f, 0.168f, 0f, 0f,
            0.272f, 0.534f, 0.131f, 0f, 0f,
            0f, 0f, 0f, 1f, 0f,
        ),
    )

@ExperimentalFoundationStyleApi
fun Style.sepiaStyle(): Style = colorMatrixStyle(sepiaMatrix)

@ExperimentalFoundationStyleApi
fun Style.brightnessStyle(amount: Float): Style = colorMatrixStyle(ColorMatrix().apply { setToScale(amount, amount, amount, 1f) })

@ExperimentalFoundationStyleApi
fun Style.brightnessStyle50(): Style = brightnessStyle(0.50f)

@ExperimentalFoundationStyleApi
fun Style.brightnessStyle150(): Style = brightnessStyle(1.50f)

@ExperimentalFoundationStyleApi
fun Style.contrastStyle(amount: Float): Style {
    val translate = 127.5f * (1 - amount)
    return colorMatrixStyle(
        ColorMatrix(
            floatArrayOf(
                amount, 0f, 0f, 0f, translate,
                0f, amount, 0f, 0f, translate,
                0f, 0f, amount, 0f, translate,
                0f, 0f, 0f, 1f, 0f,
            ),
        ),
    )
}

@ExperimentalFoundationStyleApi
fun Style.contrastStyle150(): Style = contrastStyle(1.50f)

@ExperimentalFoundationStyleApi
fun Style.saturateStyle(amount: Float): Style = colorMatrixStyle(ColorMatrix().apply { setToSaturation(amount) })

@ExperimentalFoundationStyleApi
fun Style.saturateStyle200(): Style = saturateStyle(2.00f)

@ExperimentalFoundationStyleApi
private fun Style.colorMatrixStyle(matrix: ColorMatrix): Style =
    this.then(Style { colorFilter(ColorFilter.colorMatrix(matrix)) })
