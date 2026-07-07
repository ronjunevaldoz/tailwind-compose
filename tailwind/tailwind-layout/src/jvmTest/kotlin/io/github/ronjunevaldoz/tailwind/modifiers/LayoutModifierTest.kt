package io.github.ronjunevaldoz.tailwind.modifiers

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertHeightIsEqualTo
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.unit.dp
import org.junit.Rule
import kotlin.test.Test

class LayoutModifierTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun aspectSquare_makesHeightMatchWidth() {
        composeTestRule.setContent {
            Box(Modifier.testTag("box").width(60.dp).aspectSquare())
        }
        composeTestRule.onNodeWithTag("box").assertHeightIsEqualTo(60.dp)
    }

    @Test
    fun aspectVideo_makesHeightSixteenByNine() {
        composeTestRule.setContent {
            Box(Modifier.testTag("box").width(160.dp).aspectVideo())
        }
        composeTestRule.onNodeWithTag("box").assertHeightIsEqualTo(90.dp)
    }
}
