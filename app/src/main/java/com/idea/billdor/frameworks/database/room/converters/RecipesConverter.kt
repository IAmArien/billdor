package com.idea.billdor.frameworks.database.room.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.idea.core.recipes.data.Recipe

class RecipesConverter {

    @TypeConverter
    fun toRecipeObject(string: String?): Recipe? {
        return Gson().fromJson(string, Recipe::class.java)
    }
    
    @TypeConverter
    fun toRecipeObjectString(content: Recipe?): String? {
        return Gson().toJson(content)
    }
}
