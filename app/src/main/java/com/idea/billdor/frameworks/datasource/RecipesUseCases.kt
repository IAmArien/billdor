package com.idea.billdor.frameworks.datasource

import com.idea.core.recipes.usecases.GetAllRecipeTagsAsync
import com.idea.core.recipes.usecases.GetAllRecipesAsync
import com.idea.core.recipes.usecases.GetLocalMealRecipes
import com.idea.core.recipes.usecases.GetLocalRecipes
import com.idea.core.recipes.usecases.GetMealRecipesAsync

data class RecipesUseCases(
    val getAllRecipesAsync: GetAllRecipesAsync,
    val getMealRecipesAsync: GetMealRecipesAsync,
    val getAllRecipeTagsAsync: GetAllRecipeTagsAsync,
    val getLocalRecipes: GetLocalRecipes,
    val getLocalMealRecipes: GetLocalMealRecipes
)
