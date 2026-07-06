package io.github.ronjunevaldoz.tailwind.showcase.sections

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.toPixelMap
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performTouchInput
import io.github.ronjunevaldoz.tailwind.core.TwColors
import org.junit.Rule
import kotlin.test.Test
import kotlin.test.assertNotEquals

class DarkModeShowcaseTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun draggingHandle_movesTheLightDarkDivider() {
        composeTestRule.setContent {
            DarkModeShowcase()
        }

        // Sample a point left of the default (center) divider position — starts light, since
        // light fills [0, dividerX) and dark fills [dividerX, width).
        val probeX = 60
        val probeY = 100
        val before = composeTestRule.onRoot().captureToImage().toPixelMap()[probeX, probeY]

        // Drag the handle left, decreasing dividerX so the dark region grows leftward past
        // the probe point. A single continuous move (not several small moveTo steps) --
        // Compose's touch-slop threshold is consumed once per gesture, not once per moveTo.
        composeTestRule
            .onNodeWithTag(DARK_MODE_HANDLE_TEST_TAG)
            .performTouchInput {
                down(center)
                moveTo(center - Offset(200f, 0f))
                up()
            }
        composeTestRule.waitForIdle()

        val after = composeTestRule.onRoot().captureToImage().toPixelMap()[probeX, probeY]

        assertNotEquals(before, after, "expected dragging the handle to change what's rendered at the probe point")
        assertNotEquals(TwColors.white, after, "expected the probe point to become dark after dragging past it")
    }
}
