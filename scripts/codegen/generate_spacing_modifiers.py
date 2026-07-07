#!/usr/bin/env python3
"""Generates tailwind-layout/.../Spacing.kt from tailwind_scale.py.

Run from repo root: python3 scripts/codegen/generate_spacing_modifiers.py
"""
import os

from tailwind_scale import GENERATED_HEADER, SPACING_SCALE, scale_suffix

REPO_ROOT = os.path.dirname(os.path.dirname(os.path.dirname(os.path.abspath(__file__))))
MODIFIERS_PKG_DIR = os.path.join(
    REPO_ROOT, "tailwind-layout", "src", "commonMain", "kotlin",
    "io", "github", "ronjunevaldoz", "tailwind", "modifiers",
)

# (function prefix, Modifier.padding named parameter, or None for the "all sides" overload)
PADDING_DIRECTIONS = [
    ("p", None),
    ("pt", "top"),
    ("pr", "end"),
    ("pb", "bottom"),
    ("pl", "start"),
    ("px", "horizontal"),
    ("py", "vertical"),
]

GAP_DIRECTIONS = [
    ("gap", None),
    ("gapX", "horizontal"),
    ("gapY", "vertical"),
]


def token_ref(key: str) -> str:
    return f"TwSpacing.scale{scale_suffix(key)}"


def fn_suffix(key: str) -> str:
    return scale_suffix(key)


def generate_padding() -> list[str]:
    lines = []
    for prefix, param in PADDING_DIRECTIONS:
        for key, _ in SPACING_SCALE:
            fname = f"{prefix}{fn_suffix(key)}"
            ref = token_ref(key)
            if param is None:
                body = f"this.padding({ref})"
            else:
                body = f"this.padding({param} = {ref})"
            lines.append(f"fun Modifier.{fname}(): Modifier = {body}")
        lines.append("")
    return lines


def generate_margin() -> list[str]:
    lines = [
        "/**",
        " * Compose has no distinct margin primitive — [Modifier.padding] applied as the",
        " * *outermost* modifier in a chain produces the same visual effect as external",
        " * margin. These `m*` functions are identical to their `p*` counterparts; the",
        " * separate name documents *intent* (outer spacing) to match Tailwind's mental model.",
        " */",
    ]
    for prefix, param in PADDING_DIRECTIONS:
        m_prefix = "m" + prefix[1:]  # "p" -> "m", "pt" -> "mt", "px" -> "mx", ...
        for key, _ in SPACING_SCALE:
            fname = f"{m_prefix}{fn_suffix(key)}"
            ref = token_ref(key)
            if param is None:
                body = f"this.padding({ref})"
            else:
                body = f"this.padding({param} = {ref})"
            lines.append(f"fun Modifier.{fname}(): Modifier = {body}")
        lines.append("")
    return lines


def generate_negative_margin() -> list[str]:
    lines = [
        "/**",
        " * Negative margin emulation via [Modifier.offset]. This shifts the *visual*",
        " * position of the element only — unlike CSS negative margin, it does not",
        " * shrink the layout footprint or pull sibling elements closer. Useful for the",
        " * common case of an element intentionally overlapping its container's edge",
        " * (e.g. a badge bleeding past a card corner).",
        " */",
    ]
    non_zero_steps = [(k, v) for k, v in SPACING_SCALE if k != "0"]
    directional = [
        ("mtNeg", "y", -1),
        ("mbNeg", "y", 1),
        ("mlNeg", "x", -1),
        ("mrNeg", "x", 1),
    ]
    for prefix, axis, sign in directional:
        for key, _ in non_zero_steps:
            fname = f"{prefix}{fn_suffix(key)}"
            ref = token_ref(key)
            offset_expr = f"-{ref}" if sign == -1 else ref
            lines.append(f"fun Modifier.{fname}(): Modifier = this.offset({axis} = {offset_expr})")
        lines.append("")
    # symmetric mxNeg / myNeg: negative on both sides of that axis isn't a single offset
    # (offset is a single 2D translation) — expose them as an offset in the "start/top"
    # direction, matching the common "pull toward top-left" bleed use case.
    for prefix, axis in (("mxNeg", "x"), ("myNeg", "y")):
        for key, _ in non_zero_steps:
            fname = f"{prefix}{fn_suffix(key)}"
            ref = token_ref(key)
            lines.append(f"fun Modifier.{fname}(): Modifier = this.offset({axis} = -{ref})")
        lines.append("")
    return lines


def generate_gap() -> list[str]:
    lines = [
        "/** Scale-named wrappers over [Arrangement.spacedBy] for use as Row/Column arrangement. */",
    ]
    for prefix, _ in GAP_DIRECTIONS:
        for key, _ in SPACING_SCALE:
            fname = f"{prefix}{fn_suffix(key)}"
            ref = token_ref(key)
            lines.append(
                f"fun {fname}(): Arrangement.HorizontalOrVertical = Arrangement.spacedBy({ref})"
            )
        lines.append("")
    return lines


def generate() -> str:
    header = [
        GENERATED_HEADER.format(script="generate_spacing_modifiers.py"),
        "package io.github.ronjunevaldoz.tailwind.modifiers\n",
        "import androidx.compose.foundation.layout.Arrangement",
        "import androidx.compose.foundation.layout.offset",
        "import androidx.compose.foundation.layout.padding",
        "import androidx.compose.ui.Modifier",
        "import io.github.ronjunevaldoz.tailwind.core.TwSpacing\n",
        "/**",
        " * Tailwind's `p*`/`m*`/`gap*` spacing utilities as Modifier and Arrangement",
        " * extensions over [TwSpacing]'s full scale. See the README's \"Modifier order",
        " * matters\" section — these are ordinary padding and must come *after*",
        " * `bg*()`/`rounded*()` in a chain to be visible.",
        " */",
    ]
    body = (
        generate_padding()
        + generate_margin()
        + generate_negative_margin()
        + generate_gap()
    )
    return "\n".join(header + body) + "\n"


if __name__ == "__main__":
    path = os.path.join(MODIFIERS_PKG_DIR, "Spacing.kt")
    os.makedirs(os.path.dirname(path), exist_ok=True)
    content = generate()
    with open(path, "w") as f:
        f.write(content)
    print(f"wrote {path} ({len(content.splitlines())} lines)")
