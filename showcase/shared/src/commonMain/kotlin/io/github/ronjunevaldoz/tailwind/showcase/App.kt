package io.github.ronjunevaldoz.tailwind.showcase

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

/**
 * Foundation-sprint placeholder shell. Real per-utility showcase screens (spacing,
 * color, typography, borders) replace this in a later sprint.
 */
@Suppress("ktlint:standard:function-naming")
@Composable
@Preview
fun App() {
    MaterialTheme {
        Column(
            modifier =
                Modifier
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .safeContentPadding()
                    .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text("tailwind-compose showcase")
        }
    }
}
