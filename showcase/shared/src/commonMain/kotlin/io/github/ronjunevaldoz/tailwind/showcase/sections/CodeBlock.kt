package io.github.ronjunevaldoz.tailwind.showcase.sections

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.withStyle
import io.github.ronjunevaldoz.tailwind.core.TwColors
import io.github.ronjunevaldoz.tailwind.modifiers.bgSlate900
import io.github.ronjunevaldoz.tailwind.modifiers.p3
import io.github.ronjunevaldoz.tailwind.modifiers.roundedMd
import io.github.ronjunevaldoz.tailwind.modifiers.textSm

private val KOTLIN_KEYWORDS =
    setOf(
        "fun",
        "val",
        "var",
        "if",
        "else",
        "when",
        "for",
        "while",
        "return",
        "private",
        "public",
        "object",
        "class",
        "import",
        "package",
        "true",
        "false",
        "null",
        "this",
        "by",
    )

private const val GROUP_COMMENT = 1
private const val GROUP_STRING = 2
private const val GROUP_NUMBER = 3
private const val GROUP_CALL = 4

// Group 1: line comment, 2: string literal, 3: numeric literal,
// 4: identifier immediately followed by "(" (function/constructor call), 5: any other identifier.
private val TOKEN_REGEX =
    Regex(
        "(//[^\n]*)" +
            "|(\"[^\"]*\")" +
            "|\\b(\\d+(?:\\.\\d+)?[fF]?)\\b" +
            "|\\b([A-Za-z_][A-Za-z0-9_]*)\\b(?=\\()" +
            "|\\b([A-Za-z_][A-Za-z0-9_]*)\\b",
    )

/**
 * A minimal, dependency-free Kotlin syntax highlighter for showcase code snippets —
 * enough contrast (keywords/strings/comments/calls) to read comfortably, without pulling
 * in a full markdown/highlighting library (none exist for Compose Multiplatform anyway).
 */
private fun highlightKotlin(code: String) =
    buildAnnotatedString {
        var lastIndex = 0
        for (match in TOKEN_REGEX.findAll(code)) {
            append(code.substring(lastIndex, match.range.first))
            val text = match.value
            val style =
                when {
                    match.groups[GROUP_COMMENT] != null -> SpanStyle(color = TwColors.slate500)
                    match.groups[GROUP_STRING] != null -> SpanStyle(color = TwColors.emerald400)
                    match.groups[GROUP_NUMBER] != null -> SpanStyle(color = TwColors.amber400)
                    match.groups[GROUP_CALL] != null -> SpanStyle(color = TwColors.sky400)
                    text in KOTLIN_KEYWORDS -> SpanStyle(color = TwColors.violet400)
                    else -> null
                }
            if (style != null) {
                withStyle(style) { append(text) }
            } else {
                append(text)
            }
            lastIndex = match.range.last + 1
        }
        append(code.substring(lastIndex))
    }

/** Renders a Kotlin snippet like a markdown fenced code block — monospace, dark card, highlighted. */
@Suppress("ktlint:standard:function-naming")
@Composable
fun CodeBlock(
    code: String,
    modifier: Modifier = Modifier,
) {
    val highlighted = remember(code) { highlightKotlin(code) }
    Box(
        modifier
            .bgSlate900()
            .roundedMd()
            .horizontalScroll(rememberScrollState())
            .p3(),
    ) {
        Text(
            text = highlighted,
            style = TextStyle(fontFamily = FontFamily.Monospace, color = TwColors.slate300).textSm(),
        )
    }
}
