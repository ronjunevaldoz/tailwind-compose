package io.github.ronjunevaldoz.tailwind.style

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.style.ExperimentalFoundationStyleApi
import androidx.compose.foundation.style.Style
import androidx.compose.foundation.style.styleable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toPixelMap
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.unit.dp
import org.junit.Rule
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

/** [FilterStyle]. Only the `colorFilter` StyleScope property here needs 1.12.0-beta01. */
@OptIn(ExperimentalFoundationStyleApi::class)
class FilterStyleTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun grayscaleStyle_removesColorSaturation() {
        composeTestRule.setContent {
            // Style.bgStyle (background helper) lives in :tailwind-style, which this module
            // can't depend on at runtime -- see build.gradle.kts's comment -- so this builds
            // the background inline via the same StyleScope.background(...) call bgStyle wraps.
            Box(Modifier.testTag("box").size(40.dp).styleable(style = Style { background(Color.Red) }.grayscaleStyle()))
        }
        val center = composeTestRule.onNodeWithTag("box").captureToImage().toPixelMap()[20, 20]
        assertNotEquals(Color.Red, center, "expected grayscale to desaturate the red background")
        assertEquals(center.red, center.green, 0.01f)
        assertEquals(center.green, center.blue, 0.01f)
    }

    private fun assertEquals(
        expected: Float,
        actual: Float,
        tolerance: Float,
    ) {
        assert(kotlin.math.abs(expected - actual) <= tolerance) { "expected $actual to be within $tolerance of $expected" }
    }
}
