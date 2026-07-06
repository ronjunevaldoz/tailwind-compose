package io.github.ronjunevaldoz.tailwind.showcase.sections

import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import io.github.ronjunevaldoz.tailwind.core.TwColors
import io.github.ronjunevaldoz.tailwind.core.TwRadius
import io.github.ronjunevaldoz.tailwind.modifiers.bgSky500
import io.github.ronjunevaldoz.tailwind.modifiers.bgSlate300
import io.github.ronjunevaldoz.tailwind.modifiers.bgSlate700
import io.github.ronjunevaldoz.tailwind.modifiers.bgSlate900
import io.github.ronjunevaldoz.tailwind.modifiers.bgWhite
import io.github.ronjunevaldoz.tailwind.modifiers.border
import io.github.ronjunevaldoz.tailwind.modifiers.fontBold
import io.github.ronjunevaldoz.tailwind.modifiers.fontMedium
import io.github.ronjunevaldoz.tailwind.modifiers.gap2
import io.github.ronjunevaldoz.tailwind.modifiers.p4
import io.github.ronjunevaldoz.tailwind.modifiers.px4
import io.github.ronjunevaldoz.tailwind.modifiers.py2
import io.github.ronjunevaldoz.tailwind.modifiers.roundedFull
import io.github.ronjunevaldoz.tailwind.modifiers.roundedXl
import io.github.ronjunevaldoz.tailwind.modifiers.textSlate400
import io.github.ronjunevaldoz.tailwind.modifiers.textSlate500
import io.github.ronjunevaldoz.tailwind.modifiers.textSlate900
import io.github.ronjunevaldoz.tailwind.modifiers.textSm
import io.github.ronjunevaldoz.tailwind.modifiers.textWhite
import io.github.ronjunevaldoz.tailwind.modifiers.textXl2
import io.github.ronjunevaldoz.tailwind.modifiers.textXs
import kotlin.math.roundToInt

private val COMPARE_WIDTH = 280.dp
private val COMPARE_HEIGHT = 160.dp
private val HANDLE_SIZE = 32.dp

/** Test tag for the drag handle, so tests can locate and drag it. */
internal const val DARK_MODE_HANDLE_TEST_TAG = "dark-mode-handle"

/**
 * A drag-to-compare slider between the light and dark rendering of the same mock
 * content — `twDark { }` reacts to the real system theme (see DarkMode.kt), which
 * this demo can't flip on demand, so this simulates it with a draggable divider
 * instead of calling `twDark { }` directly. Illustrative, not the real API.
 *
 * The dark layer is measured at the full compare width via a custom [layout], then
 * placed shifted left so only its revealed portion falls inside a narrower
 * `clipToBounds()` window — this keeps both layers' content pixel-aligned, unlike
 * naively re-laying-out a narrower copy. The custom layout node must report its own
 * size as the *incoming* `constraints.maxWidth`, not the child's full measured
 * width — reporting the full width caused the parent `Box` to only place/reveal
 * about half of the intended window (confirmed empirically; a `drawWithContent {
 * clipRect(...) }` alternative was also tried and consistently dropped the layer's
 * `Text` content entirely, regardless of nesting).
 */
@Suppress("ktlint:standard:function-naming", "LongMethod")
@Composable
fun DarkModeShowcase() {
    ShowcaseSection(
        title = "Dark Mode — Modifier.bgWhite().twDark { bgSlate900() }",
        code =
            """
            // Real usage — reacts to the system theme automatically:
            Modifier.bgWhite().twDark { bgSlate900() }
            """.trimIndent(),
    ) {
        val outerShape = RoundedCornerShape(TwRadius.xl)
        val density = LocalDensity.current
        val widthPx = with(density) { COMPARE_WIDTH.toPx() }
        val heightPx = with(density) { COMPARE_HEIGHT.toPx() }
        val handleSizePx = with(density) { HANDLE_SIZE.toPx() }
        var dividerX by remember { mutableStateOf(widthPx / 2f) }

        Box(
            Modifier
                .width(COMPARE_WIDTH)
                .height(COMPARE_HEIGHT)
                .roundedXl()
                .border(TwColors.slate200, outerShape),
        ) {
            CompareContent(isDark = false, modifier = Modifier.fillMaxSize())

            val revealWidth = with(density) { (widthPx - dividerX).coerceAtLeast(0f).toDp() }
            Box(
                Modifier
                    .align(Alignment.CenterStart)
                    .offset { IntOffset(dividerX.roundToInt(), 0) }
                    .width(revealWidth)
                    .fillMaxHeight()
                    .clipToBounds(),
            ) {
                CompareContent(
                    isDark = true,
                    modifier =
                        Modifier.layout { measurable, constraints ->
                            // Measure at the compare box's real full size regardless of what
                            // this clipToBounds() window offers, then place it shifted left by
                            // dividerX so only its right portion falls inside the clip window.
                            val placeable =
                                measurable.measure(
                                    Constraints.fixed(widthPx.roundToInt(), heightPx.roundToInt()),
                                )
                            layout(constraints.maxWidth, placeable.height) {
                                placeable.place(-dividerX.roundToInt(), 0)
                            }
                        },
                )
            }

            Box(
                Modifier
                    .offset { IntOffset(dividerX.roundToInt(), 0) }
                    .width(2.dp)
                    .fillMaxHeight()
                    .bgSky500(),
            )

            Box(
                Modifier
                    .align(Alignment.CenterStart)
                    .offset { IntOffset((dividerX - handleSizePx / 2).roundToInt(), 0) }
                    .size(HANDLE_SIZE)
                    .roundedFull()
                    .bgSky500()
                    .testTag(DARK_MODE_HANDLE_TEST_TAG)
                    .pointerInput(Unit) {
                        detectDragGestures { change, dragAmount ->
                            change.consume()
                            dividerX = (dividerX + dragAmount.x).coerceIn(0f, widthPx)
                        }
                    },
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    "↔",
                    style =
                        MaterialTheme.typography.bodySmall
                            .textXs()
                            .textWhite(),
                )
            }
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
private fun CompareContent(
    isDark: Boolean,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier
            .let { if (isDark) it.bgSlate900() else it.bgWhite() }
            .p4(),
        verticalArrangement = gap2(),
    ) {
        Box(Modifier.size(28.dp).roundedFull().bgSlate300())
        Text(
            "BALANCE",
            style =
                MaterialTheme.typography.bodySmall
                    .textXs()
                    .fontMedium()
                    .let { if (isDark) it.textSlate400() else it.textSlate500() },
        )
        Text(
            "$20,568.72",
            style =
                MaterialTheme.typography.bodyLarge
                    .textXl2()
                    .fontBold()
                    .let { if (isDark) it.textWhite() else it.textSlate900() },
        )
        Box(
            Modifier
                .roundedFull()
                .let { if (isDark) it.bgSlate700() else it.bgSlate900() }
                .px4()
                .py2(),
        ) {
            Text(
                "Send",
                style =
                    MaterialTheme.typography.bodyMedium
                        .textSm()
                        .fontMedium()
                        .textWhite(),
            )
        }
    }
}
