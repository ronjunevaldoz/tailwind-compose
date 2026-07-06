package io.github.ronjunevaldoz.tailwind.core

import androidx.compose.ui.graphics.Color
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin

/**
 * A color expressed in OKLCH — the perceptually uniform Lightness/Chroma/Hue
 * space Tailwind CSS v4 uses to define its default palette.
 *
 * @param lightness perceptual lightness, 0f (black) to 1f (white)
 * @param chroma colorfulness/saturation, typically 0f to ~0.4f
 * @param hue hue angle in degrees, 0-360
 */
data class Oklch(
    val lightness: Float,
    val chroma: Float,
    val hue: Float,
)

/**
 * Converts this [Oklch] color to a Compose [Color] in the sRGB color space,
 * since Compose has no native OKLCH/OKLab color space to render in directly.
 *
 * Implements Björn Ottosson's reference OKLab -> linear sRGB conversion
 * (https://bottosson.github.io/posts/oklab/), followed by sRGB gamma encoding.
 */
@Suppress("MagicNumber")
fun Oklch.toColor(): Color {
    val hueRadians = hue * (PI.toFloat() / 180f)
    val a = chroma * cos(hueRadians)
    val b = chroma * sin(hueRadians)

    val lPrime = lightness + 0.3963377774f * a + 0.2158037573f * b
    val mPrime = lightness - 0.1055613458f * a - 0.0638541728f * b
    val sPrime = lightness - 0.0894841775f * a - 1.2914855480f * b

    val l = lPrime * lPrime * lPrime
    val m = mPrime * mPrime * mPrime
    val s = sPrime * sPrime * sPrime

    val linearR = 4.0767416621f * l - 3.3077115913f * m + 0.2309699292f * s
    val linearG = -1.2684380046f * l + 2.6097574011f * m - 0.3413193965f * s
    val linearB = -0.0041960863f * l - 0.7034186147f * m + 1.7076147010f * s

    return Color(
        red = linearToSrgb(linearR),
        green = linearToSrgb(linearG),
        blue = linearToSrgb(linearB),
    )
}

@Suppress("MagicNumber")
private fun linearToSrgb(component: Float): Float {
    val c = component.coerceIn(0f, 1f)
    return if (c <= 0.0031308f) {
        c * 12.92f
    } else {
        1.055f * c.pow(1f / 2.4f) - 0.055f
    }
}
