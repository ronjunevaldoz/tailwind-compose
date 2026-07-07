package io.github.ronjunevaldoz.tailwind.modifiers

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertLeftPositionInRootIsEqualTo
import androidx.compose.ui.test.assertTopPositionInRootIsEqualTo
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.unit.dp
import org.junit.Rule
import kotlin.test.Test

class GridTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun gridCols2_wrapsTheThirdItemToASecondRow() {
        composeTestRule.setContent {
            LazyVerticalGrid(columns = gridCols2(), modifier = Modifier.size(80.dp)) {
                items(4) { index ->
                    Box(Modifier.testTag("cell$index").size(40.dp))
                }
            }
        }
        // 2 columns of 40.dp each: cell0 top-left, cell1 top-right, cell2 wraps to row 2.
        composeTestRule
            .onNodeWithTag(
                "cell0",
            ).assertLeftPositionInRootIsEqualTo(0.dp)
            .assertTopPositionInRootIsEqualTo(0.dp)
        composeTestRule
            .onNodeWithTag(
                "cell1",
            ).assertLeftPositionInRootIsEqualTo(40.dp)
            .assertTopPositionInRootIsEqualTo(0.dp)
        composeTestRule
            .onNodeWithTag(
                "cell2",
            ).assertLeftPositionInRootIsEqualTo(0.dp)
            .assertTopPositionInRootIsEqualTo(40.dp)
    }

    @Test
    fun gridCols4_fitsFourItemsOnOneRow() {
        composeTestRule.setContent {
            LazyVerticalGrid(columns = gridCols4(), modifier = Modifier.size(80.dp)) {
                items(4) { index ->
                    Box(Modifier.testTag("cell$index").size(20.dp))
                }
            }
        }
        composeTestRule
            .onNodeWithTag(
                "cell3",
            ).assertLeftPositionInRootIsEqualTo(60.dp)
            .assertTopPositionInRootIsEqualTo(0.dp)
    }
}
