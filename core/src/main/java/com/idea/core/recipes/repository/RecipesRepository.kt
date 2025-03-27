package com.idea.core.recipes.repository

class RecipesRepository(private val dataSource: RecipesDataSource) {

    suspend fun getAllRecipesAsync() = dataSource.getAllRecipesAsync()
    
    suspend fun getMealRecipesAsync(meal: String) = dataSource.getMealRecipesAsync(meal = meal)
}
