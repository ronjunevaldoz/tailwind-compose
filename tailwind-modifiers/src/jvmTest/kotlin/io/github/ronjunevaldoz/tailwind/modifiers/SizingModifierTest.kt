package io.github.ronjunevaldoz.tailwind.modifiers

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
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
 * TwSpacing.scale4 == 16.dp — used throughout as the reference step under test.
 */
class SizingModifierTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun w4_setsExplicitWidth() {
        composeTestRule.setContent {
            Box(Modifier.testTag("box").w4())
        }
        composeTestRule.onNodeWithTag("box").assertWidthIsEqualTo(16.dp)
    }

    @Test
    fun h4_setsExplicitHeight() {
        composeTestRule.setContent {
            Box(Modifier.testTag("box").h4())
        }
        composeTestRule.onNodeWithTag("box").assertHeightIsEqualTo(16.dp)
    }

    @Test
    fun size4_setsBothDimensions() {
        composeTestRule.setContent {
            Box(Modifier.testTag("box").size4())
        }
        composeTestRule
            .onNodeWithTag("box")
            .assertWidthIsEqualTo(16.dp)
            .assertHeightIsEqualTo(16.dp)
    }

    @Test
    fun minW4_enforcesMinimumWidthOnEmptyContent() {
        composeTestRule.setContent {
            Box(Modifier.testTag("box").minW4())
        }
        composeTestRule.onNodeWithTag("box").assertWidthIsEqualTo(16.dp)
    }

    @Test
    fun maxW4_clampsWidthOfOversizedContent() {
        composeTestRule.setContent {
            Box(Modifier.testTag("box").maxW4()) {
                Box(Modifier.size(999.dp))
            }
        }
        composeTestRule.onNodeWithTag("box").assertWidthIsEqualTo(16.dp)
    }

    @Test
    fun wFull_fillsAvailableWidth() {
        composeTestRule.setContent {
            Box(Modifier.testTag("outer").size(200.dp)) {
                Box(Modifier.testTag("inner").wFull())
            }
        }
        composeTestRule.onNodeWithTag("inner").assertWidthIsEqualTo(200.dp)
    }
}
