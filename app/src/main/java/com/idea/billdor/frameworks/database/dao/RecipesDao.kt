package com.idea.billdor.frameworks.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.idea.billdor.frameworks.database.entities.RecipesEntity

@Dao
interface RecipesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecipes(recipes: List<RecipesEntity>)

    @Query("SELECT * FROM RECIPES_LIST")
    fun getLocalRecipes(): List<RecipesEntity>
}
