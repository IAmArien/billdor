package com.idea.billdor.frameworks.datasource

import com.idea.core.recipes.usecases.GetAllRecipesAsync
import com.idea.core.recipes.usecases.GetMealRecipesAsync

data class RecipesUseCases(
    val getAllRecipesAsync: GetAllRecipesAsync,
    val getMealRecipesAsync: GetMealRecipesAsync
)
