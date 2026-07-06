package io.github.ronjunevaldoz.tailwind.core

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import kotlin.test.Test
import kotlin.test.assertEquals

class TwTypographyTest {
    @Test
    fun fontSize_matchesDocumentedScale() {
        assertEquals(12.sp, TwFontSize.xs)
        assertEquals(16.sp, TwFontSize.base)
        assertEquals(24.sp, TwFontSize.xl2)
        assertEquals(128.sp, TwFontSize.xl9)
    }

    @Test
    fun lineHeight_pairsWithFontSize() {
        assertEquals(16.sp, TwLineHeight.xs)
        assertEquals(24.sp, TwLineHeight.base)
    }

    @Test
    fun fontWeight_aliasesStandardValues() {
        assertEquals(FontWeight.Normal, TwFontWeight.normal)
        assertEquals(FontWeight.Bold, TwFontWeight.bold)
        assertEquals(FontWeight.Black, TwFontWeight.black)
    }
}
