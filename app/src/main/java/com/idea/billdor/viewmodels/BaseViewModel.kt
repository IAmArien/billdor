package com.idea.billdor.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.cancel
import javax.inject.Inject

open class BaseViewModel @Inject constructor() : ViewModel() {

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}
