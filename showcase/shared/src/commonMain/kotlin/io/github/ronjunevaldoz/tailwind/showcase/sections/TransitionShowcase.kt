package io.github.ronjunevaldoz.tailwind.showcase.sections

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.ronjunevaldoz.tailwind.modifiers.bgBlue500
import io.github.ronjunevaldoz.tailwind.modifiers.transitionAllDuration300

/** transitionAllDuration300 — tap to animate a size change instead of snapping. */
@Suppress("ktlint:standard:function-naming")
@Composable
fun TransitionShowcase() {
    ShowcaseSection(
        title = "Transitions — transitionAllDuration300",
        code =
            """
            var expanded by remember { mutableStateOf(false) }
            Box(
                Modifier
                    .transitionAllDuration300()
                    .size(if (expanded) 80.dp else 40.dp)
                    .bgBlue500(),
            )
            Button(onClick = { expanded = !expanded }) { Text("Toggle size") }
            """.trimIndent(),
    ) {
        var expanded by remember { mutableStateOf(false) }
        Box(
            Modifier
                .transitionAllDuration300()
                .size(if (expanded) 80.dp else 40.dp)
                .bgBlue500(),
        )
        Button(onClick = { expanded = !expanded }) {
            Text("Toggle size")
        }
    }
}
