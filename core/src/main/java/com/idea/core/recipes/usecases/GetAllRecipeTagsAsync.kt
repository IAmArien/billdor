package com.idea.core.recipes.usecases

import com.idea.core.recipes.repository.RecipesRepository

class GetAllRecipeTagsAsync(private val repository: RecipesRepository) {

    suspend operator fun invoke() = repository.getAllRecipeTagsAsync()
}