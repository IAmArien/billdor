package com.idea.billdor.viewmodels.recipes.state

import com.idea.core.recipes.data.Recipes

sealed class RecipeTagState {

    object Default : RecipeTagState()

    object Loading : RecipeTagState()

    data class Success(val response: List<String>) : RecipeTagState()

    data class Failure(val throwable: Throwable?) : RecipeTagState()
}
