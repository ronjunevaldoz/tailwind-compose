package io.github.ronjunevaldoz.tailwind.showcase.sections

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.style.ExperimentalFoundationStyleApi
import androidx.compose.foundation.style.Style
import androidx.compose.foundation.style.styleable
import androidx.compose.foundation.style.then
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import io.github.ronjunevaldoz.tailwind.modifiers.fontBold
import io.github.ronjunevaldoz.tailwind.modifiers.textSm
import io.github.ronjunevaldoz.tailwind.modifiers.textXl
import io.github.ronjunevaldoz.tailwind.modifiers.textXl2
import io.github.ronjunevaldoz.tailwind.modifiers.textXl3
import io.github.ronjunevaldoz.tailwind.style.fontSizeStyle
import io.github.ronjunevaldoz.tailwind.style.fontSizeStyleSm
import io.github.ronjunevaldoz.tailwind.style.fontSizeStyleXl
import io.github.ronjunevaldoz.tailwind.style.fontWeightStyleBold

/** textSm/textXl/textXl2/textXl3, and a bold variant for comparison. */
@OptIn(ExperimentalFoundationStyleApi::class)
@Suppress("ktlint:standard:function-naming")
@Composable
fun TypographyShowcase() {
    ShowcaseSection(
        title = "Typography — textSm, textXl, textXl2, textXl3",
        code =
            """
            Text("The quick brown fox", style = MaterialTheme.typography.bodyLarge.textSm())
            Text("The quick brown fox", style = MaterialTheme.typography.bodyLarge.textXl())
            Text("The quick brown fox", style = MaterialTheme.typography.bodyLarge.textXl2())
            Text("The quick brown fox", style = MaterialTheme.typography.bodyLarge.textXl3().fontBold())
            """.trimIndent(),
        styleCode =
            """
            // tailwind-style -- fontSizeStyle*()/fontWeightStyleBold(), the Style API's own
            // FontSizeScope/FontWeightScope, applied via BasicText (not Material3's Text --
            // the Style API styles a plain composable directly, no TextStyle merge needed).
            BasicText("The quick brown fox", modifier = Modifier.styleable(style = Style.fontSizeStyleSm()))
            BasicText("The quick brown fox", modifier = Modifier.styleable(style = Style.fontSizeStyleXl()))
            BasicText("The quick brown fox", modifier = Modifier.styleable(style = Style.fontSizeStyle(24.sp)))
            BasicText(
                "The quick brown fox",
                modifier = Modifier.styleable(style = Style.fontSizeStyle(30.sp).then(Style.fontWeightStyleBold())),
            )
            """.trimIndent(),
        styleContent = {
            Column {
                BasicText("The quick brown fox", modifier = Modifier.styleable(style = Style.fontSizeStyleSm()))
                BasicText("The quick brown fox", modifier = Modifier.styleable(style = Style.fontSizeStyleXl()))
                BasicText("The quick brown fox", modifier = Modifier.styleable(style = Style.fontSizeStyle(24.sp)))
                BasicText(
                    "The quick brown fox",
                    modifier =
                        Modifier.styleable(
                            style = Style.fontSizeStyle(30.sp).then(Style.fontWeightStyleBold()),
                        ),
                )
            }
        },
    ) {
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
