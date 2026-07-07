# tailwind-compose

Tailwind CSS-inspired, type-safe Kotlin extension functions for Compose Multiplatform
`Modifier` and `TextStyle` — spacing, sizing, color, typography, borders, opacity,
aspect-ratio, box-shadow, gradients, grid, filters, transitions, 3D transforms, flex
alignment, dark mode, and responsive breakpoints, generated from Tailwind v4's actual
token values. See [docs/tailwind-coverage-matrix.md](docs/tailwind-coverage-matrix.md)
for the exact utility-by-utility status.

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

Rule of thumb: **effect modifiers (`bg`/`rounded`/`opacity`/`border`/`shadow`) go first in the
chain**, sizing/padding modifiers go after. For a card (shadow + rounded corners + background
together), use [`twCard()`](#card-composition-helper) instead of composing the three yourself —
it's easy to get the order subtly wrong (e.g. a rounded card with a square-cornered shadow
underneath it).

### Card composition helper

Tailwind has no single `card` utility class — `bg-white rounded-lg shadow-sm` is three
independent CSS classes that apply to the same box regardless of order. `twCard()` exists
because the Compose equivalents (`shadow()`/`clip()`/`background()`) are order-sensitive
instead, threading one `Shape` through all three in the one order that keeps them visually
consistent:

```kotlin
Modifier.twCard(shape = RoundedCornerShape(TwRadius.lg), color = TwColors.white, shadowElevation = TwShadow.sm)
Modifier.twCard() // same defaults as above
```

### Dark mode

Mirrors Tailwind's own `dark:` **variant** — not a semantic color scheme (Tailwind itself has
no `background`/`surface`/`primary` roles by default, so neither does this library):

```kotlin
Box(Modifier.bgWhite().twDark { bgSlate900() })
Text("Hello", style = TextStyle().textSlate900().twDark { textSlate50() })
```

`twDark { }` applies its block only when `isTwDarkTheme()` (a thin wrapper over Compose's
`isSystemInDarkTheme()`) is true, otherwise the chain passes through unchanged. Override
`LocalTwDarkTheme` to force a theme in `@Preview`s or tests — there's otherwise no
cross-platform way to force dark mode in a test harness:

```kotlin
CompositionLocalProvider(LocalTwDarkTheme provides true) { /* isTwDarkTheme() is true here */ }
```

### Responsive design

Tailwind's `sm:`/`md:`/`lg:`/`xl:`/`2xl:` breakpoints are cumulative `min-width` matches, not
a binary switch like `dark:`, so this is a value-resolver rather than a chainable variant —
it picks one value up front, mobile-first, largest-matching-breakpoint-wins:

```kotlin
Modifier.padding(twResponsive(base = TwSpacing.scale4, md = TwSpacing.scale6, lg = TwSpacing.scale8))
val columns = twResponsive(base = 2, sm = 3, md = 4, lg = 6, xl = 8)
```

Backed by the actual window/viewport width (`currentTwWindowWidth()`), matching Tailwind's
exact 640/768/1024/1280/1536dp breakpoint scale (`TwBreakpoint`).

## Platforms

Android, iOS, Desktop (JVM), Web (JS + WasmJs).

## Modules

- [`tailwind-core`](tailwind-core) — design tokens: `TwSpacing`, `TwColors` (OKLCH-based,
  26 hues × 11 shades), `TwFontSize`/`TwLineHeight`/`TwFontWeight`/`TwTracking`, `TwRadius`,
  `TwShadow`, `TwTransition` (duration/easing), `TwBreakpoint`
- [`tailwind-layout`](tailwind-layout) — spacing, sizing, flex, grid, aspect-ratio,
  responsive breakpoints
- [`tailwind-color`](tailwind-color) — background/text color, gradients, dark mode
- [`tailwind-typography`](tailwind-typography) — font-size, line-height, font-weight,
  tracking, font-family
- [`tailwind-effects`](tailwind-effects) — border, box-shadow, opacity, filters,
  transitions, 3D transforms, the `twCard()` combinator
- [`tailwind-compose`](tailwind-compose) — public facade module; depends on `tailwind-core`
  and all four utility modules above, so most consumers only need this one dependency
- [`showcase`](showcase) — internal demo app rendering every utility category, used for
  visual verification via Roborazzi (not published)

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
