package io.github.ronjunevaldoz.tailwind.showcase.sections

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.ronjunevaldoz.tailwind.modifiers.bgBlue500
import io.github.ronjunevaldoz.tailwind.modifiers.gap4
import io.github.ronjunevaldoz.tailwind.modifiers.scale75
import io.github.ronjunevaldoz.tailwind.modifiers.scale110
import io.github.ronjunevaldoz.tailwind.modifiers.translateX8
import io.github.ronjunevaldoz.tailwind.modifiers.translateXNeg8
import io.github.ronjunevaldoz.tailwind.modifiers.translateY8

/** scale75/scale110/translateX8/translateXNeg8/translateY8, each on its own swatch. */
@Suppress("ktlint:standard:function-naming")
@Composable
fun Transform2DShowcase() {
    ShowcaseSection(
        title = "2D Transforms — scale75, scale110, translateX8, translateXNeg8, translateY8",
        code =
            """
            Row(horizontalArrangement = gap4()) {
                Box(Modifier.size(50.dp).scale75().bgBlue500())
                Box(Modifier.size(50.dp).scale110().bgBlue500())
                Box(Modifier.size(50.dp).translateX8().bgBlue500())
                Box(Modifier.size(50.dp).translateXNeg8().bgBlue500())
                Box(Modifier.size(50.dp).translateY8().bgBlue500())
            }
            """.trimIndent(),
    ) {
        Row(horizontalArrangement = gap4()) {
            Box(Modifier.size(50.dp).scale75().bgBlue500())
            Box(Modifier.size(50.dp).scale110().bgBlue500())
            Box(Modifier.size(50.dp).translateX8().bgBlue500())
            Box(Modifier.size(50.dp).translateXNeg8().bgBlue500())
            Box(Modifier.size(50.dp).translateY8().bgBlue500())
        }
    }
}
