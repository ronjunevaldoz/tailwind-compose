package io.github.ronjunevaldoz.tailwind.modifiers

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertLeftPositionInRootIsEqualTo
import androidx.compose.ui.test.assertTopPositionInRootIsEqualTo
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.unit.dp
import org.junit.Rule
import kotlin.test.Test

class FlexModifierTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun justifyCenter_centersChildOnRowsMainAxis() {
        composeTestRule.setContent {
            Row(modifier = Modifier.size(100.dp), horizontalArrangement = justifyCenter()) {
                Box(Modifier.testTag("child").size(20.dp))
            }
        }
        // (100 - 20) / 2 == 40
        composeTestRule.onNodeWithTag("child").assertLeftPositionInRootIsEqualTo(40.dp)
    }

    @Test
    fun justifyEnd_pushesChildToTheRowsEnd() {
        composeTestRule.setContent {
            Row(modifier = Modifier.size(100.dp), horizontalArrangement = justifyEnd()) {
                Box(Modifier.testTag("child").size(20.dp))
            }
        }
        composeTestRule.onNodeWithTag("child").assertLeftPositionInRootIsEqualTo(80.dp)
    }

    @Test
    fun itemsCenter_centersChildOnRowsCrossAxis() {
        composeTestRule.setContent {
            Row(modifier = Modifier.size(100.dp), verticalAlignment = itemsCenter()) {
                Box(Modifier.testTag("child").size(20.dp))
            }
        }
        composeTestRule.onNodeWithTag("child").assertTopPositionInRootIsEqualTo(40.dp)
    }

    @Test
    fun justifyCenterVertical_centersChildOnColumnsMainAxis() {
        composeTestRule.setContent {
            Column(modifier = Modifier.size(100.dp), verticalArrangement = justifyCenterVertical()) {
                Box(Modifier.testTag("child").size(20.dp))
            }
        }
        composeTestRule.onNodeWithTag("child").assertTopPositionInRootIsEqualTo(40.dp)
    }

    @Test
    fun itemsCenterHorizontal_centersChildOnColumnsCrossAxis() {
        composeTestRule.setContent {
            Column(modifier = Modifier.size(100.dp), horizontalAlignment = itemsCenterHorizontal()) {
                Box(Modifier.testTag("child").size(20.dp))
            }
        }
        composeTestRule.onNodeWithTag("child").assertLeftPositionInRootIsEqualTo(40.dp)
    }
}
