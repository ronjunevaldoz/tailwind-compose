package io.github.ronjunevaldoz.tailwind.showcase

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.ronjunevaldoz.tailwind.modifiers.bgWhite
import io.github.ronjunevaldoz.tailwind.modifiers.fontBold
import io.github.ronjunevaldoz.tailwind.modifiers.p4
import io.github.ronjunevaldoz.tailwind.modifiers.textXl2
import io.github.ronjunevaldoz.tailwind.showcase.sections.AspectRatioShowcase
import io.github.ronjunevaldoz.tailwind.showcase.sections.BorderShowcase
import io.github.ronjunevaldoz.tailwind.showcase.sections.ColorShowcase
import io.github.ronjunevaldoz.tailwind.showcase.sections.OpacityShowcase
import io.github.ronjunevaldoz.tailwind.showcase.sections.SizingShowcase
import io.github.ronjunevaldoz.tailwind.showcase.sections.SpacingShowcase
import io.github.ronjunevaldoz.tailwind.showcase.sections.TypographyShowcase

/** Renders one section per tailwind-compose utility category, built entirely from the library's own Modifiers. */
@Suppress("ktlint:standard:function-naming")
@Composable
fun App() {
    MaterialTheme {
        Column(
            modifier = Modifier.bgWhite().fillMaxSize().verticalScroll(rememberScrollState()),
        ) {
            Text(
                "tailwind-compose showcase",
                modifier = Modifier.p4(),
                style =
                    MaterialTheme.typography.bodyLarge
                        .textXl2()
                        .fontBold(),
            )
            SpacingShowcase()
            SizingShowcase()
            ColorShowcase()
            TypographyShowcase()
            BorderShowcase()
            OpacityShowcase()
            AspectRatioShowcase()
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Preview
@Composable
private fun AppPreview() {
    App()
}
