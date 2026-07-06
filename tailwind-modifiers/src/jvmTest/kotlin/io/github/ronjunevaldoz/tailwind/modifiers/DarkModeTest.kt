package io.github.ronjunevaldoz.tailwind.modifiers

import androidx.compose.foundation.layout.Box
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertHeightIsEqualTo
import androidx.compose.ui.test.assertWidthIsEqualTo
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import io.github.ronjunevaldoz.tailwind.core.TwColors
import org.junit.Rule
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * The JVM/Desktop Compose test harness defaults to light mode (verified via a
 * throwaway debug print during development: `isTwDarkTheme()` returns `false`
 * with no explicit system dark-mode override), so these tests only exercise the
 * "not in dark mode" passthrough branch — there's no reliable way to force dark
 * mode in this harness to test the other branch directly.
 */
class DarkModeTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun twDark_modifier_isNotAppliedOutsideDarkMode() {
        composeTestRule.setContent {
            Box(Modifier.testTag("box").size4().twDark { size8() })
        }
        // size4 == 16.dp, size8 == 32.dp — if twDark's block ran, this would be 32.dp.
        composeTestRule
            .onNodeWithTag("box")
            .assertWidthIsEqualTo(16.dp)
            .assertHeightIsEqualTo(16.dp)
    }

    @Test
    fun twDark_textStyle_isNotAppliedOutsideDarkMode() {
        var style: TextStyle? = null
        composeTestRule.setContent {
            style = TextStyle().textBlack().twDark { textWhite() }
        }
        assertEquals(TwColors.black, style?.color)
    }
}
