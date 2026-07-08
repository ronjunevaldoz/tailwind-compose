package io.github.ronjunevaldoz.tailwind.core

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Tailwind's default `rounded-*` corner-radius scale.
 *
 * Tailwind v4 renamed this scale: v3's `rounded-sm` is v4's `rounded-xs`, and v3's
 * bare/DEFAULT `rounded` is v4's `rounded-sm`, one tier down from where those names
 * used to point (confirmed against the live docs, not assumed). [sm] and [base] below
 * are kept at their original v3 values under `@Deprecated` -- not renamed in place,
 * since Kotlin can't have two declarations named `sm` for two different values at
 * once. [xs] is the new v4-correct name for the same value [sm] already held. There's
 * no equally-named replacement for [base] yet: v4's `rounded-sm` (4.dp) can't be
 * exposed as `TwRadius.sm` while the deprecated `sm` (2.dp) still occupies that name --
 * that only becomes possible once [sm] is removed in a future breaking release.
 */
object TwRadius {
    val none: Dp = 0.dp

    /** v4's `rounded-xs`. Was called `sm` in v3 (see [sm]). */
    val xs: Dp = 2.dp

    @Deprecated("Tailwind v3 naming -- this is v4's rounded-xs (TwRadius.xs).", ReplaceWith("xs"))
    val sm: Dp = 2.dp

    @Deprecated(
        "Tailwind v3's bare/DEFAULT rounded -- v4 calls this rounded-sm, but that name is " +
            "reserved by the deprecated TwRadius.sm (v3's different 2.dp value) until it's removed.",
    )
    val base: Dp = 4.dp
    val md: Dp = 6.dp
    val lg: Dp = 8.dp
    val xl: Dp = 12.dp
    val xl2: Dp = 16.dp
    val xl3: Dp = 24.dp

    /** v4's `rounded-4xl` -- new in v4, larger than [xl3], no v3 equivalent. */
    val xl4: Dp = 32.dp

    /** Large enough to fully round any realistic mobile component. */
    val full: Dp = 9999.dp
}
