package io.github.ronjunevaldoz.tailwind.showcase.sections

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.style.ExperimentalFoundationStyleApi
import androidx.compose.foundation.style.Style
import androidx.compose.foundation.style.styleable
import androidx.compose.foundation.style.then
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.github.ronjunevaldoz.tailwind.modifiers.bgWhite
import io.github.ronjunevaldoz.tailwind.modifiers.gap4
import io.github.ronjunevaldoz.tailwind.modifiers.shadowLg
import io.github.ronjunevaldoz.tailwind.modifiers.shadowMd
import io.github.ronjunevaldoz.tailwind.modifiers.shadowXl
import io.github.ronjunevaldoz.tailwind.modifiers.shadowXs
import io.github.ronjunevaldoz.tailwind.style.bgStyle
import io.github.ronjunevaldoz.tailwind.style.shadowStyleLg
import io.github.ronjunevaldoz.tailwind.style.shadowStyleMd
import io.github.ronjunevaldoz.tailwind.style.shadowStyleXl
import io.github.ronjunevaldoz.tailwind.style.shadowStyleXs

/** shadowXs/Md/Lg/Xl — approximated as Compose elevation (see Shadow.kt's KDoc). */
@OptIn(ExperimentalFoundationStyleApi::class)
@Suppress("ktlint:standard:function-naming")
@Composable
fun ShadowShowcase() {
    ShowcaseSection(
        title = "Shadow — shadowXs, shadowMd, shadowLg, shadowXl",
        code =
            """
            Row(horizontalArrangement = gap4(), modifier = Modifier.padding(16.dp)) {
                listOf(Modifier.shadowXs(), Modifier.shadowMd(), Modifier.shadowLg(), Modifier.shadowXl())
                    .forEach { shadow ->
                        Box(shadow.size(40.dp).bgWhite())
                    }
            }
            """.trimIndent(),
        styleCode =
            """
            // tailwind-style -- shadowStyleXs/Md/Lg/Xl(), the Style API's own dropShadow().
            Row(horizontalArrangement = gap4(), modifier = Modifier.padding(16.dp)) {
                listOf(Style.shadowStyleXs(), Style.shadowStyleMd(), Style.shadowStyleLg(), Style.shadowStyleXl())
                    .forEach { shadow ->
                        Box(
                            Modifier.size(40.dp)
                                .styleable(style = Style.bgStyle(Color.White).then(shadow)),
                        )
                    }
            }
            """.trimIndent(),
        styleContent = {
            Row(horizontalArrangement = gap4(), modifier = Modifier.padding(16.dp)) {
                listOf(
                    Style.shadowStyleXs(),
                    Style.shadowStyleMd(),
                    Style.shadowStyleLg(),
                    Style.shadowStyleXl(),
                ).forEach { shadow ->
                    Box(
                        Modifier
                            .size(40.dp)
                            .styleable(style = Style.bgStyle(Color.White).then(shadow)),
                    )
                }
            }
        },
    ) {
        Row(horizontalArrangement = gap4(), modifier = Modifier.padding(16.dp)) {
            listOf(Modifier.shadowXs(), Modifier.shadowMd(), Modifier.shadowLg(), Modifier.shadowXl())
                .forEach { shadow ->
                    Box(shadow.size(40.dp).bgWhite())
                }
        }
    }
}
