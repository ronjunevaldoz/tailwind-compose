package io.github.ronjunevaldoz.tailwind.modifiers

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toPixelMap
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.unit.dp
import io.github.ronjunevaldoz.tailwind.core.TwColors
import io.github.ronjunevaldoz.tailwind.core.TwRadius
import io.github.ronjunevaldoz.tailwind.core.TwShadow
import org.junit.Rule
import kotlin.test.Test
import kotlin.test.assertNotEquals

class CardModifierTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun twCard_clipsBackgroundAtCorners() {
        composeTestRule.setContent {
            Box(
                Modifier
                    .testTag("card")
                    .size(40.dp)
                    .twCard(shape = RoundedCornerShape(TwRadius.full), color = TwColors.blue500),
            )
        }
        val corner = composeTestRule.onNodeWithTag("card").captureToImage().toPixelMap()[0, 0]
        assertNotEquals(TwColors.blue500, corner, "expected the background to be clipped at the corner")
    }

    @Test
    fun twCard_castsAShadowMatchingItsOwnShape() {
        // Same technique as ShadowModifierTest.shadowLg_paintsBeyondTheBoxIntoTheSurroundingArea:
        // capture from a parent with room around the card, since the shadow paints outside
        // the card's own layout bounds.
        composeTestRule.setContent {
            Box(Modifier.testTag("parent").padding(20.dp).size(100.dp)) {
                Box(Modifier.size(60.dp).twCard(shadowElevation = TwShadow.lg))
            }
        }
        val pixels = composeTestRule.onNodeWithTag("parent").captureToImage().toPixelMap()
        assertNotEquals(0f, pixels[15, 15].alpha, "expected a visible shadow tint just outside the card")
    }
}
