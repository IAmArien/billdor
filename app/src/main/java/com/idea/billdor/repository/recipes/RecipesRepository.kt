package com.idea.billdor.repository.recipes

import androidx.room.withTransaction
import com.idea.billdor.frameworks.api.RecipesApi
import com.idea.billdor.frameworks.database.entities.asRecipeList
import com.idea.billdor.frameworks.database.entities.asRecipesEntity
import com.idea.billdor.frameworks.database.room.BillDorDatabase
import com.idea.billdor.repository.BaseRepository
import com.idea.core.recipes.data.Recipes
import javax.inject.Inject

class RecipesRepository @Inject constructor(
    private val api: RecipesApi,
    private val db: BillDorDatabase
) : BaseRepository() {

    suspend fun getAllRecipesAsync() = safeApiCall {
        val response = api.getAllRecipesAsync().await()
        response.apply { insertRecipes(this) }
    }

    suspend fun getMealRecipesAsync(meal: String) = safeApiCall {
        val response = api.getMealRecipesAsync(meal = meal).await()
        response
    }

    suspend fun getLocalRecipes() = safeApiCall {
        db.recipesDao.getLocalRecipes().asRecipeList()
    }

    private suspend fun insertRecipes(response: Recipes) = safeCatching {
        db.apply {
            withTransaction {
                recipesDao.insertRecipes(recipes = response.recipes.asRecipesEntity())
            }
        }
    }
}
