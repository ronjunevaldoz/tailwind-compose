# tailwind-compose

Tailwind CSS-inspired, type-safe Kotlin extension functions for Compose Multiplatform
`Modifier` and `TextStyle` — spacing, sizing, color, typography, borders, opacity, and
aspect-ratio utilities, generated from Tailwind v4's actual token values.

## Install

```kotlin
// build.gradle.kts
dependencies {
    implementation("io.github.ronjunevaldoz:tailwind-compose:<version>")
}
```

## Usage

```kotlin
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import io.github.ronjunevaldoz.tailwind.modifiers.*

Column(modifier = Modifier.bgSlate50().p4()) {
    Box(
        Modifier
            .size8()
            .bgBlue500()
            .rounded()
    )

    Text(
        "Hello, tailwind-compose",
        style = TextStyle().textLg().fontBold().textBlue600(),
    )
}
```

Utility names mirror Tailwind's own class names directly (`p4()` ~ `p-4`, `bgBlue500()` ~
`bg-blue-500`, `roundedLg()` ~ `rounded-lg`) rather than parameterized calls, so if you know
Tailwind you already know this API.

### ⚠️ Modifier order matters

`Modifier.background()`, `.clip()`, and `.alpha()` in Compose only affect modifiers that come
**after** them in the chain — not ones before. This library's `bg*()`, `rounded*()`, and
`opacity*()` functions inherit that behavior, since they're thin wrappers over those exact
Compose primitives. In practice:

```kotlin
// ✅ correct — bg fills the padded area, rounded clips the border
Modifier.bgSlate200().p4()
Modifier.roundedFull().border4(TwColors.blue500)
Modifier.opacity50().bgBlue500()

// ❌ wrong — background only fills the inner (unpadded) content, rounding has no visible effect
Modifier.p4().bgSlate200()
Modifier.border4(TwColors.blue500).roundedFull()
Modifier.bgBlue500().opacity50()
```

Rule of thumb: **effect modifiers (`bg`/`rounded`/`opacity`/`border`) go first in the chain**,
sizing/padding modifiers go after.

### Dark mode

Mirrors Tailwind's own `dark:` **variant** — not a semantic color scheme (Tailwind itself has
no `background`/`surface`/`primary` roles by default, so neither does this library):

```kotlin
Box(Modifier.bgWhite().twDark { bgSlate900() })
Text("Hello", style = TextStyle().textSlate900().twDark { textSlate50() })
```

`twDark { }` applies its block only when `isTwDarkTheme()` (a thin wrapper over Compose's
`isSystemInDarkTheme()`) is true, otherwise the chain passes through unchanged.

## Platforms

Android, iOS, Desktop (JVM), Web (JS + WasmJs).

## Modules

- [`tailwind-core`](tailwind-core) — design tokens: `TwSpacing`, `TwColors` (OKLCH-based,
  26 hues × 11 shades), `TwFontSize`/`TwLineHeight`/`TwFontWeight`/`TwTracking`, `TwRadius`
- [`tailwind-modifiers`](tailwind-modifiers) — `Modifier`/`TextStyle` extension functions
  built on those tokens
- [`tailwind-compose`](tailwind-compose) — public facade module; most consumers depend on
  this one only
- [`showcase`](showcase) — internal demo app rendering every utility category, used for
  visual verification via Roborazzi (not published)

See [docs/tailwind-coverage-matrix.md](docs/tailwind-coverage-matrix.md) for exactly which
Tailwind utility categories are covered, planned, or intentionally out of scope.

## Build

```bash
./gradlew help                 # verify the build resolves
./gradlew ktlintCheck detekt    # lint + static analysis
./gradlew jvmTest               # unit tests + Roborazzi screenshot verification
./gradlew :showcase:androidApp:assembleDebug   # showcase Android APK
./gradlew :showcase:desktopApp:run              # showcase Desktop app
```

Most token and Modifier files are code-generated from Tailwind's own scale/palette data —
see `scripts/codegen/` and each generated file's `// GENERATED CODE` header before editing.

## Publishing

Published to Maven Central under `io.github.ronjunevaldoz`. See
[.github/workflows/release.yml](.github/workflows/release.yml).

## License

Apache-2.0 — see [LICENSE](LICENSE).
