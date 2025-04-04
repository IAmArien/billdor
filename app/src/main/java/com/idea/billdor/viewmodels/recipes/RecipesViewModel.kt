package com.idea.billdor.viewmodels.recipes

import androidx.lifecycle.viewModelScope
import com.idea.billdor.frameworks.datasource.RecipesUseCases
import com.idea.billdor.viewmodels.BaseViewModel
import com.idea.billdor.viewmodels.recipes.state.MealTypeState
import com.idea.billdor.viewmodels.recipes.state.RecipeState
import com.idea.billdor.viewmodels.recipes.state.RecipeTagState
import com.idea.core.ResponseWrapper
import com.idea.core.recipes.data.Recipes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
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

    private val _recipeTagsState: MutableStateFlow<RecipeTagState> =
        MutableStateFlow(RecipeTagState.Default)
    val recipeTagsState: StateFlow<RecipeTagState>
        get() = _recipeTagsState

    private val _recipeByTagState: MutableStateFlow<RecipeState> =
        MutableStateFlow(RecipeState.Default)
    val recipeByTagState: StateFlow<RecipeState>
        get() = _recipeByTagState

    private val _errorEncountered: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val errorEncountered: StateFlow<Boolean>
        get() = _errorEncountered

    private var recipesCoroutineJob: Job? = null

    fun getAllRecipesAsync() {
        recipesCoroutineJob?.cancel()
        recipesCoroutineJob = viewModelScope.launch {
            _recipeState.value = RecipeState.Loading
            when (val response = useCases.getAllRecipesAsync.invoke()) {
                is ResponseWrapper.ResponseSuccess<Recipes> -> {
                    _recipeState.value = RecipeState.Success(
                        response = Recipes(
                            recipes = response.value.recipes.sortedBy { it.id },
                            total = response.value.recipes.size.toLong(),
                            skip = 0,
                            limit = 10
                        )
                    )
                }
                else -> {
                    setErrorEncountered(error = true)
                    getLocalRecipes()
                }
            }
        }
    }

    fun getMealRecipesAsync(meal: String) {
        recipesCoroutineJob?.cancel()
        recipesCoroutineJob = viewModelScope.launch {
            _recipeState.value = RecipeState.Loading
            when (val response = useCases.getMealRecipesAsync.invoke(meal = meal)) {
                is ResponseWrapper.ResponseSuccess<Recipes> -> {
                    _recipeState.value = RecipeState.Success(
                        response = Recipes(
                            recipes = response.value.recipes.sortedBy { it.id },
                            total = response.value.recipes.size.toLong(),
                            skip = 0,
                            limit = 10
                        )
                    )
                }
                else -> {
                    setErrorEncountered(error = true)
                    getLocalMealRecipes(meal = meal)
                }
            }
        }
    }

    fun getAllRecipeTagsAsync() {
        _recipeTagsState.value = RecipeTagState.Loading
        viewModelScope.launch {
            when (val response = useCases.getAllRecipeTagsAsync.invoke()) {
                is ResponseWrapper.ResponseSuccess<List<String>> -> {
                    _recipeTagsState.value = RecipeTagState.Success(
                        response = response.value
                    )
                }
                else -> {
                    _recipeTagsState.value = RecipeTagState.Failure(null)
                }
            }
        }
    }

    fun getRecipeByTagAsync(tag: String) {
        recipesCoroutineJob?.cancel()
        recipesCoroutineJob = viewModelScope.launch {
            _recipeByTagState.value = RecipeState.Loading
            when (val response = useCases.getRecipesByTagAsync.invoke(tag = tag)) {
                is ResponseWrapper.ResponseSuccess<Recipes> -> {
                    _recipeByTagState.value = RecipeState.Success(
                        response = Recipes(
                            recipes = response.value.recipes.sortedBy { it.id },
                            total = response.value.recipes.size.toLong(),
                            skip = 0,
                            limit = 10
                        )
                    )
                }
                else -> {
                    _recipeByTagState.value = RecipeState.Failure(null)
                }
            }
        }
    }

    fun getLocalRecipes() {
        _recipeState.value = RecipeState.Loading
        viewModelScope.launch {
            when (val response = useCases.getLocalRecipes.invoke()) {
                is ResponseWrapper.ResponseSuccess -> {
                    _recipeState.value = RecipeState.Success(
                        response = Recipes(
                            recipes = response.value.sortedBy { it.id },
                            total = response.value.size.toLong(),
                            skip = 0,
                            limit = 10
                        )
                    )
                }
                else -> {
                    _recipeState.value = RecipeState.Failure(null)
                }
            }
        }
    }

    fun getLocalMealRecipes(meal: String) {
        _recipeState.value = RecipeState.Loading
        viewModelScope.launch {
            when (val response = useCases.getLocalMealRecipes.invoke(meal = meal)) {
                is ResponseWrapper.ResponseSuccess -> {
                    _recipeState.value = RecipeState.Success(
                        response = Recipes(
                            recipes = response.value.sortedBy { it.id },
                            total = response.value.size.toLong(),
                            skip = 0,
                            limit = 10
                        )
                    )
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

    fun setErrorEncountered(error: Boolean) {
        _errorEncountered.value = error
    }

    fun setRecipeState(state: RecipeState) {
        _recipeState.value = state
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}
