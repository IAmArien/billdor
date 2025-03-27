package com.idea.billdor.viewmodels.recipes

import androidx.lifecycle.viewModelScope
import com.idea.billdor.frameworks.datasource.RecipesUseCases
import com.idea.billdor.viewmodels.BaseViewModel
import com.idea.billdor.viewmodels.recipes.state.MealTypeState
import com.idea.billdor.viewmodels.recipes.state.RecipeState
import com.idea.core.ResponseWrapper
import com.idea.core.recipes.data.Recipes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(private val useCases: RecipesUseCases) : BaseViewModel() {

    private val _selectedMealType: MutableStateFlow<MealTypeState> =
        MutableStateFlow(MealTypeState.All())
    val selectedMealType: StateFlow<MealTypeState>
        get() = _selectedMealType
    
    private val _recipeState: MutableStateFlow<RecipeState> =
        MutableStateFlow(RecipeState.Default)
    val recipeState: StateFlow<RecipeState>
        get() = _recipeState

    fun getAllRecipesAsync() {
        _recipeState.value = RecipeState.Loading
        viewModelScope.launch {
            when (val response = useCases.getAllRecipesAsync.invoke()) {
                is ResponseWrapper.ResponseSuccess<Recipes> -> {
                    _recipeState.value = RecipeState.Success(response.value)
                }
                else -> {
                    _recipeState.value = RecipeState.Failure(null)
                }
            }
        }
    }

    fun getMealRecipesAsync(meal: String) {
        _recipeState.value = RecipeState.Loading
        viewModelScope.launch {
            when (val response = useCases.getMealRecipesAsync.invoke(meal = meal)) {
                is ResponseWrapper.ResponseSuccess<Recipes> -> {
                    _recipeState.value = RecipeState.Success(response.value)
                }
                else -> {
                    _recipeState.value = RecipeState.Failure(null)
                }
            }
        }
    }

    fun setSelectedMealType(type: MealTypeState) {
        _selectedMealType.value = type
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}
