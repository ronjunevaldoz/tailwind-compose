package io.github.ronjunevaldoz.tailwind.style.demo

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.ronjunevaldoz.tailwind.core.TwColors
import io.github.ronjunevaldoz.tailwind.core.TwDuration
import io.github.ronjunevaldoz.tailwind.core.TwSpacing
import io.github.ronjunevaldoz.tailwind.style.bgStyle
import io.github.ronjunevaldoz.tailwind.style.borderStyle2
import io.github.ronjunevaldoz.tailwind.style.borderStyle4
import io.github.ronjunevaldoz.tailwind.style.borderStyle8
import io.github.ronjunevaldoz.tailwind.style.brightnessStyle150
import io.github.ronjunevaldoz.tailwind.style.contrastStyle150
import io.github.ronjunevaldoz.tailwind.style.fontFamilyStyleMono
import io.github.ronjunevaldoz.tailwind.style.fontFamilyStyleSerif
import io.github.ronjunevaldoz.tailwind.style.fontSizeStyleLg
import io.github.ronjunevaldoz.tailwind.style.fontSizeStyleSm
import io.github.ronjunevaldoz.tailwind.style.fontSizeStyleXl
import io.github.ronjunevaldoz.tailwind.style.fontWeightStyleBold
import io.github.ronjunevaldoz.tailwind.style.grayscaleStyle
import io.github.ronjunevaldoz.tailwind.style.heightStyle
import io.github.ronjunevaldoz.tailwind.style.invertStyle
import io.github.ronjunevaldoz.tailwind.style.letterSpacingStyleWide
import io.github.ronjunevaldoz.tailwind.style.marginStyle4
import io.github.ronjunevaldoz.tailwind.style.maxWidthStyle
import io.github.ronjunevaldoz.tailwind.style.minWidthStyle
import io.github.ronjunevaldoz.tailwind.style.opacityStyle0
import io.github.ronjunevaldoz.tailwind.style.opacityStyle100
import io.github.ronjunevaldoz.tailwind.style.opacityStyle50
import io.github.ronjunevaldoz.tailwind.style.paddingStyle1
import io.github.ronjunevaldoz.tailwind.style.paddingStyle2
import io.github.ronjunevaldoz.tailwind.style.paddingStyle4
import io.github.ronjunevaldoz.tailwind.style.paddingStyle8
import io.github.ronjunevaldoz.tailwind.style.ringStyle
import io.github.ronjunevaldoz.tailwind.style.ringStyle2
import io.github.ronjunevaldoz.tailwind.style.ringStyle4
import io.github.ronjunevaldoz.tailwind.style.ringStyle8
import io.github.ronjunevaldoz.tailwind.style.rotateZStyle
import io.github.ronjunevaldoz.tailwind.style.roundedStyleFull
import io.github.ronjunevaldoz.tailwind.style.roundedStyleLg
import io.github.ronjunevaldoz.tailwind.style.roundedStyleMd
import io.github.ronjunevaldoz.tailwind.style.roundedStyleXs
import io.github.ronjunevaldoz.tailwind.style.saturateStyle200
import io.github.ronjunevaldoz.tailwind.style.scaleStyle150
import io.github.ronjunevaldoz.tailwind.style.scaleStyle50
import io.github.ronjunevaldoz.tailwind.style.sepiaStyle
import io.github.ronjunevaldoz.tailwind.style.shadowStyleLg
import io.github.ronjunevaldoz.tailwind.style.shadowStyleMd
import io.github.ronjunevaldoz.tailwind.style.shadowStyleXl
import io.github.ronjunevaldoz.tailwind.style.shadowStyleXs
import io.github.ronjunevaldoz.tailwind.style.textColorStyle
import io.github.ronjunevaldoz.tailwind.style.textDecorationStyle
import io.github.ronjunevaldoz.tailwind.style.transitionStyle
import io.github.ronjunevaldoz.tailwind.style.translateXStyle4
import io.github.ronjunevaldoz.tailwind.style.translateYStyle4
import io.github.ronjunevaldoz.tailwind.style.widthStyle

