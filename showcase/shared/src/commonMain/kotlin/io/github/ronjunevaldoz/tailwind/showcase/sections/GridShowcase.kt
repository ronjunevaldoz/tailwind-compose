package io.github.ronjunevaldoz.tailwind.showcase.sections

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.ronjunevaldoz.tailwind.modifiers.bgBlue500
import io.github.ronjunevaldoz.tailwind.modifiers.gridCols4

private const val SAMPLE_ITEM_COUNT = 8

/** gridCols4 — a fixed-column-count grid via LazyVerticalGrid. */
@Suppress("ktlint:standard:function-naming")
@Composable
fun GridShowcase() {
    ShowcaseSection(
        title = "Grid — gridCols4",
        code =
            """
            LazyVerticalGrid(columns = gridCols4(), modifier = Modifier.height(100.dp)) {
                items(8) {
                    Box(Modifier.size(20.dp).padding(2.dp).bgBlue500())
                }
            }
            """.trimIndent(),
    ) {
        LazyVerticalGrid(
            columns = gridCols4(),
            modifier = Modifier.height(100.dp).padding(top = 8.dp),
        ) {
            items(SAMPLE_ITEM_COUNT) {
                Box(Modifier.size(20.dp).padding(2.dp).bgBlue500())
            }
        }
    }
}
