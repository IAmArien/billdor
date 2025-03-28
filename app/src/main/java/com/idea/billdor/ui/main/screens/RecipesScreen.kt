package com.idea.billdor.ui.main.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.idea.billdor.ui.components.MealType
import com.idea.billdor.ui.components.RecipeItem
import com.idea.billdor.ui.components.RecipeItemOverview
import com.idea.billdor.ui.components.RecipeItemPlaceholder
import com.idea.billdor.ui.effects.rememberLazyStaggeredGridReachEndState
import com.idea.billdor.ui.theme.CoolWhite
import com.idea.billdor.viewmodels.recipes.RecipesViewModel
import com.idea.billdor.viewmodels.recipes.state.RecipeState
import com.idea.core.recipes.data.Recipe
import timber.log.Timber

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipesScreen(
    recipesViewModel: RecipesViewModel = viewModel<RecipesViewModel>(),
    snackBarHostState: SnackbarHostState
) {
    val recipeState = recipesViewModel.recipeState.collectAsState().value
    val errorEncountered = recipesViewModel.errorEncountered.collectAsState().value
    val staggeredGridState = rememberLazyStaggeredGridState()
    val reachesEnd = rememberLazyStaggeredGridReachEndState(staggeredGridState)

    val bottomSheetState = rememberModalBottomSheetState()
    val showBottomSheet = remember { mutableStateOf(false) }
    val selectedRecipeItem = remember { mutableStateOf<Recipe?>(null) }

    LaunchedEffect(reachesEnd) {
        if (reachesEnd) {
            Timber.d("Reached end of the list")
        }
    }

    LaunchedEffect(errorEncountered) {
        if (errorEncountered) {
            val result = snackBarHostState.showSnackbar(
                message = "Something went wrong",
                actionLabel = "DISMISS"
            )
            if (
                result == SnackbarResult.Dismissed ||
                result == SnackbarResult.ActionPerformed
            ) {
                recipesViewModel.setErrorEncountered(error = false)
            }
        }
    }

    Column(
        modifier = Modifier
            .testTag(tag = "recipes-screen-container")
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = CoolWhite)
    ) {
        MealType(recipesViewModel = recipesViewModel)
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            state = staggeredGridState,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .testTag(tag = "recipes-screen-vertical-staggered-grid")
                .padding(start = 12.dp, end = 12.dp)
                .nestedScroll(rememberNestedScrollInteropConnection())
        ) {
            items(2) { Box(Modifier.padding(bottom = 12.dp)) }
            when (recipeState) {
                is RecipeState.Success -> {
                    if (recipeState.response.recipes.isNotEmpty()) {
                        items(recipeState.response.recipes) { recipe ->
                            RecipeItemOverview(
                                recipeItem = recipe,
                                modifier = Modifier.padding(bottom = 12.dp),
                                onClick = { recipeItem ->
                                    selectedRecipeItem.value = recipeItem
                                    showBottomSheet.value = true
                                }
                            )
                        }
                    } else {
                        items(6) { RecipeItemPlaceholder() }
                    }
                }
                is RecipeState.Loading,
                is RecipeState.Default -> {
                    items(6) { RecipeItemPlaceholder() }
                }
                else -> { }
            }
        }
    }

    if (showBottomSheet.value && selectedRecipeItem.value !== null) {
        ModalBottomSheet(
            onDismissRequest = { showBottomSheet.value = false },
            sheetState = bottomSheetState
        ) {
            RecipeItem(recipeItem = selectedRecipeItem.value!!)
        }
    }
}
