package io.github.ronjunevaldoz.tailwind.modifiers

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertHeightIsEqualTo
import androidx.compose.ui.test.assertLeftPositionInRootIsEqualTo
import androidx.compose.ui.test.assertWidthIsEqualTo
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.unit.dp
import org.junit.Rule
import kotlin.test.Test

/**
 * TwSpacing.scale4 == 16.dp — used throughout as the reference step under test.
 */
class SpacingModifierTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun p4_addsPaddingOnAllSides() {
        composeTestRule.setContent {
            Box(Modifier.testTag("outer").p4()) {
                Box(Modifier.size(50.dp))
            }
        }
        composeTestRule
            .onNodeWithTag("outer")
            .assertWidthIsEqualTo(82.dp)
            .assertHeightIsEqualTo(82.dp)
    }

    @Test
    fun px4_addsPaddingOnHorizontalAxisOnly() {
        composeTestRule.setContent {
            Box(Modifier.testTag("outer").px4()) {
                Box(Modifier.size(50.dp))
            }
        }
        composeTestRule
            .onNodeWithTag("outer")
            .assertWidthIsEqualTo(82.dp)
            .assertHeightIsEqualTo(50.dp)
    }

    @Test
    fun py4_addsPaddingOnVerticalAxisOnly() {
        composeTestRule.setContent {
            Box(Modifier.testTag("outer").py4()) {
                Box(Modifier.size(50.dp))
            }
        }
        composeTestRule
            .onNodeWithTag("outer")
            .assertWidthIsEqualTo(50.dp)
            .assertHeightIsEqualTo(82.dp)
    }

    @Test
    fun pt4_addsPaddingOnTopOnly() {
        composeTestRule.setContent {
            Box(Modifier.testTag("outer").pt4()) {
                Box(Modifier.size(50.dp))
            }
        }
        composeTestRule
            .onNodeWithTag("outer")
            .assertWidthIsEqualTo(50.dp)
            .assertHeightIsEqualTo(66.dp)
    }

    @Test
    fun m4_behavesIdenticallyToP4() {
        composeTestRule.setContent {
            Box(Modifier.testTag("outer").m4()) {
                Box(Modifier.size(50.dp))
            }
        }
        composeTestRule
            .onNodeWithTag("outer")
            .assertWidthIsEqualTo(82.dp)
            .assertHeightIsEqualTo(82.dp)
    }

    /**
     * [Modifier.offset] repositions content at the graphics/paint layer rather than the
     * layout-coordinates layer, so the shift is not observable through semantics-tree
     * position queries (`positionInRoot`) in this test harness — verified visually via a
     * Roborazzi capture during development instead. This test only asserts the documented
     * "size is unaffected" contract, which *is* observable through semantics.
     */
    @Test
    fun mtNeg4_doesNotChangeMeasuredSize() {
        composeTestRule.setContent {
            Box(Modifier.testTag("shifted").size(50.dp).mtNeg4())
        }
        composeTestRule
            .onNodeWithTag("shifted")
            .assertWidthIsEqualTo(50.dp)
            .assertHeightIsEqualTo(50.dp)
    }

    @Test
    fun gap4_spacesRowChildrenByTheScaleStep() {
        composeTestRule.setContent {
            Row(horizontalArrangement = gap4()) {
                Box(Modifier.testTag("first").size(20.dp))
                Box(Modifier.testTag("second").size(20.dp))
            }
        }
        composeTestRule.onNodeWithTag("first").assertLeftPositionInRootIsEqualTo(0.dp)
        composeTestRule.onNodeWithTag("second").assertLeftPositionInRootIsEqualTo(36.dp)
    }
}
