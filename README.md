# tailwind-compose

Tailwind CSS-inspired, type-safe Kotlin extension functions for Compose Multiplatform
`Modifier` — spacing, sizing, color, typography, borders, and (soon) layout utilities.

> Status: Foundation sprint complete. Utility implementations land in upcoming sprints.

## Platforms

Android, iOS, Desktop (JVM), Web (JS + WasmJs).

## Modules

- [`tailwind-core`](tailwind-core) — design tokens (spacing, color, typography, radius scales)
- [`tailwind-modifiers`](tailwind-modifiers) — `Modifier` extension functions built on those tokens
- [`tailwind-compose`](tailwind-compose) — public facade module; most consumers depend on this one only
- [`showcase`](showcase) — internal demo app used for visual verification (not published)

## Build

```bash
./gradlew help                 # verify the build resolves
./gradlew ktlintCheck detekt    # lint + static analysis
./gradlew jvmTest               # unit tests + Roborazzi screenshot verification
./gradlew :showcase:androidApp:assembleDebug   # showcase Android APK
./gradlew :showcase:desktopApp:run              # showcase Desktop app
```

## Publishing

Published to Maven Central under `io.github.ronjunevaldoz`. See
[.github/workflows/release.yml](.github/workflows/release.yml).

## License

Apache-2.0 — see [LICENSE](LICENSE).
