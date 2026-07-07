package io.github.ronjunevaldoz.tailwind.modifiers

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toPixelMap
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import io.github.ronjunevaldoz.tailwind.core.TwColors
import org.junit.Rule
import kotlin.test.Test
import kotlin.test.assertEquals

class ColorModifierTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun bgBlue500_paintsTheExpectedColor() {
        composeTestRule.setContent {
            Box(Modifier.testTag("box").size(20.dp).bgBlue500())
        }
        val pixel =
            composeTestRule
                .onNodeWithTag("box")
                .captureToImage()
                .toPixelMap()[10, 10]
        assertEquals(TwColors.blue500, pixel)
    }

    @Test
    fun bgRed500_paintsTheExpectedColor() {
        composeTestRule.setContent {
            Box(Modifier.testTag("box").size(20.dp).bgRed500())
        }
        val pixel =
            composeTestRule
                .onNodeWithTag("box")
                .captureToImage()
                .toPixelMap()[10, 10]
        assertEquals(TwColors.red500, pixel)
    }

    @Test
    fun textBlue500_setsTextStyleColor() {
        val style = TextStyle().textBlue500()
        assertEquals(TwColors.blue500, style.color)
    }

    @Test
    fun textWhite_setsTextStyleColor() {
        val style = TextStyle().textWhite()
        assertEquals(TwColors.white, style.color)
    }

    @Test
    fun bg_arbitraryValue_paintsTheGivenColor() {
        composeTestRule.setContent {
            Box(Modifier.testTag("box").size(20.dp).bg(TwColors.emerald400))
        }
        val pixel =
            composeTestRule
                .onNodeWithTag("box")
                .captureToImage()
                .toPixelMap()[10, 10]
        assertEquals(TwColors.emerald400, pixel)
    }

    @Test
    fun text_arbitraryValue_setsTextStyleColor() {
        val style = TextStyle().text(TwColors.emerald400)
        assertEquals(TwColors.emerald400, style.color)
    }
}
