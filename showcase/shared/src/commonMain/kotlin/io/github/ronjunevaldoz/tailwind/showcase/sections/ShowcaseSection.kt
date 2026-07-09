package io.github.ronjunevaldoz.tailwind.showcase.sections

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.ronjunevaldoz.tailwind.modifiers.bgSlate100
import io.github.ronjunevaldoz.tailwind.modifiers.bgSlate700
import io.github.ronjunevaldoz.tailwind.modifiers.bgSlate800
import io.github.ronjunevaldoz.tailwind.modifiers.bgSlate900
import io.github.ronjunevaldoz.tailwind.modifiers.fontBold
import io.github.ronjunevaldoz.tailwind.modifiers.fontMedium
import io.github.ronjunevaldoz.tailwind.modifiers.gap2
import io.github.ronjunevaldoz.tailwind.modifiers.p4
import io.github.ronjunevaldoz.tailwind.modifiers.px3
import io.github.ronjunevaldoz.tailwind.modifiers.py1
import io.github.ronjunevaldoz.tailwind.modifiers.rounded
import io.github.ronjunevaldoz.tailwind.modifiers.textLg
import io.github.ronjunevaldoz.tailwind.modifiers.textSm
import io.github.ronjunevaldoz.tailwind.modifiers.textWhite
import io.github.ronjunevaldoz.tailwind.modifiers.twCard
import io.github.ronjunevaldoz.tailwind.modifiers.twDark

private enum class SectionTabMode { PREVIEW, CODE, STYLE_API }

/**
 * Shared section wrapper used by every showcase category — a title, its live demo, and
 * (when [code] is supplied) a Preview/Code toggle that swaps the demo for a [CodeBlock]
 * showing the snippet that produced it. Explainer-only categories pass no [code] and get
 * the title + content with no toggle, same as before.
 *
 * [styleCode], when supplied, adds a third "Style API" tab showing the equivalent
 * `tailwind-style-experimental` snippet — code-only, no live preview. That module is
 * deliberately isolated on a different (pre-release) Compose Multiplatform version than
 * this showcase app (see `docs/tailwind-coverage-matrix.md`'s Style API section), so it
 * can't be depended on here to render live the way the Modifier-based [content] can.
 */
@Suppress("ktlint:standard:function-naming")
@Composable
fun ShowcaseSection(
    title: String,
    modifier: Modifier = Modifier,
    code: String? = null,
    styleCode: String? = null,
    content: @Composable () -> Unit,
) {
    var mode by remember { mutableStateOf(SectionTabMode.PREVIEW) }
    Column(
        modifier =
            modifier
                .fillMaxWidth()
                .twCard()
                .twDark { bgSlate800() }
                .p4(),
    ) {
        Text(
            text = title,
            style =
                MaterialTheme.typography.bodyLarge
                    .textLg()
                    .fontBold()
                    .twDark { textWhite() },
        )
        if (code != null) {
            Spacer(Modifier.height(8.dp))
            Row(horizontalArrangement = gap2()) {
                SectionTab(
                    label = "Preview",
                    selected = mode == SectionTabMode.PREVIEW,
                ) { mode = SectionTabMode.PREVIEW }
                SectionTab(label = "Code", selected = mode == SectionTabMode.CODE) { mode = SectionTabMode.CODE }
                if (styleCode != null) {
                    SectionTab(label = "Style API", selected = mode == SectionTabMode.STYLE_API) {
                        mode =
                            SectionTabMode.STYLE_API
                    }
                }
            }
        }
        Spacer(Modifier.height(12.dp))
        when {
            code != null && mode == SectionTabMode.CODE -> CodeBlock(code)
            styleCode != null && mode == SectionTabMode.STYLE_API -> CodeBlock(styleCode)
            else -> content()
        }
    }
}

// DEPRECATION: rounded() has no v4-named replacement yet -- see its deprecation note
@Suppress("ktlint:standard:function-naming", "DEPRECATION")
@Composable
private fun SectionTab(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
) {
    Text(
        text = label,
        style =
            MaterialTheme.typography.bodyMedium
                .textSm()
                .fontMedium()
                .let { if (selected) it.textWhite() else it.twDark { textWhite() } },
        modifier =
            Modifier
                .rounded()
                .let { if (selected) it.bgSlate900() else it.bgSlate100().twDark { bgSlate700() } }
                .clickable(onClick = onClick)
                .px3()
                .py1(),
    )
}
