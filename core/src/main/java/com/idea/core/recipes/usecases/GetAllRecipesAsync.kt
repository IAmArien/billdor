package com.idea.core.recipes.usecases

import com.idea.core.recipes.repository.RecipesRepository

class GetAllRecipesAsync(private val repository: RecipesRepository) {

    suspend operator fun invoke() = repository.getAllRecipesAsync()
}
