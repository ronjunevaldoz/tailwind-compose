package io.github.ronjunevaldoz.tailwind.modifiers

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
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
import org.junit.Rule
import kotlin.test.Test
import kotlin.test.assertNotEquals

class ShadowModifierTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun shadowLg_doesNotChangeMeasuredSize() {
        composeTestRule.setContent {
            Box(
                Modifier
                    .testTag("box")
                    .size(50.dp)
                    .shadowLg()
                    .bgWhite(),
            )
        }
        composeTestRule
            .onNodeWithTag("box")
            .assertWidthIsEqualTo(50.dp)
            .assertHeightIsEqualTo(50.dp)
    }

    /**
     * Compose's shadow paints outside the shadowed node's own layout bounds, so it
     * must be captured from a parent that has room around the box — not from the
     * shadowed node's own screenshot, which only ever shows its own white fill.
     */
    @Test
    fun shadowLg_paintsBeyondTheBoxIntoTheSurroundingArea() {
        composeTestRule.setContent {
            Box(Modifier.testTag("parent").padding(20.dp).size(100.dp)) {
                Box(Modifier.size(60.dp).shadowLg().bgWhite())
            }
        }
        val pixels = composeTestRule.onNodeWithTag("parent").captureToImage().toPixelMap()
        assertNotEquals(0f, pixels[15, 15].alpha, "expected a visible shadow tint just outside the box")
    }
}
