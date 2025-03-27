package com.idea.core.recipes.usecases

import com.idea.core.recipes.repository.RecipesRepository

class GetLocalRecipes(private val repository: RecipesRepository) {

    suspend operator fun invoke() = repository.getLocalRecipes()
}