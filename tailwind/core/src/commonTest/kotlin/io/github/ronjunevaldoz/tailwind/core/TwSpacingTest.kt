package io.github.ronjunevaldoz.tailwind.core

import androidx.compose.ui.unit.dp
import kotlin.test.Test
import kotlin.test.assertEquals

class TwSpacingTest {
    @Test
    fun scale_matchesDocumentedTailwindSteps() {
        assertEquals(0.dp, TwSpacing.scale0)
        assertEquals(1.dp, TwSpacing.scalePx)
        assertEquals(2.dp, TwSpacing.scale0p5)
        assertEquals(4.dp, TwSpacing.scale1)
        assertEquals(16.dp, TwSpacing.scale4)
        assertEquals(64.dp, TwSpacing.scale16)
        assertEquals(384.dp, TwSpacing.scale96)
    }

    @Test
    fun scaleMap_containsEveryStepByKey() {
        assertEquals(TwSpacing.scale0, TwSpacing.scale["0"])
        assertEquals(TwSpacing.scalePx, TwSpacing.scale["px"])
        assertEquals(TwSpacing.scale0p5, TwSpacing.scale["0_5"])
        assertEquals(TwSpacing.scale4, TwSpacing.scale["4"])
        assertEquals(TwSpacing.scale96, TwSpacing.scale["96"])
        assertEquals(35, TwSpacing.scale.size)
    }
}
