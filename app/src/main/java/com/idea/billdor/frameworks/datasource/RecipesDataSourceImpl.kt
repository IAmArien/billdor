package com.idea.billdor.frameworks.datasource

import com.idea.billdor.repository.recipes.RecipesRepository
import com.idea.core.ResponseWrapper
import com.idea.core.recipes.data.Recipes
import com.idea.core.recipes.repository.RecipesDataSource
import javax.inject.Inject

class RecipesDataSourceImpl @Inject constructor(private val repository: RecipesRepository) :
    RecipesDataSource {

    override suspend fun getAllRecipesAsync(): ResponseWrapper<Recipes> {
        return repository.getAllRecipesAsync()
    }
}
