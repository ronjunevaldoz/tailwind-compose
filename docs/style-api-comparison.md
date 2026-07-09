# Modifier API vs Style API — side-by-side

`tailwind-compose` (published, stable) uses plain `Modifier`/`TextStyle` extensions.
`tailwind-style-experimental` (unpublished, isolated — see
[README's "Style API (experimental)" section](../README.md#style-api-experimental-not-yet-part-of-the-public-library)
for why) ports the same utilities onto Compose Foundation's real `Style`/`StyleScope`
API. This doc is a working reference for what the same result looks like written both
ways — not a recommendation to use one over the other today; the Style API column isn't
usable outside `style-experimental`'s own isolated, Android-less build yet.

## Quick reference

| Category | `tailwind-compose` (stable) | `tailwind-style-experimental` (isolated) |
|---|---|---|
| Ring | `Modifier.ring()` / `ring2()` / `ring4()` / `ring8()` | `Style.ringStyle()` / `ringStyle2()` / `ringStyle4()` / `ringStyle8()` |
| Shadow | `Modifier.shadowXs()` / `shadowMd()` / `shadowLg()` / `shadowXl()` | `Style.shadowStyleXs()` / `shadowStyleMd()` / `shadowStyleLg()` / `shadowStyleXl()` |
| Border | `Modifier.border2/4/8(color, shape)` + separate `Modifier.clip(shape)` | `Style.borderStyle2/4/8(color)` + `Style.roundedStyle(radius)` — one shape drives border, background, *and* clip |
| Background color | `Modifier.bg(color)` | `Style.bgStyle(color)` |
| Text color | `TextStyle.text(color)` / `textAmber400()` etc. | `Style.textColorStyle(color)` |
| Opacity | `Modifier.opacity25/50/75/100()` | `Style.opacityStyle0/50/100()` |
| Filters | `Modifier.grayscale()` etc. (manual `drawWithContent`/`saveLayer`) | `Style.grayscaleStyle()` etc. (`ColorFilterScope.colorFilter` directly) |
| Transform | `Modifier.rotateX45()` / `rotateY45()` / `rotateZ45()` | `Style.rotateXStyle()` / `rotateYStyle()` / `rotateZStyle()`, `scaleStyle()`, `translateXStyle()`/`translateYStyle()` |
| Transition | `Modifier.transitionAllDuration300()` (`animateContentSize` — size only) | `Style.transitionStyle { ... }` (`AnimateStyleScope.animate` — any wrapped property) |
| Spacing | `Modifier.p1/2/4/8()` (padding) | `Style.paddingStyle1/2/4/8()`, `Style.marginStyle1/2/4/8()` |
| Sizing | `Modifier.size4/6/8/12()` | `Style.widthStyle()`/`heightStyle()`, `minWidthStyle()`/`maxWidthStyle()` |
| Typography | `TextStyle.textSm()`/`textXl()`/`fontBold()` etc. | `Style.fontSizeStyle()`, `fontWeightStyle()`, `letterSpacingStyle()`, `fontFamilyStyle()`, `textDecorationStyle()` |

Full facet-by-facet mapping (which `StyleScope` each one is built on, and what's
genuinely out of scope) is in
[`tailwind-coverage-matrix.md`](tailwind-coverage-matrix.md#tailwind-style-experimental-coverage).

## Side-by-side code

### Ring

```kotlin
// Modifier API — tailwind-compose
Box(Modifier.size(40.dp).ring2(TwColors.blue500).bgWhite())
```

```kotlin
// Style API — tailwind-style-experimental
Box(Modifier.size(40.dp).styleable(style = Style.bgStyle(Color.White).ringStyle2(TwColors.blue500)))
```

### Shadow

```kotlin
// Modifier API
Box(Modifier.size(40.dp).shadowMd().bgWhite())
```

```kotlin
// Style API
Box(Modifier.size(40.dp).styleable(style = Style.bgStyle(Color.White).shadowStyleMd()))
```

### Border + rounded corners

```kotlin
// Modifier API — shape has to be built once and threaded through both clip() and border()
val shape = RoundedCornerShape(TwRadius.lg)
Box(Modifier.clip(shape).size(40.dp).border4(TwColors.blue500, shape))
```

```kotlin
// Style API — roundedStyle()'s shape auto-clips background/border in the same Style, no separate clip() call
Box(Modifier.size(40.dp).styleable(style = Style.borderStyle4(TwColors.blue500).roundedStyleLg()))
```

### Color (background + text)

```kotlin
// Modifier API
Box(Modifier.size(24.dp).bg(TwColors.blue500))
Text("text-amber-400", style = MaterialTheme.typography.bodyLarge.text(TwColors.amber400))
```

```kotlin
// Style API
Box(Modifier.size(24.dp).styleable(style = Style.bgStyle(TwColors.blue500)))
Box(Modifier.styleable(style = Style.textColorStyle(TwColors.amber400))) {
    BasicText("text-amber-400")
}
```

### Opacity

```kotlin
// Modifier API
listOf(Modifier.opacity25(), Modifier.opacity50(), Modifier.opacity75(), Modifier.opacity100())
    .forEach { opacity -> Box(opacity.size(32.dp).bgBlue500()) }
```

```kotlin
// Style API
listOf(Style.opacityStyle0(), Style.opacityStyle50(), Style.opacityStyle100())
    .forEach { opacity -> Box(Modifier.size(32.dp).styleable(style = Style.bgStyle(TwColors.blue500).then(opacity))) }
```

### Filters

```kotlin
// Modifier API — hand-rolled drawWithContent + saveLayer under the hood (see tailwind-effects/Filters.kt)
Image(painterResource(Res.drawable.filter), null, Modifier.size(220.dp).grayscale())
```

```kotlin
// Style API — ColorFilterScope.colorFilter directly, no manual layer-paint needed
Box(Modifier.size(220.dp).styleable(style = Style.bgStyle(TwColors.orange500).grayscaleStyle()))
```

### Transform

```kotlin
// Modifier API
Row(horizontalArrangement = gap4()) {
    Box(Modifier.size(50.dp).perspectiveNormal().rotateX45().bgBlue500())
    Box(Modifier.size(50.dp).perspectiveNormal().rotateY45().bgBlue500())
    Box(Modifier.size(50.dp).perspectiveNormal().rotateZ45().bgBlue500())
}
```

```kotlin
// Style API
Box(Modifier.size(32.dp).styleable(style = Style.bgStyle(TwColors.blue500).rotateZStyle(45f)))
```

### Transition

```kotlin
// Modifier API — animateContentSize only animates size changes
var expanded by remember { mutableStateOf(false) }
Box(
    Modifier
        .transitionAllDuration300()
        .size(if (expanded) 80.dp else 40.dp)
        .bgBlue500(),
)
```

```kotlin
// Style API — AnimateStyleScope.animate wraps any StyleScope property write, not just size
var expanded by remember { mutableStateOf(false) }
Box(
    Modifier.clickable { expanded = !expanded }.styleable(
        style = Style.transitionStyle(durationMs = TwDuration.D300) {
            background(if (expanded) TwColors.blue500 else TwColors.slate300)
            contentPadding(if (expanded) 32.dp else 8.dp)
        },
    ),
)
```

### Spacing (padding + margin)

```kotlin
// Modifier API
listOf(Modifier.p1(), Modifier.p2(), Modifier.p4(), Modifier.p8()).forEach { padding ->
    Box(Modifier.bgSlate200().then(padding)) {
        Box(Modifier.size(16.dp).bgBlue500())
    }
}
```

```kotlin
// Style API
listOf(Style.paddingStyle1(), Style.paddingStyle2(), Style.paddingStyle4(), Style.paddingStyle8()).forEach { padding ->
    Box(Modifier.styleable(style = Style.bgStyle(TwColors.slate800).then(padding))) {
        Box(Modifier.size(16.dp).styleable(style = Style.bgStyle(TwColors.amber400)))
    }
}
```

### Sizing

```kotlin
// Modifier API
listOf(Modifier.size4(), Modifier.size6(), Modifier.size8(), Modifier.size12()).forEach { size ->
    Box(size.bgBlue500())
}
```

```kotlin
// Style API
Box(Modifier.styleable(style = Style.bgStyle(TwColors.blue500).widthStyle(TwSpacing.scale8).heightStyle(TwSpacing.scale4)))
```

### Typography

```kotlin
// Modifier API — TextStyle-returning function family
Text("The quick brown fox", style = MaterialTheme.typography.bodyLarge.textXl().fontBold())
```

```kotlin
// Style API — applies to a styleable region directly, not a Text composable's own style: param
Box(Modifier.styleable(style = Style.fontSizeStyleXl().fontWeightStyleBold())) {
    BasicText("The quick brown fox")
}
```

---

Every Style API snippet above is copied verbatim (or trivially trimmed of demo-only
scaffolding) from
[`tailwind/style-experimental/.../style/demo/App.kt`](../tailwind/style-experimental/src/commonMain/kotlin/io/github/ronjunevaldoz/tailwind/style/demo/App.kt),
which is live-rendered and browser-verified — run it yourself with
`./gradlew :style-experimental:wasmJsBrowserDevelopmentRun`. Every Modifier API snippet
is copied from the corresponding `showcase/shared/.../sections/*Showcase.kt` file, which
backs the published `tailwind-compose` showcase app.
