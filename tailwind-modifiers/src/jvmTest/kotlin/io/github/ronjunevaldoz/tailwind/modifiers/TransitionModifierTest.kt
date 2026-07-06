package io.github.ronjunevaldoz.tailwind.modifiers

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.unit.dp
import org.junit.Rule
import kotlin.test.Test
import kotlin.test.assertTrue

class TransitionModifierTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun transitionAllDuration300_animatesBetweenSizesRatherThanSnapping() {
        composeTestRule.mainClock.autoAdvance = false
        var expanded by mutableStateOf(false)

        composeTestRule.setContent {
            Box(
                Modifier
                    .testTag("box")
                    .transitionAllDuration300()
                    .size(if (expanded) 100.dp else 20.dp),
            )
        }
        composeTestRule.mainClock.advanceTimeByFrame()
        composeTestRule.onNodeWithTag("box").fetchSemanticsNode().size // warm up layout

        expanded = true
        composeTestRule.mainClock.advanceTimeByFrame()
        composeTestRule.mainClock.advanceTimeBy(150L) // halfway through the 300ms transition

        val widthDuringTransition =
            composeTestRule
                .onNodeWithTag("box")
                .fetchSemanticsNode()
                .size.width

        // Widths are in pixels at test density; 20.dp and 100.dp bound the animation,
        // so anything strictly between confirms it's animating, not snapping.
        val startPx = with(composeTestRule.density) { 20.dp.toPx() }
        val endPx = with(composeTestRule.density) { 100.dp.toPx() }
        assertTrue(
            widthDuringTransition > startPx && widthDuringTransition < endPx,
            "expected a mid-transition width between $startPx and $endPx but was $widthDuringTransition",
        )
    }
}
