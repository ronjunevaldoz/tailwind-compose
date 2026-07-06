package io.github.ronjunevaldoz.tailwind.modifiers

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.platform.WindowInfo
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertWidthIsEqualTo
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import io.github.ronjunevaldoz.tailwind.core.TwSpacing
import org.junit.Rule
import kotlin.test.Test

private class FakeWindowInfo(
    override val containerDpSize: DpSize,
) : WindowInfo {
    override val isWindowFocused: Boolean = true
}

class ResponsiveModifierTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private fun setContentAtWidth(
        width: Dp,
        content: @Composable () -> Unit,
    ) {
        composeTestRule.setContent {
            CompositionLocalProvider(LocalWindowInfo provides FakeWindowInfo(DpSize(width, 800.dp))) {
                content()
            }
        }
    }

    @Test
    fun narrowerThanSm_resolvesToBase() {
        setContentAtWidth(400.dp) {
            Box(
                Modifier
                    .testTag("box")
                    .width(twResponsive(base = TwSpacing.scale4, md = TwSpacing.scale8)),
            )
        }
        composeTestRule.onNodeWithTag("box").assertWidthIsEqualTo(TwSpacing.scale4)
    }

    @Test
    fun betweenMdAndLg_resolvesToMd() {
        setContentAtWidth(800.dp) {
            Box(
                Modifier
                    .testTag("box")
                    .width(
                        twResponsive(
                            base = TwSpacing.scale4,
                            md = TwSpacing.scale8,
                            lg = TwSpacing.scale12,
                        ),
                    ),
            )
        }
        composeTestRule.onNodeWithTag("box").assertWidthIsEqualTo(TwSpacing.scale8)
    }

    @Test
    fun pastEveryProvidedBreakpoint_resolvesToTheLargestOne() {
        setContentAtWidth(1500.dp) {
            Box(
                Modifier
                    .testTag("box")
                    .width(
                        twResponsive(
                            base = TwSpacing.scale4,
                            md = TwSpacing.scale8,
                            lg = TwSpacing.scale12,
                        ),
                    ),
            )
        }
        // No `xl`/`2xl` value was provided, so lg (the largest provided match) wins even
        // though the window is wider than the xl/2xl thresholds too.
        composeTestRule.onNodeWithTag("box").assertWidthIsEqualTo(TwSpacing.scale12)
    }
}
