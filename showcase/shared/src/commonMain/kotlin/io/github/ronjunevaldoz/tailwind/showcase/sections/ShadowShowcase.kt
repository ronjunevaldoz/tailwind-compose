package io.github.ronjunevaldoz.tailwind.showcase.sections

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.ronjunevaldoz.tailwind.modifiers.bgWhite
import io.github.ronjunevaldoz.tailwind.modifiers.gap4
import io.github.ronjunevaldoz.tailwind.modifiers.shadowLg
import io.github.ronjunevaldoz.tailwind.modifiers.shadowMd
import io.github.ronjunevaldoz.tailwind.modifiers.shadowSm
import io.github.ronjunevaldoz.tailwind.modifiers.shadowXl

/** shadowSm/Md/Lg/Xl — approximated as Compose elevation (see Shadow.kt's KDoc). */
@Suppress("ktlint:standard:function-naming")
@Composable
fun ShadowShowcase() {
    ShowcaseSection(
        title = "Shadow — shadowSm, shadowMd, shadowLg, shadowXl",
        code =
            """
            Row(horizontalArrangement = gap4(), modifier = Modifier.padding(16.dp)) {
                listOf(Modifier.shadowSm(), Modifier.shadowMd(), Modifier.shadowLg(), Modifier.shadowXl())
                    .forEach { shadow ->
                        Box(shadow.size(40.dp).bgWhite())
                    }
            }
            """.trimIndent(),
    ) {
        Row(horizontalArrangement = gap4(), modifier = Modifier.padding(16.dp)) {
            listOf(Modifier.shadowSm(), Modifier.shadowMd(), Modifier.shadowLg(), Modifier.shadowXl())
                .forEach { shadow ->
                    Box(shadow.size(40.dp).bgWhite())
                }
        }
    }
}
