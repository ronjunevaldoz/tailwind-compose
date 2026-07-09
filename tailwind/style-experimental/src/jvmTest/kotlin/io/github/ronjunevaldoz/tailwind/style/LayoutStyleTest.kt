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

/** [SpacingStyle] and [SizingStyle], grouped since both drive measured size. */
@OptIn(ExperimentalFoundationStyleApi::class)
class LayoutStyleTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun widthStyleAndHeightStyle_setTheMeasuredSizeDirectly() {
        composeTestRule.setContent {
            Box(Modifier.testTag("box").styleable(style = Style.widthStyle(50.dp).heightStyle(30.dp)))
        }
        composeTestRule
            .onNodeWithTag("box")
            .assertWidthIsEqualTo(50.dp)
            .assertHeightIsEqualTo(30.dp)
    }

    @Test
    fun paddingStyle4_isIncludedInAnExplicitWidthRatherThanAddedOnTopOfIt() {
        // ContentPaddingScope's own KDoc: "The width/height of the component includes content
        // padding" -- border-box semantics, not CSS content-box (where padding would grow the
        // box beyond an explicit width). Confirmed empirically: a bare zero-content Box with no
        // styleable() at all still measures a non-zero default in this test environment, and
        // widthStyle(0.dp).heightStyle(0.dp).paddingStyle4() measures exactly 0.dp, not 8.dp --
        // so the only way to see paddingStyle4's own effect is to confirm it does *not* grow an
        // already-explicit width, not to measure it adding space to an implicit one.
        composeTestRule.setContent {
            Box(Modifier.testTag("box").styleable(style = Style.widthStyle(40.dp).heightStyle(40.dp).paddingStyle4()))
        }
        composeTestRule
            .onNodeWithTag("box")
            .assertWidthIsEqualTo(40.dp)
            .assertHeightIsEqualTo(40.dp)
    }

    @Test
    fun minWidthStyle_enforcesAFloorBelowTheRequestedWidth() {
        composeTestRule.setContent {
            Box(Modifier.testTag("box").styleable(style = Style.widthStyle(10.dp).minWidthStyle(40.dp)))
        }
        composeTestRule.onNodeWithTag("box").assertWidthIsEqualTo(40.dp)
    }

    @Test
    fun maxWidthStyle_enforcesACeilingAboveTheRequestedWidth() {
        composeTestRule.setContent {
            Box(Modifier.testTag("box").styleable(style = Style.widthStyle(100.dp).maxWidthStyle(40.dp)))
        }
        composeTestRule.onNodeWithTag("box").assertWidthIsEqualTo(40.dp)
    }
}
