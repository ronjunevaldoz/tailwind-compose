package io.github.ronjunevaldoz.tailwind.core

import kotlin.math.abs
import kotlin.test.Test
import kotlin.test.assertTrue

private const val CHANNEL_TOLERANCE = 1f / 255f

class OklchTest {
    @Test
    fun toColor_fullLightnessZeroChroma_isWhite() {
        val color = Oklch(1f, 0f, 0f).toColor()
        assertCloseTo(1f, color.red)
        assertCloseTo(1f, color.green)
        assertCloseTo(1f, color.blue)
    }

    @Test
    fun toColor_zeroLightness_isBlack() {
        val color = Oklch(0f, 0f, 0f).toColor()
        assertCloseTo(0f, color.red)
        assertCloseTo(0f, color.green)
        assertCloseTo(0f, color.blue)
    }

    @Test
    fun toColor_redHue_hasDominantRedChannel() {
        val color = Oklch(0.637f, 0.237f, 25.331f).toColor()
        assertTrue(color.red > color.green)
        assertTrue(color.red > color.blue)
    }

    @Test
    fun toColor_blueHue_hasDominantBlueChannel() {
        val color = Oklch(0.623f, 0.214f, 259.815f).toColor()
        assertTrue(color.blue > color.red)
        assertTrue(color.blue > color.green)
    }

    @Test
    fun toColor_greenHue_hasDominantGreenChannel() {
        val color = Oklch(0.723f, 0.219f, 149.579f).toColor()
        assertTrue(color.green > color.red)
        assertTrue(color.green > color.blue)
    }

    private fun assertCloseTo(
        expected: Float,
        actual: Float,
    ) {
        assertTrue(
            abs(expected - actual) <= CHANNEL_TOLERANCE,
            "expected $expected but was $actual (tolerance $CHANNEL_TOLERANCE)",
        )
    }
}
