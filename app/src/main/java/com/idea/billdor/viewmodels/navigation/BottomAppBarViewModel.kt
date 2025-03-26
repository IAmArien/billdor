package com.idea.billdor.viewmodels.navigation

import androidx.lifecycle.viewModelScope
import com.idea.billdor.viewmodels.BaseViewModel
import com.idea.billdor.viewmodels.navigation.state.BottomAppBarState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class BottomAppBarViewModel @Inject constructor() : BaseViewModel() {

    private val _selectedItem: MutableStateFlow<BottomAppBarState> =
        MutableStateFlow(BottomAppBarState.Recipes())
    val selectedItem: Flow<BottomAppBarState>
        get() = _selectedItem

    fun setSelectedItem(state: BottomAppBarState) {
        _selectedItem.value = state
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}
