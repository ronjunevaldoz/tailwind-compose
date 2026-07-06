package io.github.ronjunevaldoz.tailwind.showcase.sections

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.ronjunevaldoz.tailwind.modifiers.bgBlue500
import io.github.ronjunevaldoz.tailwind.modifiers.gap4
import io.github.ronjunevaldoz.tailwind.modifiers.perspectiveNormal
import io.github.ronjunevaldoz.tailwind.modifiers.rotateX45
import io.github.ronjunevaldoz.tailwind.modifiers.rotateY45
import io.github.ronjunevaldoz.tailwind.modifiers.rotateZ45

/** rotateX45/rotateY45/rotateZ45, each with perspectiveNormal applied. */
@Suppress("ktlint:standard:function-naming")
@Composable
fun Transform3DShowcase() {
    ShowcaseSection(
        title = "3D Transforms — rotateX45, rotateY45, rotateZ45",
        code =
            """
            Row(horizontalArrangement = gap4()) {
                Box(Modifier.size(50.dp).perspectiveNormal().rotateX45().bgBlue500())
                Box(Modifier.size(50.dp).perspectiveNormal().rotateY45().bgBlue500())
                Box(Modifier.size(50.dp).perspectiveNormal().rotateZ45().bgBlue500())
            }
            """.trimIndent(),
    ) {
        Row(horizontalArrangement = gap4()) {
            Box(
                Modifier
                    .size(50.dp)
                    .perspectiveNormal()
                    .rotateX45()
                    .bgBlue500(),
            )
            Box(
                Modifier
                    .size(50.dp)
                    .perspectiveNormal()
                    .rotateY45()
                    .bgBlue500(),
            )
            Box(
                Modifier
                    .size(50.dp)
                    .perspectiveNormal()
                    .rotateZ45()
                    .bgBlue500(),
            )
        }
    }
}
