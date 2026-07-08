package io.github.ronjunevaldoz.tailwind.showcase

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.ronjunevaldoz.tailwind.modifiers.LocalTwDarkTheme
import io.github.ronjunevaldoz.tailwind.modifiers.bgSlate50
import io.github.ronjunevaldoz.tailwind.modifiers.bgSlate900
import io.github.ronjunevaldoz.tailwind.modifiers.bgSlate950
import io.github.ronjunevaldoz.tailwind.modifiers.bgWhite
import io.github.ronjunevaldoz.tailwind.modifiers.fontBold
import io.github.ronjunevaldoz.tailwind.modifiers.gap1
import io.github.ronjunevaldoz.tailwind.modifiers.gap6
import io.github.ronjunevaldoz.tailwind.modifiers.isTwDarkTheme
import io.github.ronjunevaldoz.tailwind.modifiers.p6
import io.github.ronjunevaldoz.tailwind.modifiers.px3
import io.github.ronjunevaldoz.tailwind.modifiers.py2
import io.github.ronjunevaldoz.tailwind.modifiers.roundedMd
import io.github.ronjunevaldoz.tailwind.modifiers.textSm
import io.github.ronjunevaldoz.tailwind.modifiers.textWhite
import io.github.ronjunevaldoz.tailwind.modifiers.twDark
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
import io.github.ronjunevaldoz.tailwind.showcase.sections.ResponsiveShowcase
import io.github.ronjunevaldoz.tailwind.showcase.sections.RingShowcase
import io.github.ronjunevaldoz.tailwind.showcase.sections.ShadowShowcase
import io.github.ronjunevaldoz.tailwind.showcase.sections.SizingShowcase
import io.github.ronjunevaldoz.tailwind.showcase.sections.SpacingShowcase
import io.github.ronjunevaldoz.tailwind.showcase.sections.Transform2DShowcase
import io.github.ronjunevaldoz.tailwind.showcase.sections.Transform3DShowcase
import io.github.ronjunevaldoz.tailwind.showcase.sections.TransitionShowcase
import io.github.ronjunevaldoz.tailwind.showcase.sections.TypographyShowcase

/** Test tag for the sidebar's LazyColumn, so tests can scroll off-screen items into view. */
internal const val SIDEBAR_LIST_TEST_TAG = "sidebar-list"

/** Test tag for the whole app shell, so tests can capture it to verify dark-mode re-theming. */
internal const val APP_ROOT_TEST_TAG = "app-root"

/** Sidebar + detail-view scaffold — one category per screen, built entirely from the library's own Modifiers. */
@Suppress("ktlint:standard:function-naming")
@Composable
fun App() {
    MaterialTheme {
        var darkOverride by remember { mutableStateOf<Boolean?>(null) }
        CompositionLocalProvider(LocalTwDarkTheme provides darkOverride) {
            var selected by remember { mutableStateOf(ShowcaseCategory.Spacing) }
            Column(
                modifier =
                    Modifier
                        .testTag(APP_ROOT_TEST_TAG)
                        .bgWhite()
                        .twDark { bgSlate900() }
                        .fillMaxSize(),
            ) {
                TopBar(
                    isDark = isTwDarkTheme(),
                    onToggleDark = { darkOverride = it },
                )
                Row(modifier = Modifier.fillMaxSize()) {
                    Sidebar(selected = selected, onSelect = { selected = it })
                    Column(
                        modifier =
                            Modifier
                                .bgSlate50()
                                .twDark { bgSlate950() }
                                .fillMaxHeight()
                                .verticalScroll(rememberScrollState())
                                .p6(),
                        verticalArrangement = gap6(),
                    ) {
                        Breadcrumb(selected)
                        CategoryDetail(selected)
                    }
                }
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
    Column(
        modifier =
            Modifier
                .width(200.dp)
                .fillMaxHeight()
                .bgWhite()
                .twDark { bgSlate900() },
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth().fillMaxHeight().testTag(SIDEBAR_LIST_TEST_TAG),
            verticalArrangement = gap1(),
        ) {
            items(ShowcaseCategory.entries.toList()) { category ->
                val isSelected = category == selected
                Text(
                    category.title,
                    style =
                        MaterialTheme.typography.bodyMedium
                            .textSm()
                            .let { if (isSelected) it.fontBold().textWhite() else it.twDark { textWhite() } },
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .roundedMd()
                            .let { if (isSelected) it.bgSlate900() else it }
                            .clickable { onSelect(category) }
                            .px3()
                            .py2(),
                )
            }
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
        ShowcaseCategory.Ring -> RingShowcase()
        ShowcaseCategory.Flex -> FlexShowcase()
        ShowcaseCategory.Gradient -> GradientShowcase()
        ShowcaseCategory.Grid -> GridShowcase()
        ShowcaseCategory.Filters -> FiltersShowcase()
        ShowcaseCategory.Transition -> TransitionShowcase()
        ShowcaseCategory.Transform3D -> Transform3DShowcase()
        ShowcaseCategory.Transform2D -> Transform2DShowcase()
        ShowcaseCategory.DarkMode -> DarkModeShowcase()
        ShowcaseCategory.Responsive -> ResponsiveShowcase()
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
