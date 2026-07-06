#!/usr/bin/env python3
"""Generates tailwind-core token files from tailwind_scale.py.

Run from repo root: python3 scripts/codegen/generate_tokens.py
"""
import os

from tailwind_scale import COLOR_PALETTE_OKLCH, GENERATED_HEADER, SPACING_SCALE, scale_suffix

REPO_ROOT = os.path.dirname(os.path.dirname(os.path.dirname(os.path.abspath(__file__))))
CORE_PKG_DIR = os.path.join(
    REPO_ROOT, "tailwind-core", "src", "commonMain", "kotlin",
    "io", "github", "ronjunevaldoz", "tailwind", "core",
)


def generate_spacing_tokens() -> str:
    lines = [
        GENERATED_HEADER.format(script="generate_tokens.py"),
        "package io.github.ronjunevaldoz.tailwind.core\n",
        "import androidx.compose.ui.unit.Dp",
        "import androidx.compose.ui.unit.dp\n",
        "/**",
        " * Tailwind's default spacing scale (1rem == 16dp). Each entry corresponds",
        " * to a Tailwind spacing step, e.g. `scale4` == Tailwind's `4` == `1rem`.",
        " */",
        "object TwSpacing {",
    ]
    for key, value in SPACING_SCALE:
        prop_name = f"scale{scale_suffix(key)}"
        lines.append(f"    val {prop_name}: Dp = {value}.dp")
    lines.append("")
    lines.append("    /** Lookup table keyed by the Tailwind scale step name (e.g. \"4\", \"0_5\", \"px\"). */")
    lines.append("    val scale: Map<String, Dp> = mapOf(")
    for key, _ in SPACING_SCALE:
        prop_name = f"scale{scale_suffix(key)}"
        lines.append(f'        "{key}" to {prop_name},')
    lines.append("    )")
    lines.append("}")
    lines.append("")
    return "\n".join(lines)


def generate_color_tokens() -> str:
    lines = [
        GENERATED_HEADER.format(script="generate_tokens.py"),
        "package io.github.ronjunevaldoz.tailwind.core\n",
        "import androidx.compose.ui.graphics.Color\n",
        "/**",
        " * Tailwind v4's default color palette — 26 hues x 11 shades (50-950),",
        " * plus black/white/transparent. Each value is defined from its authoritative",
        " * OKLCH(lightness, chroma, hue) source values (see Oklch.kt) rather than a",
        " * hand-picked hex approximation.",
        " */",
        "@Suppress(\"MagicNumber\")",
        "object TwColors {",
        "    val black: Color = Color(0xFF000000)",
        "    val white: Color = Color(0xFFFFFFFF)",
        "    val transparent: Color = Color(0x00000000)",
        "",
    ]
    for hue, shades in COLOR_PALETTE_OKLCH.items():
        for shade, (l, c, h) in shades.items():
            prop_name = f"{hue}{shade}"
            lines.append(f"    val {prop_name}: Color = Oklch({l}f, {c}f, {h}f).toColor()")
        lines.append("")
    lines.append("}")
    lines.append("")
    return "\n".join(lines)


def write(path: str, content: str) -> None:
    os.makedirs(os.path.dirname(path), exist_ok=True)
    with open(path, "w") as f:
        f.write(content)
    print(f"wrote {path} ({len(content.splitlines())} lines)")


if __name__ == "__main__":
    write(os.path.join(CORE_PKG_DIR, "TwSpacing.kt"), generate_spacing_tokens())
    write(os.path.join(CORE_PKG_DIR, "TwColors.kt"), generate_color_tokens())
