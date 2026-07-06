package io.github.ronjunevaldoz.tailwind.showcase

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import kotlin.test.Test

/**
 * Clicks through a representative sample of sidebar categories (one utility-backed
 * screen per distinct code path — Brush, LazyVerticalGrid, graphicsLayer, animation,
 * simulated state — plus one text-only explainer) to catch integration failures a
 * per-section unit test can't: does the sidebar actually navigate, does the detail
 * pane recompose without crashing for each `CategoryDetail` branch.
 */
class AppNavigationTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun sidebar_navigatesToEachSampledCategoryWithoutCrashing() {
        composeTestRule.setContent {
            App()
        }

        val samples =
            listOf(
                "Gradients" to "Gradients — bgGradientToR, bgGradientToB",
                "Grid" to "Grid — gridCols4",
                "3D Transforms" to "3D Transforms — rotateX45, rotateY45, rotateZ45",
                "Dark Mode" to "Dark Mode — Modifier.bgWhite().twDark { bgSlate900() }",
                // Substring of the explainer body, not the title — the title text is
                // identical to the sidebar label for explainer-only categories, which
                // would otherwise match two nodes (sidebar item + detail heading).
                "Cascade Layers" to "no stylesheet and no cascade",
            )

        samples.forEach { (sidebarLabel, detailText) ->
            composeTestRule.onNodeWithText(sidebarLabel).performClick()
            composeTestRule.onNode(hasText(detailText, substring = true)).assertIsDisplayed()
        }
    }
}
