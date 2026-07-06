package io.github.ronjunevaldoz.tailwind.showcase.sections

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.ronjunevaldoz.tailwind.modifiers.fontBold
import io.github.ronjunevaldoz.tailwind.modifiers.p4
import io.github.ronjunevaldoz.tailwind.modifiers.textLg

/** Shared section wrapper used by every showcase category — a title + its content. */
@Suppress("ktlint:standard:function-naming")
@Composable
fun ShowcaseSection(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Column(modifier = modifier.p4()) {
        Text(
            text = title,
            style =
                MaterialTheme.typography.bodyLarge
                    .textLg()
                    .fontBold(),
        )
        content()
    }
}
