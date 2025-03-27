package com.idea.billdor.repository.recipes

import com.idea.billdor.frameworks.api.RecipesApi
import com.idea.billdor.repository.BaseRepository
import javax.inject.Inject

class RecipesRepository @Inject constructor(private val api: RecipesApi) : BaseRepository() {

    suspend fun getAllRecipesAsync() = safeApiCall {
        val response = api.getAllRecipesAsync().await()
        response
    }
}
