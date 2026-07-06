package io.github.ronjunevaldoz.tailwind.showcase.sections

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import io.github.ronjunevaldoz.tailwind.modifiers.fontBold
import io.github.ronjunevaldoz.tailwind.modifiers.textSm
import io.github.ronjunevaldoz.tailwind.modifiers.textXl
import io.github.ronjunevaldoz.tailwind.modifiers.textXl2
import io.github.ronjunevaldoz.tailwind.modifiers.textXl3

/** textSm/textXl/textXl2/textXl3, and a bold variant for comparison. */
@Suppress("ktlint:standard:function-naming")
@Composable
fun TypographyShowcase() {
    ShowcaseSection(title = "Typography — textSm, textXl, textXl2, textXl3") {
        Column {
            Text("The quick brown fox", style = MaterialTheme.typography.bodyLarge.textSm())
            Text("The quick brown fox", style = MaterialTheme.typography.bodyLarge.textXl())
            Text("The quick brown fox", style = MaterialTheme.typography.bodyLarge.textXl2())
            Text(
                "The quick brown fox",
                style =
                    MaterialTheme.typography.bodyLarge
                        .textXl3()
                        .fontBold(),
            )
        }
    }
}
