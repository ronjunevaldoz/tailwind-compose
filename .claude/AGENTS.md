# AGENTS.md — tailwind-compose

This project uses [kmm-agent-skills](https://github.com/ronjunevaldoz/kmm-agent-skills).
Skills are installed in `.claude/skills/`.

## Project overview

Tailwind CSS-inspired, type-safe Kotlin extension functions for Compose Multiplatform
`Modifier` — spacing, sizing, color, typography, borders, opacity, and aspect-ratio
utilities, generated from Tailwind v4's actual token values (see
`docs/tailwind-coverage-matrix.md` for what's covered vs. out of scope).
Group ID: `io.github.ronjunevaldoz`   Published to: Maven Central

## Skill routing

| Topic | Skill |
|---|---|
| Publishing to Maven Central | `kotlin-multiplatform-library-publishing` |
| Code quality (detekt, ktlint) | `kotlin-multiplatform-code-quality` |
| CI automation | `kotlin-multiplatform-ci-github-actions` |
| Screenshot / visual tests | `kotlin-multiplatform-roborazzi` |
| Unit tests | `kotlin-multiplatform-unit-testing` |
| Architecture audit | `kotlin-multiplatform-audit` |
| Harvest consumer lessons | `kotlin-multiplatform-audit` (`--harvest` mode via `/kmm-harvest-lessons`) |
| Feature scaffold reference (module layout) | `kotlin-multiplatform-feature-scaffold` |

## Module graph

| Module | Purpose |
|---|---|
| `:tailwind-core` | Design tokens — `TwSpacing`, `TwColors` (OKLCH-based), `TwFontSize`/`TwLineHeight`/`TwFontWeight`/`TwTracking`, `TwRadius` |
| `:tailwind-modifiers` | `Modifier`/`TextStyle` extension functions consuming those tokens (`Spacing.kt`, `Sizing.kt`, `Color.kt`, `Typography.kt`, `Border.kt`, `Opacity.kt`) |
| `:tailwind-compose` | Public facade — depends on `:tailwind-core` + `:tailwind-modifiers`; the single dependency real consumers add |
| `:showcase:shared`, `:showcase:androidApp`, `:showcase:desktopApp` | Internal demo app for visual verification (Roborazzi), **not published** |

## Codegen

Most token and Modifier files under `tailwind-core`/`tailwind-modifiers` are
code-generated — look for the `// GENERATED CODE — do not edit by hand` header.
Source of truth and generator scripts live in `scripts/codegen/`:

```bash
python3 scripts/codegen/generate_tokens.py             # TwSpacing.kt, TwColors.kt
python3 scripts/codegen/generate_spacing_modifiers.py  # Spacing.kt
python3 scripts/codegen/generate_sizing_modifiers.py   # Sizing.kt
```

To refresh the color palette against a newer Tailwind release, update the source URL
in `scripts/codegen/tailwind_scale.py`'s docstring and re-run the color parsing step
documented there — do not hand-edit hex/OKLCH values.

## API surface rules

- Never remove or rename public symbols without a major version bump
- `VERSION_NAME` in `gradle.properties` is the single source of truth for the release version
- Follow Tailwind's literal naming convention for new utilities (`p4()`, `bgBlue500()`),
  not parameterized calls (`p(4)`) — see `docs/tailwind-coverage-matrix.md` for the
  reasoning and what's already covered

## Commands installed

See `.claude/commands/kmm-*.md` for available slash commands.
Key commands:
- `/kmm-run-audit` — architecture audit with per-finding remediation
- `/kmm-harvest-lessons` — collect patterns to upstream to skills
- `/kmm-verify` — full validation pipeline (build + test + lint)
- `/kmm-check-updates` — check for skill updates
- `/kmm-fix-design` — scan and fix design-token usage violations
