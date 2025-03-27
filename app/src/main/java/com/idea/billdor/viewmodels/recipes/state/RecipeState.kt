package com.idea.billdor.viewmodels.recipes.state

import com.idea.core.recipes.data.Recipes

sealed class RecipeState {

    object Default : RecipeState()

    object Loading : RecipeState()

    data class Success(val response: Recipes) : RecipeState()

    data class Failure(val throwable: Throwable?) : RecipeState()
}
