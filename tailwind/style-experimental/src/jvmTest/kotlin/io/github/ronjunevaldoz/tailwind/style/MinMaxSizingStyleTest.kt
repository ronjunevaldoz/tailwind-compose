package io.github.ronjunevaldoz.tailwind.style

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.style.ExperimentalFoundationStyleApi
import androidx.compose.foundation.style.Style
import androidx.compose.foundation.style.styleable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertWidthIsEqualTo
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.unit.dp
import org.junit.Rule
import kotlin.test.Test

/** [MinMaxSizingStyle]'s `minWidthStyle`/`maxWidthStyle`. Genuinely need 1.12.0-beta01 -- see that file's KDoc. */
@OptIn(ExperimentalFoundationStyleApi::class)
class MinMaxSizingStyleTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    // widthStyle lives in :tailwind-style, which this module can't depend on at runtime --
    // see build.gradle.kts's comment -- so these set the starting width inline via the same
    // StyleScope.width(...) call it wraps.

    @Test
    fun minWidthStyle_enforcesAFloorBelowTheRequestedWidth() {
        composeTestRule.setContent {
            Box(Modifier.testTag("box").styleable(style = Style { width(10.dp) }.minWidthStyle(40.dp)))
        }
        composeTestRule.onNodeWithTag("box").assertWidthIsEqualTo(40.dp)
    }

    @Test
    fun maxWidthStyle_enforcesACeilingAboveTheRequestedWidth() {
        composeTestRule.setContent {
            Box(Modifier.testTag("box").styleable(style = Style { width(100.dp) }.maxWidthStyle(40.dp)))
        }
        composeTestRule.onNodeWithTag("box").assertWidthIsEqualTo(40.dp)
    }
}
