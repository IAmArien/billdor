package com.idea.billdor.repository.recipes

import androidx.room.withTransaction
import com.idea.billdor.frameworks.api.RecipesApi
import com.idea.billdor.frameworks.database.entities.asMealRecipeList
import com.idea.billdor.frameworks.database.entities.asMealRecipesEntity
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
        response.apply { insertRecipes() }
    }

    suspend fun getMealRecipesAsync(meal: String) = safeApiCall {
        val response = api.getMealRecipesAsync(meal = meal).await()
        response.apply { insertMealRecipes(mealType = meal) }
    }

    suspend fun getAllRecipeTagsAsync() = safeApiCall {
        val response = api.getAllRecipeTagsAsync().await()
        response
    }

    suspend fun getRecipesByTagAsync(tag: String) = safeApiCall {
        val response = api.getRecipesByTagAsync(tag = tag).await()
        response
    }

    suspend fun getLocalRecipes() = safeApiCall {
        db.recipesDao.getLocalRecipes().asRecipeList()
    }

    suspend fun getLocalMealRecipes(meal: String) = safeApiCall {
        db.recipesDao.getLocalMealRecipes(mealType = meal).asMealRecipeList()
    }

    private suspend fun Recipes.insertRecipes() = safeCatching {
        db.apply {
            withTransaction {
                recipesDao.insertRecipes(recipes = recipes.asRecipesEntity())
            }
        }
    }

    private suspend fun Recipes.insertMealRecipes(mealType: String) = safeCatching {
        db.apply {
            withTransaction {
                recipesDao.deleteLocalMealRecipes(mealType = mealType)
                recipesDao.insertMealRecipes(
                    recipes = recipes.asMealRecipesEntity(mealType = mealType)
                )
            }
        }
    }
}
