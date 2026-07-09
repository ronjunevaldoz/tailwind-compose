package io.github.ronjunevaldoz.tailwind.style

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.style.ExperimentalFoundationStyleApi
import androidx.compose.foundation.style.styleable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

@OptIn(ExperimentalFoundationStyleApi::class)
class RingStyleTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun ringStyle4_doesNotChangeMeasuredSize() {
        composeTestRule.setContent {
            Box(Modifier.testTag("box").size(50.dp).styleable(style = ringStyle4(Color.Blue)))
        }
        composeTestRule
            .onNodeWithTag("box")
            .assertWidthIsEqualTo(50.dp)
            .assertHeightIsEqualTo(50.dp)
    }

    @Test
    fun ringStyle4_paintsBeyondTheBoxIntoTheSurroundingArea() {
        composeTestRule.setContent {
            Box(Modifier.testTag("parent").padding(20.dp).size(100.dp)) {
                Box(
                    Modifier
                        .size(60.dp)
                        .styleable(style = ringStyle4(Color.Blue))
                        .background(Color.White),
                )
            }
        }
        val pixels = composeTestRule.onNodeWithTag("parent").captureToImage().toPixelMap()
        assertNotEquals(0f, pixels[18, 18].alpha, "expected a visible ring tint just outside the box")
    }

    @Test
    fun ringStyle0_paintsNothingBeyondTheBox() {
        composeTestRule.setContent {
            Box(Modifier.testTag("parent").padding(20.dp).size(100.dp)) {
                Box(
                    Modifier
                        .size(60.dp)
                        .styleable(style = ringStyle0(Color.Blue))
                        .background(Color.White),
                )
            }
        }
        val pixels = composeTestRule.onNodeWithTag("parent").captureToImage().toPixelMap()
        assert(pixels[15, 15].alpha == 0f) { "expected no ring paint for a zero-width ring" }
    }
}
