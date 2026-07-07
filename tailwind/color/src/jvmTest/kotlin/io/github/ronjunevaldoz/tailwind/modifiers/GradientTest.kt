package io.github.ronjunevaldoz.tailwind.modifiers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toPixelMap
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.unit.dp
import io.github.ronjunevaldoz.tailwind.core.TwColors
import kotlin.math.abs
import org.junit.Rule
import kotlin.test.Test
import kotlin.test.assertTrue

private const val CHANNEL_TOLERANCE = 0.02f

class GradientTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun bgGradientToR_placesTheFirstColorNearTheLeftEdge() {
        composeTestRule.setContent {
            Box(
                Modifier
                    .testTag("box")
                    .size(40.dp)
                    .background(bgGradientToR(listOf(TwColors.red500, TwColors.blue500))),
            )
        }
        val pixels = composeTestRule.onNodeWithTag("box").captureToImage().toPixelMap()
        assertCloseTo(TwColors.red500, pixels[0, 20])
        assertNotCloseTo(TwColors.red500, pixels[39, 20])
    }

    @Test
    fun bgGradientToL_isTheReverseOfBgGradientToR() {
        composeTestRule.setContent {
            Box(
                Modifier
                    .testTag("box")
                    .size(40.dp)
                    .background(bgGradientToL(listOf(TwColors.red500, TwColors.blue500))),
            )
        }
        val pixels = composeTestRule.onNodeWithTag("box").captureToImage().toPixelMap()
        assertCloseTo(TwColors.red500, pixels[39, 20])
    }

    private fun assertCloseTo(
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

    private fun assertNotCloseTo(
        expected: Color,
        actual: Color,
    ) {
        val closeEnough =
            abs(expected.red - actual.red) <= CHANNEL_TOLERANCE &&
                abs(expected.green - actual.green) <= CHANNEL_TOLERANCE &&
                abs(expected.blue - actual.blue) <= CHANNEL_TOLERANCE
        assertTrue(!closeEnough, "expected a visibly different color than $expected but was $actual")
    }
}
