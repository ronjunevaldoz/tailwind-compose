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
| | scale, skew, translate (2D transforms), transform-origin, zoom | ⬜ | Not on roadmap; same `graphicsLayer` primitive as rotate/perspective, straightforward follow-up |
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
  presets) (post-MVP)
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
Modifier.twCard(shape = RoundedCornerShape(TwRadius.lg), color = TwColors.white, shadowElevation = TwShadow.sm)
```

It threads one `Shape` through `shadow() -> clip() -> background()` in the required order
instead of leaving callers to build and repeat it themselves. See `Card.kt`.

## Compose Styles API — not used

Jetpack Compose has a real, distinct **Styles API** (`Style`/`StyleScope`/`StyleState`,
`@ExperimentalStylesApi`, documented at `developer.android.com/develop/ui/compose/styles`) —
a CSS-style-like mechanism for defining state-based visual properties (padding, background,
border, ...) as a single reusable object, separate from `TextStyle`. This library does not
use it, for one concrete reason: **it requires Compose 1.12.0-alpha03 or later**, and this
project is pinned to Compose Multiplatform 1.11.1 (stable) — confirmed by searching the
actual 1.11.1 source jars for `ExperimentalStylesApi`, with zero matches anywhere in
`androidx.compose.foundation`/`ui`. It's also still experimental/alpha upstream, which alone
would be a reason to hold off adopting it into a library targeting a stable release. This is
unrelated to `tailwind-compose`'s own colloquial "Style API" terminology used elsewhere in
this project (the `TextStyle`-returning function family — `textSm()`, `fontBold()`,
`text(color: Color)`, etc.) — that's this library's own established, deliberately-chosen
naming, not a reference to the AndroidX feature above. Revisit once the project's pinned
Compose Multiplatform version reaches parity with 1.12.0-alpha03+ and the API stabilizes.

## Refreshing this matrix

Re-run against a newer Tailwind release with:
```bash
curl -sL "https://raw.githubusercontent.com/tailwindlabs/tailwindcss/v<version>/packages/tailwindcss/theme.css"
```
for token values, and check `https://tailwindcss.com/docs` navigation for any new utility categories.
