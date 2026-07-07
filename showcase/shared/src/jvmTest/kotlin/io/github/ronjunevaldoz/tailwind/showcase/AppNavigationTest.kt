package io.github.ronjunevaldoz.tailwind.showcase

import androidx.compose.ui.graphics.toPixelMap
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToNode
import io.github.ronjunevaldoz.tailwind.core.TwColors
import org.junit.Rule
import kotlin.test.Test
import kotlin.test.assertEquals

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
                "Responsive" to "Responsive — twResponsive(sm:, md:, lg:, xl:)",
                // Substring of the explainer body, not the title — the title text is
                // identical to the sidebar label for explainer-only categories, which
                // would otherwise match two nodes (sidebar item + detail heading).
                "Cascade Layers" to "no stylesheet and no cascade",
                "P3 Colors" to "gamut-mapping and clipping down to 8-bit sRGB",
            )

        samples.forEach { (sidebarLabel, detailText) ->
            // The sidebar is a LazyColumn — items outside the viewport aren't composed,
            // so scroll the list itself until the target label appears before clicking.
            composeTestRule
                .onNodeWithTag(SIDEBAR_LIST_TEST_TAG)
                .performScrollToNode(hasText(sidebarLabel))
            composeTestRule.onNodeWithText(sidebarLabel).performClick()
            composeTestRule.onNode(hasText(detailText, substring = true)).assertIsDisplayed()
        }
    }

    @Test
    fun sectionToggle_switchesBetweenPreviewAndCode() {
        composeTestRule.setContent {
            App()
        }

        // Default category is Spacing, which has exactly one ShowcaseSection —
        // no ambiguity between multiple Preview/Code tab pairs on screen.
        composeTestRule.onNodeWithText("Code").performClick()
        composeTestRule.onNode(hasText("bgSlate200", substring = true)).assertIsDisplayed()

        composeTestRule.onNodeWithText("Preview").performClick()
        composeTestRule.onNode(hasText("bgSlate200", substring = true)).assertDoesNotExist()
    }

    @Test
    fun topBar_showsGitHubLinkAndBreadcrumb_forTheSelectedCategory() {
        composeTestRule.setContent {
            App()
        }

        composeTestRule.onNodeWithText("GitHub ↗").assertIsDisplayed()
        // Full breadcrumb text, not a "Spacing" substring match -- that also matches the
        // sidebar item and the ShowcaseSection title, both of which contain "Spacing" too.
        composeTestRule.onNodeWithText("Showcase / Spacing").assertIsDisplayed()
    }

    @Test
    fun darkModeToggle_reThemesTheAppShell() {
        composeTestRule.setContent {
            App()
        }

        val beforePixel =
            composeTestRule
                .onNodeWithTag(APP_ROOT_TEST_TAG)
                .captureToImage()
                .toPixelMap()[APP_ROOT_BG_PROBE_X, APP_ROOT_BG_PROBE_Y]
        assertEquals(TwColors.white, beforePixel, "expected the light-mode default before toggling")

        composeTestRule.onNodeWithTag(DARK_MODE_TOGGLE_TEST_TAG).performClick()

        val afterPixel =
            composeTestRule
                .onNodeWithTag(APP_ROOT_TEST_TAG)
                .captureToImage()
                .toPixelMap()[APP_ROOT_BG_PROBE_X, APP_ROOT_BG_PROBE_Y]
        assertEquals(TwColors.slate900, afterPixel, "expected the app shell to switch to dark mode")
    }

    private companion object {
        // A pixel inside the TopBar's own background, away from any text/icon/switch --
        // the TopBar is the outermost themed surface directly under the app-root node.
        const val APP_ROOT_BG_PROBE_X = 5
        const val APP_ROOT_BG_PROBE_Y = 5
    }
}
