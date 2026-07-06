package io.github.ronjunevaldoz.tailwind.showcase.sections

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import org.junit.Rule
import kotlin.test.Test

class PaletteMatrixClickTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun tappingSwatch_revealsItsOklchValue() {
        composeTestRule.setContent { PaletteMatrixShowcase() }
        composeTestRule.onNodeWithContentDescription("red-500 swatch").performClick()
        composeTestRule
            .onNode(hasText("red-500", substring = true))
            .assertIsDisplayed()
    }
}
