package io.github.ronjunevaldoz.tailwind.style

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.style.ExperimentalFoundationStyleApi
import androidx.compose.foundation.style.Style
import androidx.compose.foundation.style.styleable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toPixelMap
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.unit.dp
import org.junit.Rule
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

/** [BorderStyle], [ColorStyle], [OpacityStyle], and [ShadowStyle], grouped since all draw. */
@OptIn(ExperimentalFoundationStyleApi::class)
class DrawStyleTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun bgStyle_fillsTheComponentWithTheGivenColor() {
        composeTestRule.setContent {
            Box(Modifier.testTag("box").size(40.dp).styleable(style = Style.bgStyle(Color.Blue)))
        }
        val center = composeTestRule.onNodeWithTag("box").captureToImage().toPixelMap()[20, 20]
        assertEquals(Color.Blue, center)
    }

    @Test
    fun bgStyleBrush_fillsTheComponentWithASolidColorBrush() {
        // A single-color Brush.horizontalGradient collapses to a solid fill -- a simple,
        // deterministic way to verify the Brush overload without asserting on a gradient's
        // exact interpolated pixel values.
        composeTestRule.setContent {
            Box(
                Modifier
                    .testTag("box")
                    .size(40.dp)
                    .styleable(style = Style.bgStyle(Brush.horizontalGradient(listOf(Color.Green, Color.Green)))),
            )
        }
        val center = composeTestRule.onNodeWithTag("box").captureToImage().toPixelMap()[20, 20]
        assertEquals(Color.Green, center)
    }

    @Test
    fun borderStyle4_paintsTheBorderColorAtTheEdge() {
        composeTestRule.setContent {
            Box(
                Modifier
                    .testTag("box")
                    .size(40.dp)
                    .styleable(style = Style.bgStyle(Color.White).borderStyle4(Color.Red)),
            )
        }
        val edge = composeTestRule.onNodeWithTag("box").captureToImage().toPixelMap()[1, 20]
        assertEquals(Color.Red, edge)
    }

    @Test
    fun opacityStyle0_makesTheComponentFullyTransparent() {
        composeTestRule.setContent {
            Box(Modifier.testTag("parent").padding(20.dp).size(80.dp)) {
                Box(Modifier.size(40.dp).styleable(style = Style.bgStyle(Color.Blue).opacityStyle0()))
            }
        }
        val pixels = composeTestRule.onNodeWithTag("parent").captureToImage().toPixelMap()
        assert(pixels[40, 40].alpha < 0.01f) { "expected the fully transparent box to paint nothing" }
    }

    @Test
    fun shadowStyle_paintsBeyondTheBoxIntoTheSurroundingArea() {
        composeTestRule.setContent {
            Box(Modifier.testTag("parent").padding(20.dp).size(100.dp)) {
                Box(
                    Modifier
                        .size(60.dp)
                        .styleable(style = Style.bgStyle(Color.White).shadowStyle(10.dp)),
                )
            }
        }
        val pixels = composeTestRule.onNodeWithTag("parent").captureToImage().toPixelMap()
        assertNotEquals(0f, pixels[15, 15].alpha, "expected a visible shadow tint just outside the box")
    }
}
