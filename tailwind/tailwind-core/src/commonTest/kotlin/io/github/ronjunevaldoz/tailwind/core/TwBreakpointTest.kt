package io.github.ronjunevaldoz.tailwind.core

import androidx.compose.ui.unit.dp
import kotlin.test.Test
import kotlin.test.assertEquals

class TwBreakpointTest {
    @Test
    fun breakpoints_matchTailwindV4DefaultScale() {
        assertEquals(640.dp, TwBreakpoint.sm)
        assertEquals(768.dp, TwBreakpoint.md)
        assertEquals(1024.dp, TwBreakpoint.lg)
        assertEquals(1280.dp, TwBreakpoint.xl)
        assertEquals(1536.dp, TwBreakpoint.xl2)
    }
}
