package io.github.ronjunevaldoz.tailwind.modifiers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toPixelMap
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.unit.dp
import io.github.ronjunevaldoz.tailwind.core.TwColors
import org.junit.Rule
import kotlin.math.abs
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

private const val ALPHA_TOLERANCE = 0.01f

class OpacityModifierTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun opacity50_setsPixelAlphaToHalf() {
        // opacity*() (like clip/rounded*()) must precede background-drawing modifiers
        // in the chain to actually affect them — see Opacity.kt's KDoc.
        composeTestRule.setContent {
            Box(
                Modifier
                    .testTag("box")
                    .width(20.dp)
                    .height(20.dp)
                    .opacity50()
                    .background(TwColors.blue500),
            )
        }
        val pixel = composeTestRule.onNodeWithTag("box").captureToImage().toPixelMap()[10, 10]
        assertTrue(abs(pixel.alpha - 0.5f) <= ALPHA_TOLERANCE, "expected alpha ~0.5 but was ${pixel.alpha}")
    }

    @Test
    fun opacity100_isFullyOpaque() {
        composeTestRule.setContent {
            Box(
                Modifier
                    .testTag("box")
                    .width(20.dp)
                    .height(20.dp)
                    .opacity100()
                    .background(TwColors.blue500),
            )
        }
        val pixel = composeTestRule.onNodeWithTag("box").captureToImage().toPixelMap()[10, 10]
        assertEquals(TwColors.blue500, pixel)
    }
}
