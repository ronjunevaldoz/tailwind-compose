package io.github.ronjunevaldoz.tailwind.style

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.style.ExperimentalFoundationStyleApi
import androidx.compose.foundation.style.Style
import androidx.compose.foundation.style.styleable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertHeightIsEqualTo
import androidx.compose.ui.test.assertWidthIsEqualTo
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.unit.dp
import org.junit.Rule
import kotlin.test.Test

/**
 * [TransformStyle] and [ZIndexStyle]. `scale`/`rotate`/`translate` are drawing-time
 * `graphicsLayer` effects, not layout-affecting -- like [io.github.ronjunevaldoz.tailwind
 * .modifiers.scale150] et al., applying them must never change the measured box size. `zIndex`
 * affects draw order only, which Roborazzi-style pixel diffing can verify but a plain size
 * assertion cannot -- covered here as a same-size smoke check instead.
 */
@OptIn(ExperimentalFoundationStyleApi::class)
class LayerStyleTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun scaleStyle150_doesNotChangeMeasuredSize() {
        composeTestRule.setContent {
            Box(Modifier.testTag("box").styleable(style = Style.widthStyle(40.dp).heightStyle(40.dp).scaleStyle150()))
        }
        composeTestRule
            .onNodeWithTag("box")
            .assertWidthIsEqualTo(40.dp)
            .assertHeightIsEqualTo(40.dp)
    }

    @Test
    fun rotateZStyle_doesNotChangeMeasuredSize() {
        composeTestRule.setContent {
            Box(Modifier.testTag("box").styleable(style = Style.widthStyle(40.dp).heightStyle(40.dp).rotateZStyle(45f)))
        }
        composeTestRule
            .onNodeWithTag("box")
            .assertWidthIsEqualTo(40.dp)
            .assertHeightIsEqualTo(40.dp)
    }

    @Test
    fun translateXStyle4_doesNotChangeMeasuredSize() {
        composeTestRule.setContent {
            Box(Modifier.testTag("box").styleable(style = Style.widthStyle(40.dp).heightStyle(40.dp).translateXStyle4()))
        }
        composeTestRule
            .onNodeWithTag("box")
            .assertWidthIsEqualTo(40.dp)
            .assertHeightIsEqualTo(40.dp)
    }

    @Test
    fun zIndexStyle_doesNotChangeMeasuredSize() {
        composeTestRule.setContent {
            Box(Modifier.testTag("box").styleable(style = Style.widthStyle(40.dp).heightStyle(40.dp).zIndexStyle10()))
        }
        composeTestRule
            .onNodeWithTag("box")
            .assertWidthIsEqualTo(40.dp)
            .assertHeightIsEqualTo(40.dp)
    }
}
