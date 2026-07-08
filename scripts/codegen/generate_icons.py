#!/usr/bin/env python3
"""Generates tailwind-icons-outline ImageVector files from Heroicons (github.com/tailwindlabs/heroicons,
MIT, Tailwind Labs) Outline SVGs.

This script is a thin driver only -- it never parses SVG path data or emits Kotlin itself.
Every icon is compiled by the kotlin-multiplatform-imagevector-generator skill's own
converter (convert_image_to_imagevector.py), per that skill's hard rule against hand-written
ImageVector.Builder bodies. Regenerating requires that skill installed locally.

Requires picosvg (`pip install picosvg`, Google Fonts, Apache-2.0): the converter has no
stroke/stroke-width handling at all, so feeding it a Heroicons SVG directly (fill="none"
stroke="currentColor") makes it naively fill the raw stroke centerline path -- a solid
blob, not a hollow outline. picosvg's topicosvg() does real stroke-to-fill conversion
(same technique as font-hinting tools), producing the correct hollow shape; verified
pixel-for-pixel against a rasterized original before this became a required step, not an
optional one.

Run from repo root:
    python3 scripts/codegen/generate_icons.py --source /path/to/heroicons/optimized/24/outline

Source SVGs: sparse-clone just the outline set to avoid GitHub raw-content rate limits on
324 individual fetches:
    git clone --depth 1 --filter=blob:none --sparse https://github.com/tailwindlabs/heroicons.git /tmp/heroicons-src
    cd /tmp/heroicons-src && git sparse-checkout set optimized/24/outline

The package is nested as "...icons.outline" (not just "...icons") so a future sibling
module for another Heroicons style (Solid/Mini/Micro) can coexist on the same classpath
without colliding on identical fully-qualified class names (e.g. two different
BellKt.class from two different jars, both in package "...icons").
"""
import argparse
import os
import subprocess
import sys
import tempfile

REPO_ROOT = os.path.dirname(os.path.dirname(os.path.dirname(os.path.abspath(__file__))))
ICONS_PKG_DIR = os.path.join(
    REPO_ROOT, "tailwind", "icons-outline", "src", "commonMain", "kotlin",
    "io", "github", "ronjunevaldoz", "tailwind", "icons", "outline",
)
DEFAULT_CONVERTER = os.path.expanduser(
    "~/.claude/skills/kotlin-multiplatform-imagevector-generator/scripts/convert_image_to_imagevector.py"
)
GROUP_ID = "io.github.ronjunevaldoz.tailwind.icons.outline"

# The converter hardcodes its package as f"{group_id}.core.designsystem.icons" (that
# suffix comes from the kotlin-multiplatform-design-system skill's own folder convention,
# not something this project uses, and there's no CLI flag to override it). Rewriting just
# this one line post-conversion isn't the "hand-edit a generated file" the skill's Rule 3
# warns against -- it never touches path/art data, it's a deterministic, scripted part of
# this driver's own pipeline, identical on every re-run.
_WRONG_PACKAGE = f"package {GROUP_ID}.core.designsystem.icons"
_RIGHT_PACKAGE = f"package {GROUP_ID}"


def pascal_case(icon_name: str) -> str:
    return "".join(word.capitalize() for word in icon_name.split("-"))


def flatten_strokes_to_fill(svg_path: str, scratch_dir: str) -> str:
    """Runs the source SVG through picosvg's stroke-to-fill + arc-flatten normalization.
    Returns the path to the normalized copy (written into scratch_dir, same basename)."""
    from picosvg.svg import SVG

    svg = SVG.parse(svg_path).topicosvg()
    out_path = os.path.join(scratch_dir, os.path.basename(svg_path))
    with open(out_path, "w", encoding="utf-8") as f:
        f.write(svg.tostring())
    return out_path


def main() -> int:
    parser = argparse.ArgumentParser()
    parser.add_argument("--source", required=True, help="Dir of Heroicons outline .svg files")
    parser.add_argument("--converter", default=DEFAULT_CONVERTER, help="Path to convert_image_to_imagevector.py")
    args = parser.parse_args()

    if not os.path.isfile(args.converter):
        print(f"Converter script not found at {args.converter}", file=sys.stderr)
        print("Install the kotlin-multiplatform-imagevector-generator skill or pass --converter.", file=sys.stderr)
        return 1

    try:
        import picosvg  # noqa: F401
    except ImportError:
        print("picosvg not installed. Run: pip install picosvg", file=sys.stderr)
        return 1

    os.makedirs(ICONS_PKG_DIR, exist_ok=True)

    svg_files = sorted(f for f in os.listdir(args.source) if f.endswith(".svg"))
    succeeded, failed = [], []
    with tempfile.TemporaryDirectory() as scratch_dir:
        for filename in svg_files:
            icon_name = filename[: -len(".svg")]
            prop_name = pascal_case(icon_name)
            try:
                normalized_svg = flatten_strokes_to_fill(os.path.join(args.source, filename), scratch_dir)
            except Exception as e:  # noqa: BLE001 -- report and continue the batch
                failed.append((icon_name, f"picosvg normalization failed: {e}"))
                continue

            result = subprocess.run(
                [
                    sys.executable, args.converter,
                    normalized_svg,
                    "--name", prop_name,
                    "--group-id", GROUP_ID,
                    "--color-mode", "semantic",
                    "--output", ICONS_PKG_DIR,
                ],
                capture_output=True, text=True,
            )
            if result.returncode == 0:
                generated_file = os.path.join(ICONS_PKG_DIR, f"{prop_name}.kt")
                with open(generated_file, "r", encoding="utf-8") as f:
                    content = f.read()
                fixed = content.replace(_WRONG_PACKAGE, _RIGHT_PACKAGE, 1)
                if fixed == content:
                    failed.append((icon_name, "expected package line not found -- converter output shape may have changed"))
                    continue
                with open(generated_file, "w", encoding="utf-8") as f:
                    f.write(fixed)
                succeeded.append(icon_name)
            else:
                failed.append((icon_name, result.stderr.strip().splitlines()[-1] if result.stderr else "unknown error"))

    print(f"\n{len(succeeded)}/{len(svg_files)} icons converted successfully.")
    if failed:
        print(f"{len(failed)} failed:")
        for name, reason in failed:
            print(f"  {name}: {reason}")
    return 0 if not failed else 1


if __name__ == "__main__":
    raise SystemExit(main())
