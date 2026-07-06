package io.github.ronjunevaldoz.tailwind.showcase.sections

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.ronjunevaldoz.tailwind.modifiers.bgBlue500
import io.github.ronjunevaldoz.tailwind.modifiers.currentTwWindowWidth
import io.github.ronjunevaldoz.tailwind.modifiers.fontMono
import io.github.ronjunevaldoz.tailwind.modifiers.textSm
import io.github.ronjunevaldoz.tailwind.modifiers.twResponsive
import kotlin.math.roundToInt

private const val SAMPLE_ITEM_COUNT = 16

/**
 * `twResponsive()` picking a grid column count from the *actual* window width — resize the
 * app window (or browser viewport) to see the column count change live at each breakpoint,
 * the same way Tailwind's `sm:grid-cols-3 md:grid-cols-4 lg:grid-cols-6` would.
 */
@Suppress("ktlint:standard:function-naming")
@Composable
fun ResponsiveShowcase() {
    ShowcaseSection(
        title = "Responsive — twResponsive(sm:, md:, lg:, xl:)",
        code =
            """
            val columns = twResponsive(base = 2, sm = 3, md = 4, lg = 6, xl = 8)
            LazyVerticalGrid(columns = GridCells.Fixed(columns)) {
                items(16) { Box(Modifier.size(20.dp).bgBlue500()) }
            }
            """.trimIndent(),
    ) {
        val width = currentTwWindowWidth()
        val columns = twResponsive(base = 2, sm = 3, md = 4, lg = 6, xl = 8)
        Text(
            "window width: ${width.value.roundToInt()}dp → $columns columns " +
                "(resize the window to see this change)",
            style =
                MaterialTheme.typography.bodySmall
                    .textSm()
                    .fontMono(),
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(columns),
            modifier = Modifier.height(100.dp).padding(top = 8.dp),
        ) {
            items(SAMPLE_ITEM_COUNT) {
                Box(Modifier.size(20.dp).padding(2.dp).bgBlue500())
            }
        }
    }
}
