package io.github.ronjunevaldoz.tailwind.modifiers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import io.github.ronjunevaldoz.tailwind.core.TwRadius
import org.junit.Rule
import kotlin.test.Test
import kotlin.test.assertNotEquals

class RingModifierTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun ring4_doesNotChangeMeasuredSize() {
        // Unlike border(), the ring is painted entirely outside the layout bounds,
        // so it must never influence the measured size -- same contract as shadow().
        composeTestRule.setContent {
            Box(Modifier.testTag("box").size(50.dp).ring4(TwColors.blue500))
        }
        composeTestRule
            .onNodeWithTag("box")
            .assertWidthIsEqualTo(50.dp)
            .assertHeightIsEqualTo(50.dp)
    }

    /**
     * Like shadow, the ring paints outside the ringed node's own bounds, so it must
     * be captured from a parent that has room around the box.
     */
    @Test
    fun ring4_paintsBeyondTheBoxIntoTheSurroundingArea() {
        composeTestRule.setContent {
            Box(Modifier.testTag("parent").padding(20.dp).size(100.dp)) {
                Box(Modifier.size(60.dp).ring4(TwColors.blue500).background(TwColors.white))
            }
        }
        val pixels = composeTestRule.onNodeWithTag("parent").captureToImage().toPixelMap()
        // A 4dp ring extends outward from the child's edge at (20,20) only to (16,16) --
        // unlike shadowLg's much wider blur bleed, (18,18) (inside that 4px band) is the
        // right probe here, not (15,15) (1px beyond the ring's actual reach).
        assertNotEquals(0f, pixels[18, 18].alpha, "expected a visible ring tint just outside the box")
    }

    @Test
    fun ring0_paintsNothingBeyondTheBox() {
        composeTestRule.setContent {
            Box(Modifier.testTag("parent").padding(20.dp).size(100.dp)) {
                Box(Modifier.size(60.dp).ring0(TwColors.blue500).background(TwColors.white))
            }
        }
        val pixels = composeTestRule.onNodeWithTag("parent").captureToImage().toPixelMap()
        assert(pixels[15, 15].alpha == 0f) { "expected no ring paint for a zero-width ring" }
    }

    @Test
    fun ring_withExplicitRoundedShape_followsTheRoundedCorners() {
        // A ring around a fully-rounded box must not paint into the sharp corners
        // of its own bounding square -- it should trace the rounded outline.
        composeTestRule.setContent {
            Box(Modifier.testTag("parent").padding(20.dp).size(100.dp)) {
                Box(
                    Modifier
                        .size(60.dp)
                        .ring4(TwColors.blue500, RoundedCornerShape(TwRadius.full)),
                )
            }
        }
        val pixels = composeTestRule.onNodeWithTag("parent").captureToImage().toPixelMap()
        // Directly outside the flat edge (mid-side) should show ring color;
        // the far corner of the bounding square should not.
        assertNotEquals(0f, pixels[50, 17].alpha, "expected ring color just outside the flat edge")
        assert(
            pixels[21, 21].alpha == 0f,
        ) { "expected no ring paint in the square corner outside a fully-rounded shape" }
    }
}
