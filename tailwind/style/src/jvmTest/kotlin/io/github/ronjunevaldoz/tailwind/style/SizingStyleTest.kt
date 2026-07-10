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

/** [SizingStyle]'s `widthStyle`/`heightStyle`. `minWidthStyle`/`maxWidthStyle` live in tailwind-style-experimental. */
@OptIn(ExperimentalFoundationStyleApi::class)
class SizingStyleTest {
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
}
