package io.github.ronjunevaldoz.tailwind.modifiers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toPixelMap
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertHeightIsEqualTo
import androidx.compose.ui.test.assertWidthIsEqualTo
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.unit.dp
import io.github.ronjunevaldoz.tailwind.core.TwColors
import org.junit.Rule
import kotlin.math.abs
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

class FiltersModifierTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun blurLg_doesNotChangeMeasuredSize() {
        composeTestRule.setContent {
            Box(
                Modifier
                    .testTag("box")
                    .size(50.dp)
                    .blurLg()
                    .background(TwColors.blue500),
            )
        }
        composeTestRule
            .onNodeWithTag("box")
            .assertWidthIsEqualTo(50.dp)
            .assertHeightIsEqualTo(50.dp)
    }

    @Test
    fun grayscale_removesColorFromAFlatFill() {
        composeTestRule.setContent {
            Box(
                Modifier
                    .testTag("box")
                    .size(20.dp)
                    .grayscale()
                    .background(TwColors.blue500),
            )
        }
        val pixel = composeTestRule.onNodeWithTag("box").captureToImage().toPixelMap()[10, 10]
        // A grayscale pixel has equal R, G, and B channels.
        assertEquals(pixel.red, pixel.green)
        assertEquals(pixel.green, pixel.blue)
        assertNotEquals(TwColors.blue500, pixel)
    }

    @Test
    fun invert_flipsAFlatFillTowardItsComplement() {
        composeTestRule.setContent {
            Box(
                Modifier
                    .testTag("box")
                    .size(20.dp)
                    .invert()
                    .background(TwColors.white),
            )
        }
        val pixel = composeTestRule.onNodeWithTag("box").captureToImage().toPixelMap()[10, 10]
        // Inverting white should land on (near-)black.
        val tolerance = 0.02f
        assertTrue(abs(pixel.red) <= tolerance, "expected red ~0 but was ${pixel.red}")
        assertTrue(abs(pixel.green) <= tolerance, "expected green ~0 but was ${pixel.green}")
        assertTrue(abs(pixel.blue) <= tolerance, "expected blue ~0 but was ${pixel.blue}")
    }
}
