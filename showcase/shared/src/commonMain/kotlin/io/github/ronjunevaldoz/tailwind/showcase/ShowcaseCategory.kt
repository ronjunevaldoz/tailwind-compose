package io.github.ronjunevaldoz.tailwind.showcase

/** One entry per showcase sidebar item. `isExplainerOnly` categories have no
 * tailwind-compose utility (they're CSS/Tailwind concepts with no Compose
 * equivalent) and render a text explainer instead of a live demo. */
enum class ShowcaseCategory(
    val title: String,
    val isExplainerOnly: Boolean = false,
) {
    Spacing("Spacing"),
    Sizing("Sizing"),
    Color("Color"),
    Typography("Typography"),
    Borders("Borders"),
    Opacity("Opacity"),
    AspectRatio("Aspect Ratio"),
    Shadow("Shadow"),
    Flex("Flex Alignment"),
    Gradient("Gradients"),
    Grid("Grid"),
    Filters("Filters"),
    Transition("Transitions"),
    Transform3D("3D Transforms"),
    DarkMode("Dark Mode"),
    CssVariables("CSS Variables", isExplainerOnly = true),
    CascadeLayers("Cascade Layers", isExplainerOnly = true),
    ContainerQueries("Container Queries", isExplainerOnly = true),
    LogicalProperties("Logical Properties", isExplainerOnly = true),
    P3Colors("P3 Colors", isExplainerOnly = true),
}
