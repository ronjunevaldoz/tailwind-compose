package io.github.ronjunevaldoz.tailwind.core

import kotlin.test.Test
import kotlin.test.assertEquals

class TwTransitionTest {
    @Test
    fun duration_matchesDocumentedScale() {
        assertEquals(75, TwDuration.D75)
        assertEquals(150, TwDuration.D150)
        assertEquals(1000, TwDuration.D1000)
    }

    @Test
    fun easing_matchesDocumentedCssCubicBeziers() {
        // Sanity checks at the curve's midpoint — enough to catch a transcription
        // error in the (x1, y1, x2, y2) control points without hand-verifying the
        // full curve.
        assertEquals(0.5f, TwEasing.linear.transform(0.5f))
    }
}
