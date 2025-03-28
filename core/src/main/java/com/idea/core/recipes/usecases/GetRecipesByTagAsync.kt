package com.idea.core.recipes.usecases

import com.idea.core.recipes.repository.RecipesRepository

class GetRecipesByTagAsync(private val repository: RecipesRepository) {

    suspend operator fun invoke(tag: String) = repository.getRecipesByTagAsync(tag = tag)
}
