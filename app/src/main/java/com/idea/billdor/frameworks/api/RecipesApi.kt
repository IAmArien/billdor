package com.idea.billdor.frameworks.api

import com.idea.core.recipes.data.Recipes
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface RecipesApi {
    @GET("/recipes")
    fun getAllRecipesAsync(): Deferred<Recipes>
}
