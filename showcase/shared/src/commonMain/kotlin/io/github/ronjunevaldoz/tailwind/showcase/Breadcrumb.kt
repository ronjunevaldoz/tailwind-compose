package io.github.ronjunevaldoz.tailwind.showcase

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import io.github.ronjunevaldoz.tailwind.modifiers.fontMedium
import io.github.ronjunevaldoz.tailwind.modifiers.textSlate400
import io.github.ronjunevaldoz.tailwind.modifiers.textSlate500
import io.github.ronjunevaldoz.tailwind.modifiers.textSlate900
import io.github.ronjunevaldoz.tailwind.modifiers.textSm
import io.github.ronjunevaldoz.tailwind.modifiers.textWhite
import io.github.ronjunevaldoz.tailwind.modifiers.twDark

/** "Showcase / {category}" trail shown above each category's content, current page emphasized. */
@Suppress("ktlint:standard:function-naming")
@Composable
fun Breadcrumb(
    category: ShowcaseCategory,
    modifier: Modifier = Modifier,
) {
    val prefixStyle =
        MaterialTheme.typography.bodySmall
            .textSm()
            .textSlate500()
            .twDark { textSlate400() }
    val currentStyle = prefixStyle.fontMedium().textSlate900().twDark { textWhite() }
    Text(
        text =
            buildAnnotatedString {
                append("Showcase / ")
                withStyle(currentStyle.toSpanStyle()) { append(category.title) }
            },
        style = prefixStyle,
        modifier = modifier,
    )
}
