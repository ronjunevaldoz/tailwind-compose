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

/** [SpacingStyle]. Only the `contentPadding`/`externalPadding` StyleScope properties here need 1.12.0-beta01. */
@OptIn(ExperimentalFoundationStyleApi::class)
class SpacingStyleTest {
    @get:Rule
    val composeTestRule = createComposeRule()

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
            // widthStyle/heightStyle live in :tailwind-style, which this module can't depend
            // on at runtime -- see build.gradle.kts's comment -- so this sets the explicit
            // size inline via the same StyleScope.width/height(...) calls those wrap.
            val fixedSize =
                Style {
                    width(40.dp)
                    height(40.dp)
                }
            Box(Modifier.testTag("box").styleable(style = fixedSize.paddingStyle4()))
        }
        composeTestRule
            .onNodeWithTag("box")
            .assertWidthIsEqualTo(40.dp)
            .assertHeightIsEqualTo(40.dp)
    }
}
