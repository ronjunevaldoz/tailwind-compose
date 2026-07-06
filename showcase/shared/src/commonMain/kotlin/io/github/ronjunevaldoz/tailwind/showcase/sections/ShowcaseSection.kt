package io.github.ronjunevaldoz.tailwind.showcase.sections

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import io.github.ronjunevaldoz.tailwind.modifiers.bgSlate900
import io.github.ronjunevaldoz.tailwind.modifiers.bgWhite
import io.github.ronjunevaldoz.tailwind.modifiers.fontBold
import io.github.ronjunevaldoz.tailwind.modifiers.fontMedium
import io.github.ronjunevaldoz.tailwind.modifiers.gap2
import io.github.ronjunevaldoz.tailwind.modifiers.p4
import io.github.ronjunevaldoz.tailwind.modifiers.px3
import io.github.ronjunevaldoz.tailwind.modifiers.py1
import io.github.ronjunevaldoz.tailwind.modifiers.rounded
import io.github.ronjunevaldoz.tailwind.modifiers.roundedLg
import io.github.ronjunevaldoz.tailwind.modifiers.shadowSm
import io.github.ronjunevaldoz.tailwind.modifiers.textLg
import io.github.ronjunevaldoz.tailwind.modifiers.textSm
import io.github.ronjunevaldoz.tailwind.modifiers.textWhite

/**
 * Shared section wrapper used by every showcase category — a title, its live demo, and
 * (when [code] is supplied) a Preview/Code toggle that swaps the demo for a [CodeBlock]
 * showing the snippet that produced it. Explainer-only categories pass no [code] and get
 * the title + content with no toggle, same as before.
 */
@Suppress("ktlint:standard:function-naming")
@Composable
fun ShowcaseSection(
    title: String,
    modifier: Modifier = Modifier,
    code: String? = null,
    content: @Composable () -> Unit,
) {
    var showCode by remember { mutableStateOf(false) }
    Column(
        // Shape-affecting modifiers must be ordered shadow -> clip -> background: a Compose
        // shadow's halo is clipped by anything applied before it, and a background painted
        // before a clip() ignores that clip entirely (it's drawn outside the clipped layer).
        // See docs/tailwind-coverage-matrix.md for the corner-artifact this order avoids.
        modifier =
            modifier
                .shadowSm()
                .roundedLg()
                .bgWhite()
                .p4(),
    ) {
        Text(
            text = title,
            style =
                MaterialTheme.typography.bodyLarge
                    .textLg()
                    .fontBold(),
        )
        if (code != null) {
            Spacer(Modifier.height(8.dp))
            Row(horizontalArrangement = gap2()) {
                SectionTab(label = "Preview", selected = !showCode) { showCode = false }
                SectionTab(label = "Code", selected = showCode) { showCode = true }
            }
        }
        Spacer(Modifier.height(12.dp))
        if (code != null && showCode) {
            CodeBlock(code)
        } else {
            content()
        }
    }
}

@Suppress("ktlint:standard:function-naming")
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
                .let { if (selected) it.textWhite() else it },
        modifier =
            Modifier
                .rounded()
                .let { if (selected) it.bgSlate900() else it.bgSlate100() }
                .clickable(onClick = onClick)
                .px3()
                .py1(),
    )
}
