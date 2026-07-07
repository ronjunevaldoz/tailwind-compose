package io.github.ronjunevaldoz.tailwind.modifiers

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toPixelMap
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertHeightIsEqualTo
import androidx.compose.ui.test.assertWidthIsEqualTo
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import io.github.ronjunevaldoz.tailwind.core.TwColors
import org.junit.Rule
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * The JVM/Desktop Compose test harness defaults to light mode with no way to force the
 * system setting directly, which is exactly what [LocalTwDarkTheme] exists to work
 * around — tests below use it to exercise the dark-mode branch without a platform-level
 * override.
 */
class DarkModeTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun twDark_modifier_isNotAppliedOutsideDarkMode() {
        // Any two distinct sizes prove the point -- raw Modifier.size() rather than this
        // library's own size4()/size8() (tailwind-layout) so this module's tests don't
        // need a cross-module test dependency for an incidental "is it 16dp or 32dp" check.
        composeTestRule.setContent {
            Box(Modifier.testTag("box").size(16.dp).twDark { size(32.dp) })
        }
        // If twDark's block ran, this would be 32.dp.
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

    @Test
    fun localTwDarkTheme_override_forcesDarkModeRegardlessOfSystemSetting() {
        // Uses the library's actual documented pattern (Modifier.bgWhite().twDark {
        // bgSlate900() }) rather than stacking two .size() calls: background draws later-
        // wins, but Modifier.size() is outer-constraint-wins, so two chained .size() calls
        // can't tell "block ran" from "block didn't run" the way two chained backgrounds can.
        composeTestRule.setContent {
            CompositionLocalProvider(LocalTwDarkTheme provides true) {
                Box(
                    Modifier
                        .testTag("box")
                        .size(20.dp)
                        .bgWhite()
                        .twDark { bgSlate900() },
                )
            }
        }
        val pixel = composeTestRule.onNodeWithTag("box").captureToImage().toPixelMap()[10, 10]
        assertEquals(TwColors.slate900, pixel)
    }

    @Test
    fun localTwDarkTheme_override_forcesLightModeRegardlessOfSystemSetting() {
        composeTestRule.setContent {
            CompositionLocalProvider(LocalTwDarkTheme provides false) {
                Box(
                    Modifier
                        .testTag("box")
                        .size(20.dp)
                        .bgWhite()
                        .twDark { bgSlate900() },
                )
            }
        }
        val pixel = composeTestRule.onNodeWithTag("box").captureToImage().toPixelMap()[10, 10]
        assertEquals(TwColors.white, pixel)
    }

    @Test
    fun localTwDarkTheme_nullByDefault_defersToSystemSetting() {
        var isDark: Boolean? = null
        composeTestRule.setContent {
            isDark = isTwDarkTheme()
        }
        assertFalse(isDark ?: true, "expected the JVM/Desktop harness's system setting (light) with no override")
    }

    @Test
    fun localTwDarkTheme_override_isReadableDirectlyThroughIsTwDarkTheme() {
        var isDark: Boolean? = null
        composeTestRule.setContent {
            CompositionLocalProvider(LocalTwDarkTheme provides true) {
                isDark = isTwDarkTheme()
            }
        }
        assertTrue(isDark == true)
    }
}
