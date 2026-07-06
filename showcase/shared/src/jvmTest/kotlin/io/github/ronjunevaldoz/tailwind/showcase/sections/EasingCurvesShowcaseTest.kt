package io.github.ronjunevaldoz.tailwind.showcase.sections

import androidx.compose.ui.graphics.toPixelMap
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onRoot
import org.junit.Rule
import kotlin.test.Test
import kotlin.test.assertTrue

class EasingCurvesShowcaseTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun dots_animateOverTime() {
        composeTestRule.mainClock.autoAdvance = false
        composeTestRule.setContent { EasingCurvesShowcase() }

        val pixelMapAtStart = composeTestRule.onRoot().captureToImage().toPixelMap()
        composeTestRule.mainClock.advanceTimeBy(1000)
        val pixelMapAfter = composeTestRule.onRoot().captureToImage().toPixelMap()

        var diffCount = 0
        for (y in 0 until pixelMapAtStart.height step 4) {
            for (x in 0 until pixelMapAtStart.width step 4) {
                if (pixelMapAtStart[x, y] != pixelMapAfter[x, y]) diffCount++
            }
        }
        assertTrue(diffCount > 10, "expected many pixels to differ after 1s of animation, found $diffCount")
    }
}
