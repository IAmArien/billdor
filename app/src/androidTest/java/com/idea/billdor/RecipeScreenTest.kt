package com.idea.billdor

import androidx.compose.material3.SnackbarHostState
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.idea.billdor.mocks.mockRecipeResponseJson
import com.idea.billdor.ui.main.screens.RecipesScreen
import com.idea.billdor.ui.theme.BillDorTheme
import com.idea.billdor.viewmodels.recipes.RecipesViewModel
import com.idea.billdor.viewmodels.recipes.state.RecipeState
import com.idea.core.recipes.data.Recipes
import io.mockk.mockk
import io.mockk.spyk
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RecipeScreenTest {

    @get:Rule val composeTestRule = createComposeRule()

    private lateinit var mockViewModel: RecipesViewModel
    private val snackBarHostState = SnackbarHostState()

    @Before
    fun setUp() {
        mockViewModel = spyk(RecipesViewModel(mockk()), recordPrivateCalls = true)
    }

    @Test
    fun ifMealTypeWillBeDisplayedAndSelected() {
        mockViewModel.setRecipeState(state = RecipeState.Loading)
        composeTestRule.setContent {
            BillDorTheme {
                RecipesScreen(
                    recipesViewModel = mockViewModel,
                    snackBarHostState = snackBarHostState
                )
            }
        }
        // Assert Loading Container
        composeTestRule.onNodeWithTag(testTag = "recipes-screen-container").assertIsDisplayed()
        // Assert Meal Type Items
        composeTestRule.onNodeWithTag(testTag = "meal-type-item-container").assertIsDisplayed()
        composeTestRule.onNodeWithTag(testTag = "meal-type-item-All-true").assertIsDisplayed()
        composeTestRule.onNodeWithTag(testTag = "meal-type-item-breakfast-false").assertIsDisplayed()
        composeTestRule.onNodeWithTag(testTag = "meal-type-item-lunch-false").assertIsDisplayed()
        composeTestRule.onNodeWithTag(testTag = "meal-type-item-dinner-false").assertIsDisplayed()
        composeTestRule.onNodeWithTag(testTag = "meal-type-item-snack-false").assertIsDisplayed()
        // Perform action and assertion
        composeTestRule.onNodeWithTag(testTag = "meal-type-item-lunch-false").performClick()
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithTag(testTag = "meal-type-item-lunch-true").assertIsDisplayed()
        composeTestRule.onNodeWithTag(testTag = "meal-type-item-All-false").assertIsDisplayed()
    }

    @Test
    fun ifLoadingContainerWillBeDisplayed() {
        mockViewModel.setRecipeState(state = RecipeState.Loading)
        composeTestRule.setContent {
            BillDorTheme {
                RecipesScreen(
                    recipesViewModel = mockViewModel,
                    snackBarHostState = snackBarHostState
                )
            }
        }
        // Assert Loading Container
        composeTestRule.onNodeWithTag(testTag = "recipes-screen-container").assertIsDisplayed()
        composeTestRule.onNodeWithTag(testTag = "loading-container").assertIsDisplayed()
    }

    @Test
    fun ifRecipesWillBeDisplayed() {
        mockViewModel.setRecipeState(state = RecipeState.Success(response = mockRecipeResponseJson))
        composeTestRule.setContent {
            BillDorTheme {
                RecipesScreen(
                    recipesViewModel = mockViewModel,
                    snackBarHostState = snackBarHostState
                )
            }
        }
        // Assert Recipe Screen List
        composeTestRule.onNodeWithTag(testTag = "recipes-screen-container").assertIsDisplayed()
        composeTestRule.onNodeWithTag(testTag = "loading-container").assertIsNotDisplayed()
        composeTestRule.onNodeWithTag(testTag = "recipes-screen-vertical-staggered-grid").assertIsDisplayed()
        // Assert at least 5 Recipes
        composeTestRule.onNodeWithTag(testTag = "recipe-item-overview-Classic Margherita Pizza").assertIsDisplayed()
        composeTestRule.onNodeWithTag(testTag = "recipe-item-overview-Vegetarian Stir-Fry").assertIsDisplayed()
        composeTestRule.onNodeWithTag(testTag = "recipe-item-overview-Chocolate Chip Cookies").assertIsDisplayed()
        composeTestRule.onNodeWithTag(testTag = "recipe-item-overview-Chicken Alfredo Pasta").assertIsDisplayed()
        composeTestRule.onNodeWithTag(testTag = "recipe-item-overview-Mango Salsa Chicken").assertIsDisplayed()
    }

    @Test
    fun ifSnackBarWillBeDisplayed() {
        mockViewModel.setErrorEncountered(error = true)
        mockViewModel.setRecipeState(
            state = RecipeState.Success(
                response = Recipes(
                    recipes = emptyList(),
                    total = 0L,
                    skip = 0L,
                    limit = 0L
                )
            )
        )
        composeTestRule.setContent {
            BillDorTheme {
                RecipesScreen(
                    recipesViewModel = mockViewModel,
                    snackBarHostState = snackBarHostState
                )
            }
        }
        // Assert Recipe Screen List
        composeTestRule.onNodeWithTag(testTag = "recipes-screen-container").assertIsDisplayed()
        // Assert SnackBar
        composeTestRule.waitForIdle()
        assert(snackBarHostState.currentSnackbarData?.visuals?.message?.isNotEmpty() == true)
        assert(snackBarHostState.currentSnackbarData?.visuals?.message == "Something went wrong")
    }
}
