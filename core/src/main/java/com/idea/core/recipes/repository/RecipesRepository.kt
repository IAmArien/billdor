package com.idea.core.recipes.repository

class RecipesRepository(private val dataSource: RecipesDataSource) {

    suspend fun getAllRecipesAsync() = dataSource.getAllRecipesAsync()
    
    suspend fun getMealRecipesAsync(meal: String) = dataSource.getMealRecipesAsync(meal = meal)

    suspend fun getAllRecipeTagsAsync() = dataSource.getAllRecipeTagsAsync()

    suspend fun getLocalRecipes() = dataSource.getLocalRecipes()

    suspend fun getLocalMealRecipes(meal: String) = dataSource.getLocalMealRecipes(meal = meal)
}
