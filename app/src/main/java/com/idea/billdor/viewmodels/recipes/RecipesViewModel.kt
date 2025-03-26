package com.idea.billdor.viewmodels.recipes

import androidx.lifecycle.viewModelScope
import com.idea.billdor.viewmodels.BaseViewModel
import com.idea.billdor.viewmodels.recipes.state.MealTypeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor() : BaseViewModel() {

    private val _selectedMealType: MutableStateFlow<MealTypeState> =
        MutableStateFlow(MealTypeState.All())
    val selectedMealType: StateFlow<MealTypeState>
        get() = _selectedMealType

    fun setSelectedMealType(type: MealTypeState) {
        _selectedMealType.value = type
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}
