package io.github.ronjunevaldoz.tailwind.showcase.sections

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.github.ronjunevaldoz.tailwind.modifiers.bgSlate900
import io.github.ronjunevaldoz.tailwind.modifiers.bgWhite
import io.github.ronjunevaldoz.tailwind.modifiers.p4

/**
 * `twDark { }` reacts to the real system theme (see DarkMode.kt), which this demo
 * app can't flip on demand — this section simulates it with local state instead of
 * calling `twDark { }` directly, so the toggle below is illustrative, not the real API.
 */
@Suppress("ktlint:standard:function-naming")
@Composable
fun DarkModeShowcase() {
    ShowcaseSection(title = "Dark Mode — Modifier.bgWhite().twDark { bgSlate900() }") {
        var simulatedDark by remember { mutableStateOf(false) }
        Column {
            Box(Modifier.size(80.dp).let { if (simulatedDark) it.bgSlate900() else it.bgWhite() }.p4()) {
                Text(
                    if (simulatedDark) "dark" else "light",
                    color = if (simulatedDark) Color.White else Color.Black,
                )
            }
            Button(onClick = { simulatedDark = !simulatedDark }) {
                Text("Simulate toggle")
            }
        }
    }
}
