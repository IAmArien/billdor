package com.idea.billdor.frameworks.database.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.idea.core.recipes.data.Recipe

@Entity(tableName = "RECIPES_LIST", indices = [ Index(value = ["recipeId"], unique = true) ])
data class RecipesEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val recipeId: Long,
    val recipe: Recipe
)

@Entity(tableName = "MEAL_RECIPES_LIST", indices = [ Index(value = ["recipeId"], unique = true) ])
data class MealRecipesEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val recipeId: Long,
    val mealType: String,
    val recipe: Recipe
)

fun List<RecipesEntity>.asRecipeList(): List<Recipe> {
    return map { item -> item.recipe }
}

fun List<Recipe>.asRecipesEntity(): List<RecipesEntity> {
    return map { item ->
        RecipesEntity(
            id = 0,
            recipeId = item.id,
            recipe = item
        )
    }
}

fun List<MealRecipesEntity>.asMealRecipeList(): List<Recipe> {
    return map { item -> item.recipe }
}

fun List<Recipe>.asMealRecipesEntity(mealType: String): List<MealRecipesEntity> {
    return map { item ->
        MealRecipesEntity(
            id = 0,
            recipeId = item.id,
            mealType = mealType,
            recipe = item
        )
    }
}
