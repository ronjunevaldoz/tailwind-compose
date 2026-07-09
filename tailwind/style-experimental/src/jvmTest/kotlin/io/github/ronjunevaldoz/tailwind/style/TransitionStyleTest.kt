package io.github.ronjunevaldoz.tailwind.style

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.style.ExperimentalFoundationStyleApi
import androidx.compose.foundation.style.Style
import androidx.compose.foundation.style.styleable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.unit.dp
import org.junit.Rule
import kotlin.test.Test

/**
 * [TransitionStyle]. `AnimateStyleScope.animate` only does anything when a wrapped property
 * changes as a result of [androidx.compose.foundation.style.StyleState] driving a different
 * style branch -- there's no state change to animate here, so this is a smoke test that wrapping
 * a style in [Style.transitionStyle] doesn't break applying it, not a test of the animation
 * itself.
 */
@OptIn(ExperimentalFoundationStyleApi::class)
class TransitionStyleTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun transitionStyle_appliesWithoutCrashingAndStaysDisplayed() {
        composeTestRule.setContent {
            Box(
                Modifier
                    .testTag("box")
                    .size(40.dp)
                    .styleable(
                        style =
                            Style.transitionStyle {
                                background(Color.Blue)
                            },
                    ),
            )
        }
        composeTestRule.onNodeWithTag("box").assertIsDisplayed()
    }
}
