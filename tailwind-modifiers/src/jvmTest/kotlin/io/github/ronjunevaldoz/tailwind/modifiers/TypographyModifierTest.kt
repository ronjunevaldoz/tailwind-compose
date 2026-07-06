package io.github.ronjunevaldoz.tailwind.modifiers

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import io.github.ronjunevaldoz.tailwind.core.TwFontSize
import io.github.ronjunevaldoz.tailwind.core.TwLineHeight
import kotlin.test.Test
import kotlin.test.assertEquals

class TypographyModifierTest {
    @Test
    fun textLg_setsFontSizeAndPairedLineHeight() {
        val style = TextStyle().textLg()
        assertEquals(TwFontSize.lg, style.fontSize)
        assertEquals(TwLineHeight.lg, style.lineHeight)
    }

    @Test
    fun textXl9_setsLargestFontSize() {
        val style = TextStyle().textXl9()
        assertEquals(128.sp, style.fontSize)
    }

    @Test
    fun fontBold_setsBoldWeight() {
        val style = TextStyle().fontBold()
        assertEquals(FontWeight.Bold, style.fontWeight)
    }

    @Test
    fun trackingWide_setsPositiveLetterSpacing() {
        val style = TextStyle().trackingWide()
        assertEquals(0.025f.em, style.letterSpacing)
    }

    @Test
    fun textAlignCenter_setsCenterAlignment() {
        val style = TextStyle().textAlignCenter()
        assertEquals(TextAlign.Center, style.textAlign)
    }

    @Test
    fun fontMono_setsMonospaceFamily() {
        val style = TextStyle().fontMono()
        assertEquals(FontFamily.Monospace, style.fontFamily)
    }

    @Test
    fun fontSans_setsSansSerifFamily() {
        val style = TextStyle().fontSans()
        assertEquals(FontFamily.SansSerif, style.fontFamily)
    }

    @Test
    fun chainedModifiers_composeTogether() {
        val style = TextStyle().textLg().fontBold().textAlignCenter()
        assertEquals(TwFontSize.lg, style.fontSize)
        assertEquals(FontWeight.Bold, style.fontWeight)
        assertEquals(TextAlign.Center, style.textAlign)
    }
}
