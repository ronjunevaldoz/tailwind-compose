package io.github.ronjunevaldoz.tailwind.modifiers

import androidx.compose.foundation.layout.Box
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

class BorderModifierTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun border2_doesNotChangeMeasuredSize() {
        composeTestRule.setContent {
            Box(Modifier.testTag("box").size(50.dp).border2(TwColors.blue500))
        }
        composeTestRule
            .onNodeWithTag("box")
            .assertWidthIsEqualTo(50.dp)
            .assertHeightIsEqualTo(50.dp)
    }

    @Test
    fun roundedFull_clipsCorners() {
        // A fully-rounded square clips its corners, so the corner pixel should
        // no longer be the fill color even though the center still is.
        composeTestRule.setContent {
            Box(
                Modifier
                    .testTag("box")
                    .size(40.dp)
                    .roundedFull()
                    .bgBlue500(),
            )
        }
        val image = composeTestRule.onNodeWithTag("box").captureToImage().toPixelMap()
        val corner = image[0, 0]
        val center = image[20, 20]
        assert(corner != TwColors.blue500) { "expected corner to be clipped, but was $corner" }
        assert(center == TwColors.blue500) { "expected center to remain filled, but was $center" }
    }

    @Test
    fun border4_withExplicitRoundedShape_clipsBorderAtCorners() {
        // Passing a shape directly to border4() must round the border stroke itself,
        // independent of any surrounding clip() -- there is none here.
        composeTestRule.setContent {
            Box(
                Modifier
                    .testTag("box")
                    .size(40.dp)
                    .border4(TwColors.blue500, RoundedCornerShape(TwRadius.full)),
            )
        }
        val corner = composeTestRule.onNodeWithTag("box").captureToImage().toPixelMap()[0, 0]
        assert(corner != TwColors.blue500) {
            "expected the border to be clipped to the rounded shape at the corner, but was $corner"
        }
    }
}
