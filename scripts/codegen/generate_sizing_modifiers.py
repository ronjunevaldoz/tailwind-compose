#!/usr/bin/env python3
"""Generates tailwind-modifiers/.../Sizing.kt from tailwind_scale.py.

Run from repo root: python3 scripts/codegen/generate_sizing_modifiers.py
"""
import os

from tailwind_scale import GENERATED_HEADER, SPACING_SCALE, scale_suffix

REPO_ROOT = os.path.dirname(os.path.dirname(os.path.dirname(os.path.abspath(__file__))))
MODIFIERS_PKG_DIR = os.path.join(
    REPO_ROOT, "tailwind-modifiers", "src", "commonMain", "kotlin",
    "io", "github", "ronjunevaldoz", "tailwind", "modifiers",
)

# (function prefix, Modifier call template — {ref} is substituted with the Dp token reference)
SIZE_CATEGORIES = [
    ("w", "this.width({ref})"),
    ("h", "this.height({ref})"),
    ("minW", "this.widthIn(min = {ref})"),
    ("maxW", "this.widthIn(max = {ref})"),
    ("minH", "this.heightIn(min = {ref})"),
    ("maxH", "this.heightIn(max = {ref})"),
    ("size", "this.size({ref})"),
]


def token_ref(key: str) -> str:
    return f"TwSpacing.scale{scale_suffix(key)}"


def fn_suffix(key: str) -> str:
    return scale_suffix(key)


def generate_scaled() -> list[str]:
    lines = []
    for prefix, template in SIZE_CATEGORIES:
        for key, _ in SPACING_SCALE:
            fname = f"{prefix}{fn_suffix(key)}"
            body = template.format(ref=token_ref(key))
            lines.append(f"fun Modifier.{fname}(): Modifier = {body}")
        lines.append("")
    return lines


def generate_fixed() -> list[str]:
    return [
        "/** Tailwind's `w-full` / `h-full` — fills the available space on that axis. */",
        "fun Modifier.wFull(): Modifier = this.fillMaxWidth()",
        "fun Modifier.hFull(): Modifier = this.fillMaxHeight()",
        "",
    ]


def generate() -> str:
    header = [
        GENERATED_HEADER.format(script="generate_sizing_modifiers.py"),
        "package io.github.ronjunevaldoz.tailwind.modifiers\n",
        "import androidx.compose.foundation.layout.fillMaxHeight",
        "import androidx.compose.foundation.layout.fillMaxWidth",
        "import androidx.compose.foundation.layout.height",
        "import androidx.compose.foundation.layout.heightIn",
        "import androidx.compose.foundation.layout.size",
        "import androidx.compose.foundation.layout.width",
        "import androidx.compose.foundation.layout.widthIn",
        "import androidx.compose.ui.Modifier",
        "import io.github.ronjunevaldoz.tailwind.core.TwSpacing\n",
    ]
    body = generate_scaled() + generate_fixed()
    return "\n".join(header + body) + "\n"


if __name__ == "__main__":
    path = os.path.join(MODIFIERS_PKG_DIR, "Sizing.kt")
    os.makedirs(os.path.dirname(path), exist_ok=True)
    content = generate()
    with open(path, "w") as f:
        f.write(content)
    print(f"wrote {path} ({len(content.splitlines())} lines)")
