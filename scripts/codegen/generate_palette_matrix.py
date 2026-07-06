#!/usr/bin/env python3
"""Generates the showcase's full 26x11 color palette matrix data.

Run from repo root: python3 scripts/codegen/generate_palette_matrix.py
"""
import os

from tailwind_scale import COLOR_PALETTE_OKLCH, GENERATED_HEADER

REPO_ROOT = os.path.dirname(os.path.dirname(os.path.dirname(os.path.abspath(__file__))))
SHOWCASE_SECTIONS_DIR = os.path.join(
    REPO_ROOT, "showcase", "shared", "src", "commonMain", "kotlin",
    "io", "github", "ronjunevaldoz", "tailwind", "showcase", "sections",
)


def generate_palette_matrix() -> str:
    lines = [
        GENERATED_HEADER.format(script="generate_palette_matrix.py"),
        "package io.github.ronjunevaldoz.tailwind.showcase.sections\n",
        "import androidx.compose.ui.graphics.Color",
        "import io.github.ronjunevaldoz.tailwind.core.TwColors\n",
        "/**",
        " * One swatch in the full palette matrix: its color, and the exact OKLCH source",
        " * triple (lightness %, chroma, hue) used to derive it -- see Oklch.kt.",
        " */",
        "data class PaletteSwatch(",
        "    val hue: String,",
        "    val shade: Int,",
        "    val color: Color,",
        "    val oklch: String,",
        ")\n",
        "/**",
        " * All 26 hues x 11 shades of TwColors, generated from the same OKLCH source data",
        " * as TwColors.kt itself (see scripts/codegen/tailwind_scale.py) -- renders the full",
        " * palette matrix and demonstrates that every swatch is OKLCH-derived, not hex-picked.",
        " */",
        '@Suppress("MagicNumber")',
        "val PALETTE_MATRIX: List<List<PaletteSwatch>> =",
        "    listOf(",
    ]
    for hue, shades in COLOR_PALETTE_OKLCH.items():
        lines.append("        listOf(")
        for shade, (l, c, h) in shades.items():
            pct = round(l * 100, 1)
            oklch_str = f"oklch({pct}% {c} {h})"
            lines.append(f'            PaletteSwatch("{hue}", {shade}, TwColors.{hue}{shade}, "{oklch_str}"),')
        lines.append("        ),")
    lines.append("    )")
    lines.append("")
    return "\n".join(lines)


def write(path: str, content: str) -> None:
    os.makedirs(os.path.dirname(path), exist_ok=True)
    with open(path, "w") as f:
        f.write(content)
    print(f"wrote {path} ({len(content.splitlines())} lines)")


if __name__ == "__main__":
    write(os.path.join(SHOWCASE_SECTIONS_DIR, "PaletteSwatch.kt"), generate_palette_matrix())
