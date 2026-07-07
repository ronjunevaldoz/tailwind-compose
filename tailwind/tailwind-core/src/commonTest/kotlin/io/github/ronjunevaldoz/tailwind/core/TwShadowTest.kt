package io.github.ronjunevaldoz.tailwind.core

import androidx.compose.ui.unit.dp
import kotlin.test.Test
import kotlin.test.assertEquals

class TwShadowTest {
    @Test
    fun shadow_matchesDocumentedApproximatedScale() {
        assertEquals(0.dp, TwShadow.none)
        assertEquals(1.dp, TwShadow.sm)
        assertEquals(3.dp, TwShadow.base)
        assertEquals(6.dp, TwShadow.md)
        assertEquals(10.dp, TwShadow.lg)
        assertEquals(15.dp, TwShadow.xl)
        assertEquals(25.dp, TwShadow.xl2)
    }
}
