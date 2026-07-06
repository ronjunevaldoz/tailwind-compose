package io.github.ronjunevaldoz.tailwind.showcase

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.ronjunevaldoz.tailwind.modifiers.bgSlate100
import io.github.ronjunevaldoz.tailwind.modifiers.bgSlate200
import io.github.ronjunevaldoz.tailwind.modifiers.bgWhite
import io.github.ronjunevaldoz.tailwind.modifiers.p2
import io.github.ronjunevaldoz.tailwind.showcase.sections.AspectRatioShowcase
import io.github.ronjunevaldoz.tailwind.showcase.sections.BorderShowcase
import io.github.ronjunevaldoz.tailwind.showcase.sections.CascadeLayersExplainer
import io.github.ronjunevaldoz.tailwind.showcase.sections.ColorShowcase
import io.github.ronjunevaldoz.tailwind.showcase.sections.ContainerQueriesExplainer
import io.github.ronjunevaldoz.tailwind.showcase.sections.CssVariablesExplainer
import io.github.ronjunevaldoz.tailwind.showcase.sections.DarkModeShowcase
import io.github.ronjunevaldoz.tailwind.showcase.sections.FiltersShowcase
import io.github.ronjunevaldoz.tailwind.showcase.sections.FlexShowcase
import io.github.ronjunevaldoz.tailwind.showcase.sections.GradientShowcase
import io.github.ronjunevaldoz.tailwind.showcase.sections.GridShowcase
import io.github.ronjunevaldoz.tailwind.showcase.sections.LogicalPropertiesExplainer
import io.github.ronjunevaldoz.tailwind.showcase.sections.OpacityShowcase
import io.github.ronjunevaldoz.tailwind.showcase.sections.P3ColorsExplainer
import io.github.ronjunevaldoz.tailwind.showcase.sections.ShadowShowcase
import io.github.ronjunevaldoz.tailwind.showcase.sections.SizingShowcase
import io.github.ronjunevaldoz.tailwind.showcase.sections.SpacingShowcase
import io.github.ronjunevaldoz.tailwind.showcase.sections.Transform3DShowcase
import io.github.ronjunevaldoz.tailwind.showcase.sections.TransitionShowcase
import io.github.ronjunevaldoz.tailwind.showcase.sections.TypographyShowcase

/** Sidebar + detail-view scaffold — one category per screen, built entirely from the library's own Modifiers. */
@Suppress("ktlint:standard:function-naming")
@Composable
fun App() {
    MaterialTheme {
        var selected by remember { mutableStateOf(ShowcaseCategory.Spacing) }
        Row(modifier = Modifier.bgWhite().fillMaxSize()) {
            Sidebar(selected = selected, onSelect = { selected = it })
            Column(modifier = Modifier.fillMaxHeight().verticalScroll(rememberScrollState())) {
                CategoryDetail(selected)
            }
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
private fun Sidebar(
    selected: ShowcaseCategory,
    onSelect: (ShowcaseCategory) -> Unit,
) {
    LazyColumn(modifier = Modifier.width(180.dp).fillMaxHeight().bgSlate100()) {
        items(ShowcaseCategory.entries.toList()) { category ->
            val isSelected = category == selected
            Text(
                category.title,
                modifier =
                    Modifier
                        .let { if (isSelected) it.bgSlate200() else it }
                        .clickable { onSelect(category) }
                        .fillMaxHeight()
                        .p2(),
            )
        }
    }
}

@Suppress("ktlint:standard:function-naming", "CyclomaticComplexMethod")
@Composable
private fun CategoryDetail(category: ShowcaseCategory) {
    when (category) {
        ShowcaseCategory.Spacing -> SpacingShowcase()
        ShowcaseCategory.Sizing -> SizingShowcase()
        ShowcaseCategory.Color -> ColorShowcase()
        ShowcaseCategory.Typography -> TypographyShowcase()
        ShowcaseCategory.Borders -> BorderShowcase()
        ShowcaseCategory.Opacity -> OpacityShowcase()
        ShowcaseCategory.AspectRatio -> AspectRatioShowcase()
        ShowcaseCategory.Shadow -> ShadowShowcase()
        ShowcaseCategory.Flex -> FlexShowcase()
        ShowcaseCategory.Gradient -> GradientShowcase()
        ShowcaseCategory.Grid -> GridShowcase()
        ShowcaseCategory.Filters -> FiltersShowcase()
        ShowcaseCategory.Transition -> TransitionShowcase()
        ShowcaseCategory.Transform3D -> Transform3DShowcase()
        ShowcaseCategory.DarkMode -> DarkModeShowcase()
        ShowcaseCategory.CssVariables -> CssVariablesExplainer()
        ShowcaseCategory.CascadeLayers -> CascadeLayersExplainer()
        ShowcaseCategory.ContainerQueries -> ContainerQueriesExplainer()
        ShowcaseCategory.LogicalProperties -> LogicalPropertiesExplainer()
        ShowcaseCategory.P3Colors -> P3ColorsExplainer()
    }
}

@Suppress("ktlint:standard:function-naming")
@Preview
@Composable
private fun AppPreview() {
    App()
}
