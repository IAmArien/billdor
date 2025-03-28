package com.idea.billdor

import android.app.Application
import android.content.Intent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.idea.billdor.app.BillDorApplication
import com.idea.billdor.mocks.mockRecipeResponseJson
import com.idea.billdor.ui.components.RecipeItem
import com.idea.billdor.ui.theme.BillDorTheme
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.verify
import org.junit.Rule
import org.junit.Test

class RecipeItemTests {

    @get:Rule val composeTestRule = createComposeRule()
    
    @Test
    fun testIfRecipeObjectIsProperlyDisplayed() {
        composeTestRule.setContent {
            BillDorTheme {
                RecipeItem(recipeItem = mockRecipeResponseJson.recipes[0])
            }
        }
        // Assert Recipe Image
        composeTestRule.onNodeWithTag(testTag = "recipe-item-image-box-container").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription(label = "recipe-async-image").assertIsDisplayed()
        composeTestRule.onNodeWithTag(testTag = "recipe-item-async-image").assertIsDisplayed()
        // Assert Recipe Rating
        composeTestRule.onNodeWithTag(testTag = "recipe-item-rating-container").assertIsDisplayed()
        composeTestRule.onNodeWithText(text = "4.6").assertIsDisplayed()
        // Assert Recipe Name
        composeTestRule.onNodeWithText(text = "Classic Margherita Pizza").assertIsDisplayed()
        // Assert Recipe Tags
        composeTestRule.onNodeWithTag(testTag = "recipe-item-tags-flow-row-container").assertIsDisplayed()
        composeTestRule.onNodeWithText(text = "Pizza").assertIsDisplayed()
        composeTestRule.onNodeWithText(text = "Italian").assertIsDisplayed()
        composeTestRule.onNodeWithTag(testTag = "recipe-item-tags-Pizza-0").assertIsDisplayed()
        composeTestRule.onNodeWithTag(testTag = "recipe-item-tags-Italian-1").assertIsDisplayed()
        // Assert Recipe Ingredients
        composeTestRule.onNodeWithTag(testTag = "recipe-item-ingredients-text").assertIsDisplayed()
        composeTestRule.onNodeWithText(
            text = "Ingredients",
            substring = true,
            ignoreCase = true
        ).assertIsDisplayed()
        composeTestRule.onAllNodesWithText(
            text = "Pizza dough",
            substring = true,
            ignoreCase = true
        )[0].assertIsDisplayed()
        composeTestRule.onAllNodesWithText(
            text = "Tomato sauce",
            substring = true,
            ignoreCase = true
        )[0].assertIsDisplayed()
        // Assert Instructions
        composeTestRule.onNodeWithTag(testTag = "recipe-item-instructions-text").assertIsDisplayed()
        composeTestRule.onNodeWithText(
            text = "Instructions",
            substring = true,
            ignoreCase = true
        ).assertIsDisplayed()
        composeTestRule.onNodeWithText(
            text = "Preheat the oven to 475°F (245°C).",
            substring = true,
            ignoreCase = true
        ).assertIsDisplayed()
        composeTestRule.onNodeWithText(
            text = "Roll out the pizza dough and spread tomato sauce evenly.",
            substring = true,
            ignoreCase = true
        ).assertIsDisplayed()
        // Assert Nutrition Facts
        composeTestRule.onNodeWithTag(testTag = "recipe-item-nutrition-facts-description").assertIsDisplayed()
        composeTestRule.onNodeWithText(
            text = "Calories per serving: 300g",
            substring = true,
            ignoreCase = true
        ).assertIsDisplayed()
        composeTestRule.onNodeWithText(
            text = "Cuisine: Italian",
            substring = true,
            ignoreCase = true
        ).assertIsDisplayed()
        composeTestRule.onNodeWithText(
            text = "Difficulty: Easy",
            substring = true,
            ignoreCase = true
        ).assertIsDisplayed()
        // Assert Action Button
        composeTestRule.onNodeWithTag(testTag = "recipe-item-button-share").assertIsDisplayed()
    }

    @Test
    fun ifButtonShareWillIntentSuccessfully() {
        mockkObject(BillDorApplication)
        val mockContext = mockk<Application>(relaxed = true)
        every { BillDorApplication.getContext() } returns mockContext

        composeTestRule.setContent {
            BillDorTheme {
                RecipeItem(recipeItem = mockRecipeResponseJson.recipes[0])
            }
        }

        // Assert Action Button
        composeTestRule.onNodeWithTag(testTag = "recipe-item-button-share").assertIsDisplayed()
        composeTestRule.onNodeWithTag(testTag = "recipe-item-button-share").performClick()
        // Assert Intent
        verify {
            mockContext.startActivity(withArg { intent ->
                assert(intent.action == Intent.ACTION_CHOOSER)
                assert(
                    intent.getParcelableExtra(
                        Intent.EXTRA_INTENT,
                        Intent::class.java
                    )?.type == "text/plain"
                )
                assert(
                    intent.getParcelableExtra(
                        Intent.EXTRA_INTENT,
                        Intent::class.java
                    )?.getStringExtra(Intent.EXTRA_TEXT) == "https://dummyjson.com/docs/recipes"
                )
            })
        }
    }
}
