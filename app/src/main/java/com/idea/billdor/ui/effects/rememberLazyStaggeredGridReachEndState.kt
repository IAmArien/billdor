package com.idea.billdor.ui.effects

import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow

@Composable
fun rememberLazyStaggeredGridReachEndState(state: LazyStaggeredGridState): Boolean {
    val reachesEnd = remember { mutableStateOf(false) }
    LaunchedEffect(state) {
        snapshotFlow { state.layoutInfo }.collect { layoutInfo ->
            val lastVisibleItemIndex = layoutInfo.visibleItemsInfo.lastOrNull()?.index
            val totalItemsCount = layoutInfo.totalItemsCount
            reachesEnd.value = lastVisibleItemIndex != null && lastVisibleItemIndex >= totalItemsCount - 1
        }
    }
    return reachesEnd.value
}
