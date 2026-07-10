package io.github.ronjunevaldoz.tailwind.style.demo

import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.style.ExperimentalFoundationStyleApi
import androidx.compose.foundation.style.Style
import androidx.compose.foundation.style.contentPadding
import androidx.compose.foundation.style.styleable
import androidx.compose.foundation.style.then
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.ronjunevaldoz.tailwind.core.TwColors
import io.github.ronjunevaldoz.tailwind.core.TwDuration
import io.github.ronjunevaldoz.tailwind.core.TwEasing
import io.github.ronjunevaldoz.tailwind.style.brightnessStyle150
import io.github.ronjunevaldoz.tailwind.style.contrastStyle150
import io.github.ronjunevaldoz.tailwind.style.grayscaleStyle
import io.github.ronjunevaldoz.tailwind.style.invertStyle
import io.github.ronjunevaldoz.tailwind.style.marginStyle4
import io.github.ronjunevaldoz.tailwind.style.paddingStyle1
import io.github.ronjunevaldoz.tailwind.style.paddingStyle2
import io.github.ronjunevaldoz.tailwind.style.paddingStyle4
import io.github.ronjunevaldoz.tailwind.style.paddingStyle8
import io.github.ronjunevaldoz.tailwind.style.saturateStyle200
import io.github.ronjunevaldoz.tailwind.style.sepiaStyle

/**
 * Standalone, web-only live demo of this module's own Style extensions --
 * FilterStyle.kt (`colorFilter`) and SpacingStyle.kt (`contentPadding`/`externalPadding`), the
 * two StyleScope properties that are genuinely new in Compose Multiplatform 1.12.0-beta01 and
 * don't exist in 1.11.1 yet. Everything else in the Style-API port (Ring, Border, Shadow,
 * Color, Opacity, Sizing, Transform, Transition, Typography, ZIndex) has its own demo in
 * :tailwind-style instead -- this module deliberately has no runtime dependency on that
 * artifact (see build.gradle.kts's comment: mixing 1.11.1-compiled and 1.12.0-beta01-compiled
 * Style-API classes in one process crashes, confirmed empirically via AbstractMethodError on
 * JVM and a Skiko WebAssembly LinkError on web), so this demo builds its own backgrounds/sizes
 * inline via raw StyleScope calls rather than importing tailwind-style's bgStyle/widthStyle.
 */
@OptIn(ExperimentalFoundationStyleApi::class)
@Suppress("ktlint:standard:function-naming")
@Composable
fun App() {
    Column(
        Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
    ) {
        BasicText(
            "tailwind-style-experimental live demo",
            style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
        )
        BasicText(
            "Compose Multiplatform 1.12.0-beta01 -- every Style extension below is live-rendered, not a code snippet.",
            style = TextStyle(fontSize = 14.sp, color = Color(0xFF64748B)),
        )
        Spacer(Modifier.height(16.dp))

        DemoSection("Filters -- grayscale/invert/sepia/brightness/contrast/saturate") { FilterDemo() }
        DemoSection("Transition -- transitionStyle (click to toggle)") { TransitionDemo() }
        DemoSection("Spacing -- paddingStyle1/2/4/8, marginStyle4") { SpacingDemo() }
    }
}

@OptIn(ExperimentalFoundationStyleApi::class)
@Suppress("ktlint:standard:function-naming")
@Composable
private fun DemoSection(
    title: String,
    content: @Composable () -> Unit,
) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
            .styleable(
                style =
                    Style {
                        background(Color(0xFFF8FAFC))
                    }.paddingStyle4(),
            ),
    ) {
        BasicText(title, style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold))
        Spacer(Modifier.height(12.dp))
        content()
    }
}

@OptIn(ExperimentalFoundationStyleApi::class)
@Suppress("ktlint:standard:function-naming")
@Composable
private fun FilterDemo() {
    Row(Modifier.padding(8.dp)) {
        listOf(
            Style.grayscaleStyle(),
            Style.invertStyle(),
            Style.sepiaStyle(),
            Style.brightnessStyle150(),
            Style.contrastStyle150(),
            Style.saturateStyle200(),
        ).forEach { filter ->
            Box(
                Modifier
                    .padding(6.dp)
                    .size(36.dp)
                    .styleable(style = Style { background(TwColors.orange500) }.then(filter)),
            )
        }
    }
}

@OptIn(ExperimentalFoundationStyleApi::class)
@Suppress("ktlint:standard:function-naming")
@Composable
private fun TransitionDemo() {
    // transitionStyle lives in :tailwind-style, which this module can't depend on at
    // runtime -- see build.gradle.kts's comment -- so this calls the same
    // AnimateStyleScope.animate(spec, spec) { ... } pattern it wraps, directly.
    var expanded by remember { mutableStateOf(false) }
    Box(
        Modifier
            .padding(8.dp)
            .clickable { expanded = !expanded }
            .styleable(
                style =
                    Style {
                        val spec = tween<Float>(TwDuration.D300, easing = TwEasing.easeInOut)
                        animate(spec, spec) {
                            background(if (expanded) TwColors.blue500 else TwColors.slate300)
                            contentPadding(if (expanded) 32.dp else 8.dp)
                        }
                    },
            ),
    ) {
        BasicText(if (expanded) "click to shrink" else "click to grow")
    }
}

@OptIn(ExperimentalFoundationStyleApi::class)
@Suppress("ktlint:standard:function-naming")
@Composable
private fun SpacingDemo() {
    Row(Modifier.padding(8.dp)) {
        listOf(
            Style.paddingStyle1(),
            Style.paddingStyle2(),
            Style.paddingStyle4(),
            Style.paddingStyle8(),
        ).forEach { padding ->
            Box(
                Modifier
                    .styleable(style = Style { background(TwColors.slate800) }.marginStyle4().then(padding)),
            ) {
                Box(Modifier.size(16.dp).styleable(style = Style { background(TwColors.amber400) }))
            }
        }
    }
}
