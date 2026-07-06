package io.github.ronjunevaldoz.tailwind.showcase.sections

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.ronjunevaldoz.tailwind.modifiers.bgBlue500
import io.github.ronjunevaldoz.tailwind.modifiers.bgSlate200
import io.github.ronjunevaldoz.tailwind.modifiers.justifyBetween
import io.github.ronjunevaldoz.tailwind.modifiers.justifyCenter
import io.github.ronjunevaldoz.tailwind.modifiers.justifyEnd

/** justifyCenter/justifyEnd/justifyBetween — Row's main-axis alignment. */
@Suppress("ktlint:standard:function-naming")
@Composable
fun FlexShowcase() {
    ShowcaseSection(
        title = "Flex — justifyCenter, justifyEnd, justifyBetween",
        code =
            """
            listOf(justifyCenter(), justifyEnd(), justifyBetween()).forEach { arrangement ->
                Row(
                    modifier = Modifier.width(200.dp).height(24.dp).bgSlate200(),
                    horizontalArrangement = arrangement,
                ) {
                    Box(Modifier.size(20.dp).bgBlue500())
                    Box(Modifier.size(20.dp).bgBlue500())
                }
            }
            """.trimIndent(),
    ) {
        listOf(
            "justifyCenter" to justifyCenter(),
            "justifyEnd" to justifyEnd(),
            "justifyBetween" to justifyBetween(),
        ).forEach { (_, arrangement) ->
            Row(
                modifier = Modifier.width(200.dp).height(24.dp).bgSlate200(),
                horizontalArrangement = arrangement,
            ) {
                Box(Modifier.size(20.dp).bgBlue500())
                Box(Modifier.size(20.dp).bgBlue500())
            }
        }
    }
}
