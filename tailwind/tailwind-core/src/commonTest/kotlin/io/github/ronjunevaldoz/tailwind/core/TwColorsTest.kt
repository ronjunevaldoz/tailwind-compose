package io.github.ronjunevaldoz.tailwind.core

import androidx.compose.ui.graphics.Color
import kotlin.math.abs
import kotlin.test.Test
import kotlin.test.assertTrue

/**
 * Expected values are the sRGB conversion of Tailwind v4's actual OKLCH source
 * values (packages/tailwindcss/theme.css), not the old v3 hex palette — v4
 * deliberately shifts some shades for perceptual uniformity, so these are
 * *not* expected to match v3's hex constants.
 */
private const val CHANNEL_TOLERANCE = 1f / 255f

class TwColorsTest {
    @Test
    fun spotCheck_wellKnownShades() {
        assertColorCloseTo(Color(0xFF2B7FFF), TwColors.blue500)
        assertColorCloseTo(Color(0xFFFB2C36), TwColors.red500)
        assertColorCloseTo(Color(0xFF00C950), TwColors.green500)
        assertColorCloseTo(Color(0xFF0F172B), TwColors.slate900)
        assertColorCloseTo(Color(0xFFFFFFFF), TwColors.white)
        assertColorCloseTo(Color(0xFF000000), TwColors.black)
    }

    @Test
    fun spotCheck_edgeShades() {
        assertColorCloseTo(Color(0xFFF8FAFC), TwColors.slate50)
        assertColorCloseTo(Color(0xFF020618), TwColors.slate950)
        assertColorCloseTo(Color(0xFFFFF1F2), TwColors.rose50)
        assertColorCloseTo(Color(0xFF4D0218), TwColors.rose950)
    }

    @Test
    fun newV4OnlyHues_areDefined() {
        // mauve/mist/olive/taupe are new neutral-family hues added in Tailwind v4.3.
        assertTrue(TwColors.mauve500 != TwColors.mist500)
        assertTrue(TwColors.olive500 != TwColors.taupe500)
    }

    private fun assertColorCloseTo(
        expected: Color,
        actual: Color,
    ) {
        assertTrue(
            abs(expected.red - actual.red) <= CHANNEL_TOLERANCE,
            "red: expected ${expected.red} but was ${actual.red}",
        )
        assertTrue(
            abs(expected.green - actual.green) <= CHANNEL_TOLERANCE,
            "green: expected ${expected.green} but was ${actual.green}",
        )
        assertTrue(
            abs(expected.blue - actual.blue) <= CHANNEL_TOLERANCE,
            "blue: expected ${expected.blue} but was ${actual.blue}",
        )
    }
}
