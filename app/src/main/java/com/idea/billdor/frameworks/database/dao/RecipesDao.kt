package com.idea.billdor.frameworks.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.idea.billdor.frameworks.database.entities.MealRecipesEntity
import com.idea.billdor.frameworks.database.entities.RecipesEntity

@Dao
interface RecipesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecipes(recipes: List<RecipesEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMealRecipes(recipes: List<MealRecipesEntity>)

    @Query("SELECT * FROM RECIPES_LIST")
    fun getLocalRecipes(): List<RecipesEntity>

    @Query("SELECT * FROM MEAL_RECIPES_LIST WHERE mealType = :mealType")
    fun getLocalMealRecipes(mealType: String): List<MealRecipesEntity>
}
