package com.idea.core.recipes.repository

import com.idea.core.ResponseWrapper
import com.idea.core.recipes.data.Recipes

interface RecipesDataSource {
    
    suspend fun getAllRecipesAsync(): ResponseWrapper<Recipes>
}
