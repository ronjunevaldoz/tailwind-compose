package io.github.ronjunevaldoz.tailwind.showcase

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onRoot
import io.github.takahirom.roborazzi.captureRoboImage
import org.junit.Rule
import kotlin.test.Test

/**
 * Foundation-sprint baseline golden for the showcase app shell. Real per-utility
 * showcase screens (spacing, color, typography, borders) replace App() in a later sprint.
 */
class AppScreenshotTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun app_default() {
        composeTestRule.setContent {
            App()
        }
        composeTestRule.onRoot().captureRoboImage("src/jvmTest/snapshots/App/app_default.png")
    }
}
