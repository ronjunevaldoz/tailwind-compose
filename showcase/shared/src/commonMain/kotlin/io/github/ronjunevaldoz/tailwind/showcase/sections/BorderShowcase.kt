package io.github.ronjunevaldoz.tailwind.showcase.sections

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.style.ExperimentalFoundationStyleApi
import androidx.compose.foundation.style.Style
import androidx.compose.foundation.style.styleable
import androidx.compose.foundation.style.then
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import io.github.ronjunevaldoz.tailwind.core.TwColors
import io.github.ronjunevaldoz.tailwind.core.TwRadius
import io.github.ronjunevaldoz.tailwind.modifiers.border4
import io.github.ronjunevaldoz.tailwind.modifiers.gap4
import io.github.ronjunevaldoz.tailwind.style.borderStyle4
import io.github.ronjunevaldoz.tailwind.style.roundedStyleFull
import io.github.ronjunevaldoz.tailwind.style.roundedStyleLg
import io.github.ronjunevaldoz.tailwind.style.roundedStyleNone
import io.github.ronjunevaldoz.tailwind.style.roundedStyleXs

/**
 * rounded corners (none/default/lg/full) applied to a bordered square. The same
 * [RoundedCornerShape] is passed to both `clip()` and `border4()` — see border4's
 * KDoc for why that's more robust than relying on clip() alone.
 */
@OptIn(ExperimentalFoundationStyleApi::class)
@Suppress("ktlint:standard:function-naming")
@Composable
fun BorderShowcase() {
    ShowcaseSection(
        title = "Borders — roundedNone, roundedXs, roundedLg, roundedFull",
        code =
            """
            Row(horizontalArrangement = gap4()) {
                listOf(TwRadius.none, TwRadius.xs, TwRadius.lg, TwRadius.full).forEach { radius ->
                    val shape = RoundedCornerShape(radius)
                    Box(Modifier.clip(shape).size(40.dp).border4(TwColors.blue500, shape))
                }
            }
            """.trimIndent(),
        styleCode =
            """
            // tailwind-style -- borderStyle4() + roundedStyle*(), Style extensions built on
            // the real Compose Styles API. dropShadow's own shape() call (see FocusRing.kt in
            // shadcn-compose) is how a border/ring follows a shape there; here the plain
            // border() StyleScope property clips to whatever shape() the Style itself sets.
            Row(horizontalArrangement = gap4()) {
                listOf(Style.roundedStyleNone(), Style.roundedStyleXs(), Style.roundedStyleLg(), Style.roundedStyleFull())
                    .forEach { rounded ->
                        Box(
                            Modifier.size(40.dp)
                                .styleable(style = Style.borderStyle4(TwColors.blue500).then(rounded)),
                        )
                    }
            }
            """.trimIndent(),
        styleContent = {
            Row(horizontalArrangement = gap4()) {
                listOf(
                    Style.roundedStyleNone(),
                    Style.roundedStyleXs(),
                    Style.roundedStyleLg(),
                    Style.roundedStyleFull(),
                ).forEach { rounded ->
                    Box(
                        Modifier
                            .size(40.dp)
                            .styleable(style = Style.borderStyle4(TwColors.blue500).then(rounded)),
                    )
                }
            }
        },
    ) {
        Row(horizontalArrangement = gap4()) {
            listOf(TwRadius.none, TwRadius.xs, TwRadius.lg, TwRadius.full).forEach { radius ->
                val shape = RoundedCornerShape(radius)
                Box(Modifier.clip(shape).size(40.dp).border4(TwColors.blue500, shape))
            }
        }
    }
}
