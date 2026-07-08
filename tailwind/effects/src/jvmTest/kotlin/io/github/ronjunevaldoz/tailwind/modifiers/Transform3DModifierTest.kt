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
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class Transform3DModifierTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun rotateX45_doesNotChangeMeasuredSize() {
        composeTestRule.setContent {
            Box(
                Modifier
                    .testTag("box")
                    .size(50.dp)
                    .rotateX45()
                    .background(TwColors.blue500),
            )
        }
        composeTestRule
            .onNodeWithTag("box")
            .assertWidthIsEqualTo(50.dp)
            .assertHeightIsEqualTo(50.dp)
    }

    @Test
    fun rotateZ90_stillRendersContentInTheCenter() {
        composeTestRule.setContent {
            Box(
                Modifier
                    .testTag("box")
                    .size(50.dp)
                    .perspectiveNormal()
                    .rotateZ90()
                    .background(TwColors.blue500),
            )
        }
        val centerPixel = composeTestRule.onNodeWithTag("box").captureToImage().toPixelMap()[25, 25]
        assertEquals(TwColors.blue500, centerPixel)
    }

    @Test
    fun scale50_doesNotChangeMeasuredSize() {
        composeTestRule.setContent {
            Box(
                Modifier
                    .testTag("box")
                    .size(50.dp)
                    .scale50()
                    .background(TwColors.blue500),
            )
        }
        composeTestRule
            .onNodeWithTag("box")
            .assertWidthIsEqualTo(50.dp)
            .assertHeightIsEqualTo(50.dp)
    }

    @Test
    fun scale50_shrinksRenderedContentTowardCenter() {
        composeTestRule.setContent {
            Box(
                Modifier
                    .testTag("box")
                    .size(50.dp)
                    .scale50()
                    .background(TwColors.blue500),
            )
        }
        val pixelMap = composeTestRule.onNodeWithTag("box").captureToImage().toPixelMap()
        // Center is still covered by the shrunk content...
        assertEquals(TwColors.blue500, pixelMap[25, 25])
        // ...but a corner, which would be filled at scale 1.0, now falls outside it.
        assertNotEquals(TwColors.blue500, pixelMap[2, 2])
    }

    @Test
    fun translateX8_doesNotChangeMeasuredSize() {
        composeTestRule.setContent {
            Box(
                Modifier
                    .testTag("box")
                    .size(50.dp)
                    .translateX8()
                    .background(TwColors.blue500),
            )
        }
        composeTestRule
            .onNodeWithTag("box")
            .assertWidthIsEqualTo(50.dp)
            .assertHeightIsEqualTo(50.dp)
    }

    @Test
    fun translateX8_shiftsRenderedContentRight() {
        composeTestRule.setContent {
            Box(
                Modifier
                    .testTag("box")
                    .size(50.dp)
                    .translateX8()
                    .background(TwColors.blue500),
            )
        }
        val pixelMap = composeTestRule.onNodeWithTag("box").captureToImage().toPixelMap()
        // The original left edge is no longer covered once content shifts 8dp right...
        assertNotEquals(TwColors.blue500, pixelMap[2, 25])
        // ...while a pixel well inside the shifted region still is.
        assertEquals(TwColors.blue500, pixelMap[45, 25])
    }
}
