package io.github.ronjunevaldoz.tailwind.core

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Tailwind's `shadow-*` scale, approximated as Compose elevation (a single Dp
 * value driving Android's ambient/spot shadow model). CSS `box-shadow` supports
 * arbitrary offset/blur/spread/color per layer, which Compose's [androidx.compose.ui.draw.shadow]
 * has no equivalent for — this is a visual approximation of Tailwind's scale, not
 * a faithful reproduction. `shadow-inner` has no Compose primitive (no inset-shadow
 * support) and is intentionally not included.
 *
 * Tailwind v4 renamed this whole scale: v3's `shadow-sm` is v4's `shadow-xs`, and v3's
 * bare/DEFAULT `shadow` is v4's `shadow-sm`, one tier down from where those names used
 * to point (confirmed against the live docs, not assumed). [sm] and [base] below are kept
 * at their original v3 values under `@Deprecated` — not renamed in place, since Kotlin
 * can't have two declarations named `sm` for two different values at once. [xs] is the
 * new v4-correct name for the same value [sm] already held. There is no equally-named
 * replacement for [base] yet: v4's `shadow-sm` (3.dp) can't be exposed as `TwShadow.sm`
 * while the deprecated `sm` (1.dp) still occupies that name — that only becomes possible
 * once [sm] is removed in a future breaking release.
 */
object TwShadow {
    val none: Dp = 0.dp

    /** v4's `shadow-2xs` — smaller than [xs], new in v4, no v3 equivalent. */
    val xs2: Dp = 0.5.dp

    /** v4's `shadow-xs`. Was called `sm` in v3 (see [sm]). */
    val xs: Dp = 1.dp

    @Deprecated("Tailwind v3 naming -- this is v4's shadow-xs (TwShadow.xs).", ReplaceWith("xs"))
    val sm: Dp = 1.dp

    @Deprecated(
        "Tailwind v3's bare/DEFAULT shadow -- v4 calls this shadow-sm, but that name is " +
            "reserved by the deprecated TwShadow.sm (v3's different 1.dp value) until it's removed.",
    )
    val base: Dp = 3.dp
    val md: Dp = 6.dp
    val lg: Dp = 10.dp
    val xl: Dp = 15.dp
    val xl2: Dp = 25.dp
}
