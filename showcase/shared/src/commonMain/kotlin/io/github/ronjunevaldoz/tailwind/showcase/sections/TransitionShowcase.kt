package io.github.ronjunevaldoz.tailwind.showcase.sections

import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import io.github.ronjunevaldoz.tailwind.core.TwColors
import io.github.ronjunevaldoz.tailwind.core.TwEasing
import io.github.ronjunevaldoz.tailwind.core.TwRadius
import io.github.ronjunevaldoz.tailwind.modifiers.bg
import io.github.ronjunevaldoz.tailwind.modifiers.bgBlue500
import io.github.ronjunevaldoz.tailwind.modifiers.bgSlate200
import io.github.ronjunevaldoz.tailwind.modifiers.bgSlate50
import io.github.ronjunevaldoz.tailwind.modifiers.border
import io.github.ronjunevaldoz.tailwind.modifiers.fontMono
import io.github.ronjunevaldoz.tailwind.modifiers.gap1
import io.github.ronjunevaldoz.tailwind.modifiers.gap4
import io.github.ronjunevaldoz.tailwind.modifiers.roundedFull
import io.github.ronjunevaldoz.tailwind.modifiers.roundedMd
import io.github.ronjunevaldoz.tailwind.modifiers.textSm
import io.github.ronjunevaldoz.tailwind.modifiers.transitionAllDuration300
import kotlin.math.roundToInt

/** transitionAllDuration300 — tap to animate a size change instead of snapping. */
@Suppress("ktlint:standard:function-naming")
@Composable
fun TransitionShowcase() {
    ShowcaseSection(
        title = "Transitions — transitionAllDuration300",
        code =
            """
            var expanded by remember { mutableStateOf(false) }
            Box(
                Modifier
                    .transitionAllDuration300()
                    .size(if (expanded) 80.dp else 40.dp)
                    .bgBlue500(),
            )
            Button(onClick = { expanded = !expanded }) { Text("Toggle size") }
            """.trimIndent(),
    ) {
        var expanded by remember { mutableStateOf(false) }
        Box(
            Modifier
                .transitionAllDuration300()
                .size(if (expanded) 80.dp else 40.dp)
                .bgBlue500(),
        )
        Button(onClick = { expanded = !expanded }) {
            Text("Toggle size")
        }
    }
    EasingCurvesShowcase()
}

private data class NamedEasing(
    val label: String,
    val easing: Easing,
    val color: Color,
)

private val EASINGS =
    listOf(
        NamedEasing("linear", TwEasing.linear, TwColors.sky500),
        NamedEasing("ease-out", TwEasing.easeOut, TwColors.violet500),
        NamedEasing("ease-in-out", TwEasing.easeInOut, TwColors.pink500),
        NamedEasing("ease-in", TwEasing.easeIn, TwColors.indigo500),
    )

private val GRAPH_SIZE = 56.dp
private val TRACK_WIDTH = 180.dp
private val TRACK_DOT_SIZE = 20.dp
private const val GRAPH_STEPS = 32
private const val LOOP_DURATION_MS = 2000

/**
 * `TwEasing`'s four curves (see TwTransition.kt), each graphed from its actual
 * [Easing.transform] — not decorative icons — next to a dot looping across a track
 * with that same easing, so the shape on the left visibly matches the motion on the
 * right.
 */
@Suppress("ktlint:standard:function-naming")
@Composable
fun EasingCurvesShowcase() {
    ShowcaseSection(
        title = "Transitions — TwEasing curves",
        code =
            """
            val progress by rememberInfiniteTransition().animateFloat(
                initialValue = 0f,
                targetValue = 1f,
                animationSpec = infiniteRepeatable(tween(2000, easing = TwEasing.easeInOut)),
            )
            """.trimIndent(),
    ) {
        Column(verticalArrangement = gap4()) {
            EASINGS.forEach { named -> EasingCurveRow(named) }
        }
    }
}

@Suppress("ktlint:standard:function-naming", "LongMethod")
@Composable
private fun EasingCurveRow(named: NamedEasing) {
    val transition = rememberInfiniteTransition(label = named.label)
    val progress by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec =
            infiniteRepeatable(
                animation = tween(LOOP_DURATION_MS, easing = named.easing),
                repeatMode = RepeatMode.Restart,
            ),
        label = "${named.label}-progress",
    )

    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = gap4()) {
        val graphShape = RoundedCornerShape(TwRadius.md)
        Canvas(
            Modifier
                .roundedMd()
                .bgSlate50()
                .border(TwColors.slate200, graphShape)
                .size(GRAPH_SIZE),
        ) {
            val path = Path()
            for (i in 0..GRAPH_STEPS) {
                val t = i / GRAPH_STEPS.toFloat()
                val eased = named.easing.transform(t)
                val x = t * size.width
                val y = size.height - eased * size.height
                if (i == 0) path.moveTo(x, y) else path.lineTo(x, y)
            }
            drawPath(path, color = named.color, style = Stroke(width = 2.dp.toPx()))
        }
        Column(verticalArrangement = gap1()) {
            Text(
                named.label,
                style =
                    MaterialTheme.typography.bodyMedium
                        .textSm()
                        .fontMono(),
            )
            Box(Modifier.width(TRACK_WIDTH).height(TRACK_DOT_SIZE)) {
                Box(
                    Modifier
                        .align(Alignment.CenterStart)
                        .fillMaxWidth()
                        .height(2.dp)
                        .bgSlate200(),
                )
                val density = LocalDensity.current
                val trackPx = with(density) { (TRACK_WIDTH - TRACK_DOT_SIZE).toPx() }
                Box(
                    Modifier
                        .align(Alignment.CenterStart)
                        .offset { IntOffset((progress * trackPx).roundToInt(), 0) }
                        .size(TRACK_DOT_SIZE)
                        .bg(named.color)
                        .roundedFull(),
                )
            }
        }
    }
}
