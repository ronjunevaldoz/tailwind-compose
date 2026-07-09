# Tailwind CSS → tailwind-compose coverage matrix

Tracking against **Tailwind CSS v4.3.2** (latest stable, confirmed via
[github.com/tailwindlabs/tailwindcss](https://github.com/tailwindlabs/tailwindcss) releases
and [tailwindcss.com/docs](https://tailwindcss.com/docs) navigation, fetched directly rather
than from memory — see `scripts/codegen/tailwind_scale.py` header for the exact source URL).

Status legend:
- ✅ **Implemented** — tokens + Modifier/helper functions exist and are tested
- 🟡 **Tokens only** — design tokens exist in `tailwind-core`, no consuming Modifier yet
- ⏳ **Planned** — on the sprint/roadmap, not yet built
- ⬜ **Not planned** — no roadmap item; see notes for why (often a CSS-only or N/A-in-Compose concept)

| Tailwind category | Utility groups | Status | Notes |
|---|---|---|---|
| **Spacing** | padding, margin | ✅ | Sprint 2 — `p*`/`m*`/`gap*` in `Spacing.kt`, full 35-step scale incl. negative margin (offset-based) |
| **Sizing** | width, min/max-width, height, min/max-height | ✅ | Sprint 2 — `w*`/`h*`/`minW*`/`maxW*`/`minH*`/`maxH*`/`size*`/`wFull`/`hFull` in `Sizing.kt` |
| | inline-size/block-size (logical) | ⬜ | Compose has no separate logical-size axis from width/height; N/A |
| **Typography** | font-size, line-height, font-weight, letter-spacing, text-align | ✅ | Sprint 3 — `TextStyle` extensions in `Typography.kt` (`textLg()`, `fontBold()`, `trackingWide()`, `textAlignCenter()`, ...) |
| | font-family (`font-sans`/`font-serif`/`font-mono`) | ✅ | Post-MVP — `TwFontFamily` (tailwind-core) + `fontSans()`/`fontSerif()`/`fontMono()` in `Typography.kt`. Maps to Compose's generic `FontFamily.SansSerif`/`Serif`/`Monospace` categories rather than Tailwind's literal web font-stack strings — no 1:1 equivalent, each platform substitutes its own default font per category |
| | text-decoration, text-transform, list-style, content, hyphens | ⬜ | Not on roadmap yet |
| **Backgrounds** | background-color, text-color | ✅ | Sprint 3 — `bg*()` (Modifier) / `text*()` (TextStyle) in `Color.kt`, generated over all 289 palette colors |
| | arbitrary values (`bg-[#1da1f2]`, `text-[#1da1f2]`) | ✅ | Post-MVP — `bg(color: Color)` / `text(color: Color)` in `Color.kt`, for colors picked at runtime (e.g. iterating a `List<Color>`) rather than known at compile time, where a named `bg*()` function can't be called |
| | background-image/gradient (4 cardinal directions) | ✅ | Post-MVP — `bgGradientToR/L/T/B()` in `Gradient.kt`, returns a `Brush` for `Modifier.background(brush)`. Diagonal corners (`to-tr`/`to-br`/`to-bl`/`to-tl`) and `from-*`/`via-*`/`to-*` stop utilities not included — pass the color list directly instead |
| | position, repeat, size, clip, origin | ⬜ | Not on roadmap |
| **Borders** | border-radius, border-width, border-color | ✅ | Sprint 3 — `rounded*()` (clip-based) and `border*(color, shape = RectangleShape)` in `Border.kt`. The `shape` param (post-MVP addition) lets a border round independent of a `clip()` call — pass the same `Shape` to both for a border that's guaranteed to match, rather than relying on `clip()`'s layer-wide effect on a later `border()` in the same chain |
| | outline-width/color/style/offset | ⬜ | Not on roadmap; Compose's focus-ring story differs enough from CSS outline to need its own design pass |
| **Effects** | opacity | ✅ | Sprint 3 — `opacity0()`…`opacity100()` (5% steps) in `Opacity.kt` |
| | box-shadow | ✅ | Post-MVP — `shadowSm()`…`shadowXl2()` in `Shadow.kt`, approximated as Compose elevation (single Dp, not CSS's offset/blur/spread/color); `shadow-inner` has no Compose primitive and is not included |
| | text-shadow, mix/background-blend-mode, mask-* | ⬜ | Not on roadmap; mask-* has a Compose equivalent (`Modifier.graphicsLayer` + `BlendMode`) but is a bigger lift |
| **Flexbox & Grid** | gap | ✅ | Covered as part of Sprint 2's `Spacing.kt` (`gap`/`gapX`/`gapY`) |
| | justify-content, align-items | ✅ | Post-MVP — `justify*()`/`items*()` (Row) and `justify*Vertical()`/`items*Horizontal()` (Column) in `Flex.kt`; `items-baseline`/`items-stretch` have no direct `Alignment` equivalent and are not included |
| | flex-direction, flex-wrap, order | ⬜ | Not on roadmap; these map to which composable you choose (Row vs Column) and child ordering, not a Modifier utility |
| | grid-cols-* (fixed column count) | ✅ | Post-MVP — `gridCols1()`…`gridCols12()` in `Grid.kt`, returns `GridCells` for `LazyVerticalGrid(columns = ...)` |
| | grid-template-columns/rows track lists, grid-column/row placement/span, grid-auto-* | ⬜ | Not on roadmap; would need a custom `Layout`, CSS Grid's arbitrary track-sizing doesn't map to `LazyVerticalGrid`'s item-grid model |
| **Layout** | position, top/right/bottom/left, z-index | ⬜ | Not on roadmap; would map to `Modifier.zIndex()` + a custom offset-from-edge helper |
| | display, overflow, overscroll-behavior, visibility | ⬜ | Not on roadmap; many (display) are N/A in Compose's layout model |
| | aspect-ratio | ✅ | Sprint 3 — `aspectSquare()`/`aspectVideo()` in `Layout.kt` (`aspectAuto()` is a documented no-op) |
| | columns, break-*, box-sizing, float, clear, isolation, object-fit/position | ⬜ | Not on roadmap; several are CSS-print/float concepts with no Compose equivalent |
| **Filters** | blur, grayscale, invert, sepia | ✅ | Post-MVP — `blurNone()`…`blurXl3()` (native `Modifier.blur`); `grayscale()`/`invert()`/`sepia()` via a `ColorMatrix` + layer-paint technique (Compose has no built-in equivalent) in `Filters.kt` |
| | brightness-*, contrast-*, saturate-* | ✅ | `brightness0()`…`brightness200()`, `contrast0()`…`contrast200()`, `saturate0()`…`saturate200()` in `Filters.kt` — same `ColorMatrix` technique, canonical scale stops verified against Tailwind v4's `utilities.ts` `suggest(...)` lists (bare percentages, not a fixed CSS keyword scale) |
| | hue-rotate-*, drop-shadow-*, backdrop-* | ⬜ | Same `ColorMatrix` technique as the others but needs a rotation matrix (hue-rotate) or isn't a per-content filter at all (drop-shadow, backdrop-*) — deferred until something actually needs them |
| **Transitions & Animation** | duration-*, ease-* (tokens), transition-all (size only) | ✅ | Post-MVP — `TwDuration`/`TwEasing` tokens (`tailwind-core`) matching CSS's exact cubic-bezier curves; `transitionAllDuration75/150/300/500()` in `Transition.kt` wraps `Modifier.animateContentSize` (the closest single-Modifier match to CSS's "animate any changed property" — Compose has no general equivalent, other properties need `animateXAsState` at the call site using the same tokens) |
| **Transforms** | rotate-x/y/z (45/90/180°), perspective (near/normal/distant) | ✅ | Post-MVP — `Transform3D.kt`, via `Modifier.graphicsLayer`'s `rotationX/Y/Z`/`cameraDistance`. Representative subset only (Tailwind's full rotate scale is ~9 steps × 3 axes × 2 signs); `perspective-*` is a named-preset approximation, not a unit conversion (CSS px vs. Compose's inversely-related camera-distance units) |
| | scale (uniform), translate-x/y | ✅ | `scale0()`…`scale200()` (complete 11-step canonical scale, per `utilities.ts` `suggest('scale', ...)`) and `translateX1/2/4/8()`/`translateXNeg1/2/4/8()`/`translateY1/2/4/8()`/`translateYNeg1/2/4/8()` (representative subset of the [TwSpacing] scale, matching this file's existing rotate-scope precedent) in `Transform3D.kt`, via `Modifier.graphicsLayer`'s `scaleX/Y`/`translationX/Y` |
| | skew, scale-x/y (per-axis), transform-origin, zoom, translate fractions (`1/2`, `full`) | ⬜ | Not on roadmap; `skew` has no `graphicsLayer` property (verified against Compose Multiplatform 1.11.1 `GraphicsLayerScope` source — only scale/translation/rotation/cameraDistance/transformOrigin exist), would need a custom matrix/canvas-transform technique instead of a passthrough; translate fractions are relative to the element's own measured size, a different mechanism than a fixed Dp offset |
| **Interactivity** | cursor, scroll-behavior/margin/padding, scroll-snap-*, touch-action, user-select, resize, will-change, appearance | ⬜ | Not on roadmap; several are desktop/pointer-only concepts (cursor) that don't apply to touch-first mobile UIs |
| **Tables** | border-collapse, border-spacing, table-layout, caption-side | ⬜ | Not planned — Compose has no HTML-table layout primitive; would map to a custom grid composable, out of scope for a Modifier library |
| **SVG** | fill, stroke, stroke-width | ⬜ | Not planned — Compose styles vector art via `Painter`/`ImageVector` properties, not `Modifier`; different API shape entirely |
| **Accessibility** | forced-color-adjust | ⬜ | Not planned — Compose's accessibility story is `Modifier.semantics`/`contentDescription`, unrelated to this CSS media-query concept |
| **Responsive design** | `sm:`/`md:`/`lg:`/`xl:`/`2xl:` breakpoints | ✅ | Post-MVP — `TwBreakpoint` (tailwind-core, exact 640/768/1024/1280/1536dp match for `--breakpoint-*`) + `twResponsive()` (tailwind-layout), a value-resolver rather than a chainable variant — see "Responsive design" section below for why |

## Summary

- **Fully covered**: Spacing, Sizing (Sprint 2); Typography, Color/Backgrounds, Borders, Opacity,
  aspect-ratio (Sprint 3); box-shadow, flex alignment, dark mode, gradients (4 cardinal
  directions), grid (fixed column count), filters (blur/grayscale/invert/sepia),
  transitions (size animation + duration/easing tokens), 3D transforms (rotate + perspective
  presets), 2D transforms (scale + translate-x/y) (post-MVP)
- **Deliberately partial**: each of the "bigger lift" post-MVP items above covers the
  common/representative case, not Tailwind's full numeric scale or CSS's complete model —
  see each category's row for exactly what's deferred and why
- **Not applicable to Compose**: Tables, SVG (different API surface entirely), most of Interactivity (pointer/cursor-first concepts), several Layout utilities (float, columns, box-sizing), CSS cascade layers (no cascade concept in Compose), container queries (Compose's answer is `BoxWithConstraints`, a genuinely different mechanism than the viewport-based `sm:`/`md:`/`lg:` breakpoints — see "Responsive design" below), CSS custom properties (this library's tokens — `TwColors`, `TwSpacing`, etc. — already are the equivalent single source of truth)
- **P3/wide-gamut color**: not a data gap — `TwColors.kt` already sources the exact OKLCH triples Tailwind v4 ships (verified against `tailwindcss.com`'s own docs, which confirm there's no separate P3 palette; browsers just render OKLCH in the widest gamut the display supports). The blocker is Compose Multiplatform itself: `Color.toArgb()` — used by every `Paint.color` setter on every target (`AndroidPaint.android.kt`, `SkiaBackedPaint.skiko.kt`, verified in Compose Multiplatform 1.11.1 sources) — calls `convert(ColorSpaces.Srgb).value shr 32`, gamut-mapping and clipping to 8-bit sRGB before the pixel is drawn. Tagging a `Color` with `ColorSpaces.DisplayP3` compiles but is discarded before compositing, on Android included, not just non-Android targets. No Modifier-level workaround exists; would require a wide-gamut-aware paint path upstream in Compose. See the "P3 Colors" showcase screen for the full writeup.

## Dark mode

Implemented as Tailwind's own `dark:` **variant** model, not a Material/shadcn-style semantic
color scheme — Tailwind itself has no semantic tokens by default, so this keeps the library's
"literal Tailwind naming" philosophy intact rather than introducing a new vocabulary
(`background`/`surface`/`primary` roles) Tailwind doesn't have:

```kotlin
Modifier.bgWhite().twDark { bgSlate900() }
TextStyle().textSlate900().twDark { textSlate50() }
```

`isTwDarkTheme()` wraps Compose's `isSystemInDarkTheme()`, checking `LocalTwDarkTheme` first
so `@Preview`s and tests can force a theme (`CompositionLocalProvider(LocalTwDarkTheme provides
true) { ... }`) — there is otherwise no cross-platform way to force dark mode in a test harness
(the JVM/Desktop harness always reports light mode). Defaults to `null`, deferring to the
system setting. See `DarkMode.kt`.

## Responsive design

Not modeled as a chainable variant like `dark:`, deliberately — `dark:` is a binary switch (light
XOR dark), but `sm:`/`md:`/`lg:`/`xl:`/`2xl:` are cumulative `min-width` thresholds: at a
1024dp-wide window, `sm`, `md`, *and* `lg` are all simultaneously true. A `twSm { }.twMd { }.twLg { }`
chain modeled on `twDark { }` would apply every matching block (e.g. three stacked `.padding()`
calls) instead of the one Tailwind's cascade actually resolves to. `twResponsive()` instead
picks a single value up front, mobile-first, largest-matching-breakpoint-wins:

```kotlin
Modifier.padding(twResponsive(base = TwSpacing.scale4, md = TwSpacing.scale6, lg = TwSpacing.scale8))
val columns = twResponsive(base = 2, sm = 3, md = 4, lg = 6, xl = 8)
```

Backed by `LocalWindowInfo.current.containerDpSize` (`currentTwWindowWidth()` in `Responsive.kt`)
— the actual window/viewport size, not `BoxWithConstraints` (which measures the local container,
Tailwind's *different* `@container` concept) and not Material's `WindowSizeClass` (whose
600dp/840dp breakpoints don't match Tailwind's 640/768/1024/1280/1536 scale in `TwBreakpoint`).

## Card composition helper

Tailwind has no single `card` utility class — `bg-white rounded-lg shadow-sm` is three
independent classes, and CSS applies all three to the same box regardless of declaration
order. Compose's `shadow()`/`clip()`/`background()` equivalents are order-*sensitive*
instead: get it wrong and the shadow casts a rectangular halo under rounded content, or the
background ignores the clip entirely (this bit `ShowcaseSection.kt` twice — see its commit
history). `twCard()` exists purely to close that Compose-specific gap, not to model a
Tailwind class, hence the `tw` prefix it shares with `twDark`/`twResponsive`:

```kotlin
Modifier.twCard(shape = RoundedCornerShape(TwRadius.lg), color = TwColors.white, shadowElevation = TwShadow.xs)
```

It threads one `Shape` through `shadow() -> clip() -> background()` in the required order
instead of leaving callers to build and repeat it themselves. See `Card.kt`.

## Compose Styles API — adopted, isolated in `tailwind-style-experimental`

Jetpack Compose has a real, distinct **Styles API** (`Style`/`StyleScope`/`StyleState`,
`@ExperimentalFoundationStyleApi` — not `@ExperimentalStylesApi`, a name that appears in some
secondary docs but doesn't match the actual compiled annotation, confirmed by decompiling the
real 1.12.0-beta01 jar rather than trusting the summary), documented at
`developer.android.com/develop/ui/compose/styles` — a CSS-style-like mechanism for defining
state-based visual properties (padding, background, border, drop/inner shadow via a `Shadow`
value with a real `spread` parameter, ...) as a single reusable object, separate from
`TextStyle`. Originally not used here at all: it required Compose 1.12.0-alpha03+ while this
project was pinned to 1.11.1 (stable), and was confirmed absent from the actual 1.11.1 source
jars. As of `tailwind-style-experimental`, Compose Multiplatform 1.12.0-beta01 (the latest
available at the time) was confirmed to include `Style`/`StyleScope`/`StyleState`/`ShadowScope`
in `commonMain` (genuinely multiplatform, not Android-only) by downloading and inspecting the
real sources jar — so a `Style.ringStyle()` implementation was built on it (`RingStyle.kt`,
`ShadowScope.dropShadow(Shadow(radius = 0.dp, spread = width, color = color))`, more faithful
to CSS `box-shadow`'s spread radius than `tailwind-effects`' hand-rolled `ring()`).
**Every custom style in this module is an extension on `Style` itself** (`Style` carries a
`companion object : Style`, the empty/no-op default, so `Style.ringStyle(color)` reads as
"start from the Style entry point") — **not** a `Modifier` extension. A `Modifier` extension
was tried and deliberately removed: it can't be `then`-composed with another `Style` the way
two `Style` values can (`someStyle.ringStyle(color).then(otherStyle)`), so it would quietly
steer callers away from the one thing a `Style` does that a plain `Modifier` chain doesn't.
Apply the result with the Style API's own `Modifier.styleable(style = someStyle.ringStyle(...))`
directly — no tailwind-compose-provided shortcut. The API is
still `@ExperimentalFoundationStyleApi` ("subject to change") and 1.12.0-beta01 is pre-release,
so this is deliberately **isolated in its own composite build**
(`tailwind/style-experimental/`, included via `includeBuild` in the root `settings.gradle.kts`,
not a regular subproject) — a plain `include()` subproject can't run a different
`org.jetbrains.compose` plugin version than the rest of the build (the plugin resolves to one
version build-wide once anything applies it via the root's `apply false`), and this keeps every
stable, published module on 1.11.1. It resolves `tailwind-core` from `mavenLocal()` rather than
via automatic composite-build dependency substitution, which does not fire in this direction
(included build consuming the includer's own regular subproject, not the other way around) —
confirmed empirically, not assumed; run `./gradlew :tailwind-core:publishToMavenLocal` before
building this module to pick up local `tailwind-core` changes. No Android target: Compose
1.12.0-beta01's Android artifacts require AGP 9.1.0+/compileSdk 37+, a second, unrelated version
bump out of scope here — JVM/desktop already fully exercises and verifies the real Style API via
`RingStyleTest.kt`'s passing pixel assertions. This is unrelated to `tailwind-compose`'s own
colloquial "Style API" terminology used elsewhere in this project (the `TextStyle`-returning
function family — `textSm()`, `fontBold()`, `text(color: Color)`, etc.) — that's this library's
own established, deliberately-chosen naming, not a reference to the AndroidX feature above.
Revisit folding this into the stable module graph once Compose Multiplatform reaches a stable
release with this API and this project's own pinned version catches up to it.

## Refreshing this matrix

Re-run against a newer Tailwind release with:
```bash
curl -sL "https://raw.githubusercontent.com/tailwindlabs/tailwindcss/v<version>/packages/tailwindcss/theme.css"
```
for token values, and check `https://tailwindcss.com/docs` navigation for any new utility categories.
