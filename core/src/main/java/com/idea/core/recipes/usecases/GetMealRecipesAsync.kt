package com.idea.core.recipes.usecases

import com.idea.core.recipes.repository.RecipesRepository

class GetMealRecipesAsync(private val repository: RecipesRepository) {

    suspend operator fun invoke(meal: String) = repository.getMealRecipesAsync(meal = meal)
}
