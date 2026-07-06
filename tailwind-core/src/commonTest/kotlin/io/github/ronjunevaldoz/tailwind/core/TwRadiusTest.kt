package io.github.ronjunevaldoz.tailwind.core

import androidx.compose.ui.unit.dp
import kotlin.test.Test
import kotlin.test.assertEquals

class TwRadiusTest {
    @Test
    fun radius_matchesDocumentedScale() {
        assertEquals(0.dp, TwRadius.none)
        assertEquals(4.dp, TwRadius.base)
        assertEquals(8.dp, TwRadius.lg)
        assertEquals(24.dp, TwRadius.xl3)
    }
}
