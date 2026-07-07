package io.github.ronjunevaldoz.tailwind.core

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

/** Tailwind's default font-size scale, paired with its default line-height. */
object TwFontSize {
    val xs: TextUnit = 12.sp
    val sm: TextUnit = 14.sp
    val base: TextUnit = 16.sp
    val lg: TextUnit = 18.sp
    val xl: TextUnit = 20.sp
    val xl2: TextUnit = 24.sp
    val xl3: TextUnit = 30.sp
    val xl4: TextUnit = 36.sp
    val xl5: TextUnit = 48.sp
    val xl6: TextUnit = 60.sp
    val xl7: TextUnit = 72.sp
    val xl8: TextUnit = 96.sp
    val xl9: TextUnit = 128.sp
}

/** Tailwind's default line-height paired to each [TwFontSize] step. */
object TwLineHeight {
    val xs: TextUnit = 16.sp
    val sm: TextUnit = 20.sp
    val base: TextUnit = 24.sp
    val lg: TextUnit = 28.sp
    val xl: TextUnit = 28.sp
    val xl2: TextUnit = 32.sp
    val xl3: TextUnit = 36.sp
    val xl4: TextUnit = 40.sp
    val xl5: TextUnit = 48.sp
    val xl6: TextUnit = 60.sp
    val xl7: TextUnit = 72.sp
    val xl8: TextUnit = 96.sp
    val xl9: TextUnit = 128.sp
}

/** Tailwind's `font-*` weight scale, aliasing [FontWeight]'s standard values. */
object TwFontWeight {
    val thin: FontWeight = FontWeight.Thin
    val extraLight: FontWeight = FontWeight.ExtraLight
    val light: FontWeight = FontWeight.Light
    val normal: FontWeight = FontWeight.Normal
    val medium: FontWeight = FontWeight.Medium
    val semiBold: FontWeight = FontWeight.SemiBold
    val bold: FontWeight = FontWeight.Bold
    val extraBold: FontWeight = FontWeight.ExtraBold
    val black: FontWeight = FontWeight.Black
}

/**
 * Tailwind's `font-*` family scale (`font-sans`, `font-serif`, `font-mono`). Tailwind's
 * actual stacks are web-specific system-font lists (e.g. `ui-sans-serif, system-ui, ...`)
 * with no 1:1 Compose equivalent, so this maps to Compose's generic [FontFamily]
 * categories instead — each platform substitutes its own default sans/serif/monospace
 * font for that category, which is the closest analogue to Tailwind's "use the
 * platform's native stack" intent.
 */
object TwFontFamily {
    val sans: FontFamily = FontFamily.SansSerif
    val serif: FontFamily = FontFamily.Serif
    val mono: FontFamily = FontFamily.Monospace
}

/** Tailwind's `tracking-*` letter-spacing scale. */
@Suppress("MagicNumber")
object TwTracking {
    val tighter: TextUnit = (-0.05).em
    val tight: TextUnit = (-0.025).em
    val normal: TextUnit = 0.em
    val wide: TextUnit = 0.025.em
    val wider: TextUnit = 0.05.em
    val widest: TextUnit = 0.1.em
}
