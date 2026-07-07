package io.github.ronjunevaldoz.tailwind.showcase

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.testTag
import io.github.ronjunevaldoz.tailwind.modifiers.bgSlate900
import io.github.ronjunevaldoz.tailwind.modifiers.bgWhite
import io.github.ronjunevaldoz.tailwind.modifiers.fontBold
import io.github.ronjunevaldoz.tailwind.modifiers.gap4
import io.github.ronjunevaldoz.tailwind.modifiers.p4
import io.github.ronjunevaldoz.tailwind.modifiers.textLg
import io.github.ronjunevaldoz.tailwind.modifiers.textSlate50
import io.github.ronjunevaldoz.tailwind.modifiers.textSlate500
import io.github.ronjunevaldoz.tailwind.modifiers.textSm
import io.github.ronjunevaldoz.tailwind.modifiers.textWhite
import io.github.ronjunevaldoz.tailwind.modifiers.twDark

internal const val REPO_URL = "https://github.com/ronjunevaldoz/tailwind-compose"
internal const val DARK_MODE_TOGGLE_TEST_TAG = "topbar-dark-mode-toggle"

/**
 * App-shell header: title, a link to the repo (no vector asset -- generating a GitHub
 * brand mark would mean hand-drawing SVG path data, which this project's icon tooling
 * forbids; the source repo is the more useful link anyway), and the dark-mode toggle
 * that drives [LocalTwDarkTheme][io.github.ronjunevaldoz.tailwind.modifiers.LocalTwDarkTheme]
 * for the rest of the app shell.
 */
@Suppress("ktlint:standard:function-naming")
@Composable
fun TopBar(
    isDark: Boolean,
    onToggleDark: (Boolean) -> Unit,
) {
    val uriHandler = LocalUriHandler.current
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .bgWhite()
                .twDark { bgSlate900() }
                .p4(),
        horizontalArrangement = gap4(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            "tailwind-compose",
            style =
                MaterialTheme.typography.bodyLarge
                    .textLg()
                    .fontBold()
                    .twDark { textWhite() },
            modifier = Modifier.weight(1f),
        )
        Text(
            "GitHub ↗",
            style =
                MaterialTheme.typography.bodyMedium
                    .textSm()
                    .textSlate500()
                    .twDark { textSlate50() },
            modifier = Modifier.clickable { uriHandler.openUri(REPO_URL) },
        )
        Switch(
            checked = isDark,
            onCheckedChange = onToggleDark,
            modifier = Modifier.testTag(DARK_MODE_TOGGLE_TEST_TAG),
        )
    }
}
