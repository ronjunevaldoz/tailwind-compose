package io.github.ronjunevaldoz.tailwind.core

import androidx.compose.ui.graphics.Color
import kotlin.test.Test
import kotlin.test.assertEquals

class TwColorsTest {
    @Test
    fun spotCheck_wellKnownShades() {
        assertEquals(Color(0xFF3B82F6), TwColors.blue500)
        assertEquals(Color(0xFFEF4444), TwColors.red500)
        assertEquals(Color(0xFF22C55E), TwColors.green500)
        assertEquals(Color(0xFF0F172A), TwColors.slate900)
        assertEquals(Color(0xFFFFFFFF), TwColors.white)
        assertEquals(Color(0xFF000000), TwColors.black)
    }

    @Test
    fun spotCheck_edgeShades() {
        assertEquals(Color(0xFFF8FAFC), TwColors.slate50)
        assertEquals(Color(0xFF020617), TwColors.slate950)
        assertEquals(Color(0xFFFFF1F2), TwColors.rose50)
        assertEquals(Color(0xFF4C0519), TwColors.rose950)
    }
}
