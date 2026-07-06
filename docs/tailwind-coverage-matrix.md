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
| | font-family, text-decoration, text-transform, list-style, content, hyphens | ⬜ | Not on roadmap yet |
| **Backgrounds** | background-color, text-color | ✅ | Sprint 3 — `bg*()` (Modifier) / `text*()` (TextStyle) in `Color.kt`, generated over all 289 palette colors |
| | background-image/gradient, position, repeat, size, clip, origin | ⬜ | Not on roadmap; gradients would need a `Brush`-based follow-up |
| **Borders** | border-radius, border-width, border-color | ✅ | Sprint 3 — `rounded*()` (clip-based) and `border*(color)` in `Border.kt` |
| | outline-width/color/style/offset | ⬜ | Not on roadmap; Compose's focus-ring story differs enough from CSS outline to need its own design pass |
| **Effects** | opacity | ✅ | Sprint 3 — `opacity0()`…`opacity100()` (5% steps) in `Opacity.kt` |
| | box-shadow | ✅ | Post-MVP — `shadowSm()`…`shadowXl2()` in `Shadow.kt`, approximated as Compose elevation (single Dp, not CSS's offset/blur/spread/color); `shadow-inner` has no Compose primitive and is not included |
| | text-shadow, mix/background-blend-mode, mask-* | ⬜ | Not on roadmap; mask-* has a Compose equivalent (`Modifier.graphicsLayer` + `BlendMode`) but is a bigger lift |
| **Flexbox & Grid** | gap | ✅ | Covered as part of Sprint 2's `Spacing.kt` (`gap`/`gapX`/`gapY`) |
| | justify-content, align-items | ✅ | Post-MVP — `justify*()`/`items*()` (Row) and `justify*Vertical()`/`items*Horizontal()` (Column) in `Flex.kt`; `items-baseline`/`items-stretch` have no direct `Alignment` equivalent and are not included |
| | flex-direction, flex-wrap, order | ⬜ | Not on roadmap; these map to which composable you choose (Row vs Column) and child ordering, not a Modifier utility |
| | grid-template-columns/rows, grid-column/row, grid-auto-* | ⬜ | Not on roadmap; Compose's `LazyVerticalGrid`/custom `Layout` don't map 1:1 to CSS grid, needs its own design |
| **Layout** | position, top/right/bottom/left, z-index | ⬜ | Not on roadmap; would map to `Modifier.zIndex()` + a custom offset-from-edge helper |
| | display, overflow, overscroll-behavior, visibility | ⬜ | Not on roadmap; many (display) are N/A in Compose's layout model |
| | aspect-ratio | ✅ | Sprint 3 — `aspectSquare()`/`aspectVideo()` in `Layout.kt` (`aspectAuto()` is a documented no-op) |
| | columns, break-*, box-sizing, float, clear, isolation, object-fit/position | ⬜ | Not on roadmap; several are CSS-print/float concepts with no Compose equivalent |
| **Filters** | blur, brightness, contrast, grayscale, hue-rotate, invert, saturate, sepia, drop-shadow, backdrop-* | ⬜ | Not on roadmap; Compose has `RenderEffect`/`graphicsLayer` primitives that could back these, non-trivial follow-up |
| **Transitions & Animation** | transition-*, animation | ⬜ | Not on roadmap; Compose's animation APIs (`animateXAsState`, `AnimatedVisibility`) are a different enough model that a "Tailwind-style" wrapper needs its own design |
| **Transforms** | rotate, scale, skew, translate, transform-origin, zoom | ⬜ | Not on roadmap; maps to `Modifier.graphicsLayer`, good candidate for a future sprint |
| **Interactivity** | cursor, scroll-behavior/margin/padding, scroll-snap-*, touch-action, user-select, resize, will-change, appearance | ⬜ | Not on roadmap; several are desktop/pointer-only concepts (cursor) that don't apply to touch-first mobile UIs |
| **Tables** | border-collapse, border-spacing, table-layout, caption-side | ⬜ | Not planned — Compose has no HTML-table layout primitive; would map to a custom grid composable, out of scope for a Modifier library |
| **SVG** | fill, stroke, stroke-width | ⬜ | Not planned — Compose styles vector art via `Painter`/`ImageVector` properties, not `Modifier`; different API shape entirely |
| **Accessibility** | forced-color-adjust | ⬜ | Not planned — Compose's accessibility story is `Modifier.semantics`/`contentDescription`, unrelated to this CSS media-query concept |

## Summary

- **Fully covered**: Spacing, Sizing (Sprint 2); Typography, Color/Backgrounds, Borders, Opacity,
  aspect-ratio (Sprint 3); box-shadow, flex alignment (post-MVP)
- **Remaining post-MVP item**: dark-mode/semantic theming — needs a design decision before
  implementation (see project discussion), not yet started
- **Larger, deliberately out of scope for now**: Grid, Filters, Transforms, Transitions/Animation — each needs its own design pass since Compose's model differs enough from CSS that a naive 1:1 port would be misleading
- **Not applicable to Compose**: Tables, SVG (different API surface entirely), most of Interactivity (pointer/cursor-first concepts), several Layout utilities (float, columns, box-sizing)

## Refreshing this matrix

Re-run against a newer Tailwind release with:
```bash
curl -sL "https://raw.githubusercontent.com/tailwindlabs/tailwindcss/v<version>/packages/tailwindcss/theme.css"
```
for token values, and check `https://tailwindcss.com/docs` navigation for any new utility categories.
