package com.idea.billdor.frameworks.api

import com.idea.core.recipes.data.Recipes
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface RecipesApi {
    @GET("/recipes")
    fun getAllRecipesAsync(): Deferred<Recipes>

    @GET("/recipes/meal-type/{mealType}")
    fun getMealRecipesAsync(@Path("mealType") meal: String): Deferred<Recipes>

    @GET("/recipes/tags")
    fun getAllRecipeTagsAsync(): Deferred<List<String>>

    @GET("/recipes/tag/{tag}")
    fun getRecipesByTagAsync(@Path("tag") tag: String): Deferred<Recipes>
}
