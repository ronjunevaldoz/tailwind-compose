package io.github.ronjunevaldoz.tailwind.style

import androidx.compose.foundation.layout.width
import androidx.compose.foundation.style.ExperimentalFoundationStyleApi
import androidx.compose.foundation.style.Style
import androidx.compose.foundation.style.styleable
import androidx.compose.foundation.text.BasicText
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import org.junit.Rule
import kotlin.test.Test

/**
 * [TypographyStyle]. Font rendering itself isn't pixel-verified here (that needs golden-image
 * tooling this module doesn't have set up) -- each property is checked for the thing a
 * `StyleScope` extension *can* break structurally: applying it doesn't crash and the styled
 * text still renders.
 */
@OptIn(ExperimentalFoundationStyleApi::class)
class TextStyleApiTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun everyTypographyStyleProperty_appliesWithoutCrashingAndStaysDisplayed() {
        val fullStyle =
            Style
                .fontSizeStyleLg()
                .lineHeightStyleBase()
                .fontWeightStyle(FontWeight.Bold)
                .letterSpacingStyleWide()
                .textAlignStyle(TextAlign.Center)
                .textDecorationStyle(TextDecoration.Underline)
                .fontFamilyStyleMono()
                .textColorStyle(Color.Red)

        composeTestRule.setContent {
            BasicText(
                "tailwind-style-experimental",
                modifier = Modifier.testTag("text").width(200.dp).styleable(style = fullStyle),
            )
        }
        composeTestRule.onNodeWithTag("text").assertIsDisplayed()
    }
}
