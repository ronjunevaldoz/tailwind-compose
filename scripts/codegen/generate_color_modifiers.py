#!/usr/bin/env python3
"""Generates tailwind-color/.../Color.kt from tailwind_scale.py.

Run from repo root: python3 scripts/codegen/generate_color_modifiers.py
"""
import os

from tailwind_scale import COLOR_PALETTE_OKLCH, GENERATED_HEADER

REPO_ROOT = os.path.dirname(os.path.dirname(os.path.dirname(os.path.abspath(__file__))))
MODIFIERS_PKG_DIR = os.path.join(
    REPO_ROOT, "tailwind", "color", "src", "commonMain", "kotlin",
    "io", "github", "ronjunevaldoz", "tailwind", "modifiers",
)

SPECIAL_COLORS = ["black", "white", "transparent"]


def all_color_names() -> list[str]:
    names = list(SPECIAL_COLORS)
    for hue, shades in COLOR_PALETTE_OKLCH.items():
        for shade in shades:
            names.append(f"{hue}{shade}")
    return names


def capitalize(name: str) -> str:
    return name[0].upper() + name[1:]


def generate() -> str:
    lines = [
        GENERATED_HEADER.format(script="generate_color_modifiers.py"),
        "package io.github.ronjunevaldoz.tailwind.modifiers\n",
        "import androidx.compose.foundation.background",
        "import androidx.compose.ui.Modifier",
        "import androidx.compose.ui.graphics.Color",
        "import androidx.compose.ui.text.TextStyle",
        "import io.github.ronjunevaldoz.tailwind.core.TwColors\n",
        "/**",
        " * Tailwind's arbitrary-value syntax (`bg-[#1da1f2]`, `text-[#1da1f2]`) for a color that",
        " * isn't one of the named palette steps below -- e.g. a color picked at runtime from a",
        " * `List<Color>` rather than known at compile time.",
        " */",
        "fun Modifier.bg(color: Color): Modifier = this.background(color)\n",
        "fun TextStyle.text(color: Color): TextStyle = this.copy(color = color)\n",
        "/** Background-color Modifier extensions over Tailwind's default palette. */",
    ]
    for name in all_color_names():
        fname = f"bg{capitalize(name)}"
        lines.append(f"fun Modifier.{fname}(): Modifier = this.background(TwColors.{name})")
    lines.append("")
    lines.append("/** Text-color TextStyle extensions over Tailwind's default palette. */")
    for name in all_color_names():
        fname = f"text{capitalize(name)}"
        lines.append(f"fun TextStyle.{fname}(): TextStyle = this.copy(color = TwColors.{name})")
    lines.append("")
    return "\n".join(lines)


if __name__ == "__main__":
    path = os.path.join(MODIFIERS_PKG_DIR, "Color.kt")
    os.makedirs(os.path.dirname(path), exist_ok=True)
    content = generate()
    with open(path, "w") as f:
        f.write(content)
    print(f"wrote {path} ({len(content.splitlines())} lines)")
