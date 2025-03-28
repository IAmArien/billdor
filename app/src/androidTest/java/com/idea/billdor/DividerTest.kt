package com.idea.billdor

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.idea.billdor.ui.components.Divider
import com.idea.billdor.ui.components.DividerOrientation
import com.idea.billdor.ui.theme.BillDorTheme
import org.junit.Rule
import org.junit.Test

class DividerTest {

    @get:Rule val composeTestRule = createComposeRule()

    @Test
    fun ifDividerOrientationIsHorizontal() {
        composeTestRule.setContent {
            BillDorTheme {
                Divider(orientation = DividerOrientation.Horizontal)
            }
        }
        composeTestRule.onNodeWithTag(testTag = "divider-horizontal").assertIsDisplayed()
        composeTestRule.onNodeWithTag(testTag = "divider-vertical").assertIsNotDisplayed()
    }

    @Test
    fun ifDividerOrientationIsVertical() {
        composeTestRule.setContent {
            BillDorTheme {
                Divider(orientation = DividerOrientation.Vertical)
            }
        }
        composeTestRule.onNodeWithTag(testTag = "divider-horizontal").assertIsNotDisplayed()
        composeTestRule.onNodeWithTag(testTag = "divider-vertical").assertIsDisplayed()
    }
}