/**
 * Standalone, web-only live demo of every `tailwind-style-experimental` extension --
 * this module is pinned to Compose Multiplatform 1.12.0-beta01
 * (see build.gradle.kts's own comment for why), so it can only live-render here, in its own
 * isolated build, not embedded in the main showcase app (which stays on 1.11.1 -- mixing the
 * two in one binary crashes at runtime with a Skiko WebAssembly LinkError, confirmed
 * empirically). Every demo below dogfoods the Style API itself for its own layout/chrome
 * (no `tailwind-compose` dependency), so the whole binary is homogeneously one Compose version.
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

        DemoSection("Ring -- ringStyle/2/4/8") { RingDemo() }
        DemoSection("Shadow -- shadowStyleXs/Md/Lg/Xl") { ShadowDemo() }
        DemoSection("Border + Rounded -- borderStyle2/4/8, roundedStyleXs/Md/Full") { BorderDemo() }
        DemoSection("Color -- bgStyle, textColorStyle") { ColorDemo() }
        DemoSection("Opacity -- opacityStyle0/50/100") { OpacityDemo() }
        DemoSection("Filters -- grayscale/invert/sepia/brightness/contrast/saturate") { FilterDemo() }
        DemoSection("Transform -- scaleStyle, rotateZStyle, translateXStyle/translateYStyle") { TransformDemo() }
        DemoSection("Transition -- transitionStyle (click to toggle)") { TransitionDemo() }
        DemoSection("Spacing -- paddingStyle1/2/4/8, marginStyle4") { SpacingDemo() }
        DemoSection("Sizing -- widthStyle/heightStyle, minWidthStyle/maxWidthStyle") { SizingDemo() }
        DemoSection("Typography -- fontSizeStyle, fontWeightStyle, letterSpacingStyle, fontFamilyStyle") { TypographyDemo() }
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
                    Style
                        .bgStyle(Color(0xFFF8FAFC))
                        .roundedStyleMd()
                        .paddingStyle4(),
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
private fun RingDemo() {
    Row(Modifier.padding(8.dp)) {
        listOf(
            Style.ringStyle(TwColors.blue500),
            Style.ringStyle2(TwColors.blue500),
            Style.ringStyle4(TwColors.blue500),
            Style.ringStyle8(TwColors.blue500),
        ).forEach { ring ->
            Box(
                Modifier
                    .padding(8.dp)
                    .size(40.dp)
                    .styleable(style = Style.bgStyle(Color.White).then(ring)),
            )
        }
    }
}

@OptIn(ExperimentalFoundationStyleApi::class)
@Suppress("ktlint:standard:function-naming")
@Composable
private fun ShadowDemo() {
    Row(Modifier.padding(8.dp)) {
        listOf(
            Style.shadowStyleXs(),
            Style.shadowStyleMd(),
            Style.shadowStyleLg(),
            Style.shadowStyleXl(),
        ).forEach { shadow ->
            Box(
                Modifier
                    .padding(12.dp)
                    .size(40.dp)
                    .styleable(style = Style.bgStyle(Color.White).then(shadow)),
            )
        }
    }
}

@OptIn(ExperimentalFoundationStyleApi::class)
@Suppress("ktlint:standard:function-naming")
@Composable
private fun BorderDemo() {
    Row(Modifier.padding(8.dp)) {
        listOf(
            Style.borderStyle2(TwColors.slate800).roundedStyleXs(),
            Style.borderStyle4(TwColors.slate800).roundedStyleMd(),
            Style.borderStyle8(TwColors.slate800).roundedStyleFull(),
        ).forEach { border ->
            Box(
                Modifier
                    .padding(8.dp)
                    .size(48.dp)
                    .styleable(style = Style.bgStyle(Color.White).then(border)),
            )
        }
    }
}

@OptIn(ExperimentalFoundationStyleApi::class)
@Suppress("ktlint:standard:function-naming")
@Composable
private fun ColorDemo() {
    Row(Modifier.padding(8.dp)) {
        listOf(TwColors.blue500, TwColors.red500, TwColors.green500, TwColors.amber500).forEach { color ->
            Box(
                Modifier
                    .padding(8.dp)
                    .size(40.dp)
                    .styleable(style = Style.bgStyle(color).roundedStyleXs()),
            )
        }
        Box(
            Modifier
                .padding(8.dp)
                .styleable(style = Style.bgStyle(TwColors.slate800).roundedStyleXs().paddingStyle2()),
        ) {
            Box(Modifier.styleable(style = Style.textColorStyle(TwColors.amber400))) {
                BasicText("text-amber-400")
            }
        }
    }
}

@OptIn(ExperimentalFoundationStyleApi::class)
@Suppress("ktlint:standard:function-naming")
@Composable
private fun OpacityDemo() {
    Row(
        Modifier
            .padding(8.dp)
            .styleable(style = Style.bgStyle(TwColors.slate800).paddingStyle2()),
    ) {
        listOf(Style.opacityStyle0(), Style.opacityStyle50(), Style.opacityStyle100()).forEach { opacity ->
            Box(
                Modifier
                    .padding(8.dp)
                    .size(40.dp)
                    .styleable(style = Style.bgStyle(TwColors.amber400).then(opacity)),
            )
        }
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
                    .styleable(style = Style.bgStyle(TwColors.orange500).then(filter)),
            )
        }
    }
}

@OptIn(ExperimentalFoundationStyleApi::class)
@Suppress("ktlint:standard:function-naming")
@Composable
private fun TransformDemo() {
    Row(Modifier.padding(24.dp)) {
        listOf(
            Style.scaleStyle50(),
            Style.scaleStyle150(),
            Style.rotateZStyle(45f),
            Style.translateXStyle4(),
            Style.translateYStyle4(),
        ).forEach { transform ->
            Box(
                Modifier
                    .padding(16.dp)
                    .size(32.dp)
                    .styleable(style = Style.bgStyle(TwColors.blue500).then(transform)),
            )
        }
    }
}

@OptIn(ExperimentalFoundationStyleApi::class)
@Suppress("ktlint:standard:function-naming")
@Composable
private fun TransitionDemo() {
    var expanded by remember { mutableStateOf(false) }
    Box(
        Modifier
            .padding(8.dp)
            .clickable { expanded = !expanded }
            .styleable(
                style =
                    Style.transitionStyle(durationMs = TwDuration.D300) {
                        background(if (expanded) TwColors.blue500 else TwColors.slate300)
                        contentPadding(if (expanded) 32.dp else 8.dp)
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
                    .styleable(style = Style.bgStyle(TwColors.slate800).marginStyle4().then(padding)),
            ) {
                Box(Modifier.size(16.dp).styleable(style = Style.bgStyle(TwColors.amber400)))
            }
        }
    }
}

@OptIn(ExperimentalFoundationStyleApi::class)
@Suppress("ktlint:standard:function-naming")
@Composable
private fun SizingDemo() {
    Row(Modifier.padding(8.dp)) {
        Box(
            Modifier
                .padding(8.dp)
                .styleable(style = Style.bgStyle(TwColors.blue500).widthStyle(TwSpacing.scale8).heightStyle(TwSpacing.scale4)),
        )
        Box(
            Modifier
                .padding(8.dp)
                .width(80.dp)
                .styleable(style = Style.bgStyle(TwColors.blue500).minWidthStyle(120.dp).heightStyle(TwSpacing.scale4)),
        )
        Box(
            Modifier
                .padding(8.dp)
                .width(160.dp)
                .styleable(style = Style.bgStyle(TwColors.blue500).maxWidthStyle(60.dp).heightStyle(TwSpacing.scale4)),
        )
    }
}

@OptIn(ExperimentalFoundationStyleApi::class)
@Suppress("ktlint:standard:function-naming")
@Composable
private fun TypographyDemo() {
    Column(Modifier.padding(8.dp)) {
        Box(Modifier.styleable(style = Style.fontSizeStyleSm())) { BasicText("fontSizeStyleSm") }
        Box(Modifier.styleable(style = Style.fontSizeStyleLg().fontWeightStyleBold())) {
            BasicText("fontSizeStyleLg + fontWeightStyleBold")
        }
        Box(Modifier.styleable(style = Style.fontSizeStyleXl().letterSpacingStyleWide())) {
            BasicText("fontSizeStyleXl + letterSpacingStyleWide")
        }
        Box(Modifier.styleable(style = Style.fontFamilyStyleSerif())) { BasicText("fontFamilyStyleSerif") }
        Box(Modifier.styleable(style = Style.fontFamilyStyleMono())) { BasicText("fontFamilyStyleMono") }
        Box(Modifier.styleable(style = Style.textDecorationStyle(TextDecoration.Underline))) {
            BasicText("textDecorationStyle(Underline)")
        }
    }
}
