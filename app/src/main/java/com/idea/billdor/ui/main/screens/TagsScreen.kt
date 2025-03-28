package com.idea.billdor.ui.main.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
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
import com.idea.billdor.ui.components.LoadingContainer
import com.idea.billdor.ui.components.RecipeItem
import com.idea.billdor.ui.components.RecipeItemOverview
import com.idea.billdor.ui.components.RecipeItemTags
import com.idea.billdor.ui.theme.CoolWhite
import com.idea.billdor.viewmodels.recipes.RecipesViewModel
import com.idea.billdor.viewmodels.recipes.state.RecipeState
import com.idea.billdor.viewmodels.recipes.state.RecipeTagState
import com.idea.core.recipes.data.Recipe

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TagsScreen(
    recipesViewModel: RecipesViewModel = viewModel<RecipesViewModel>(),
    snackBarHostState: SnackbarHostState
) {
    val recipeTagState = recipesViewModel.recipeTagsState.collectAsState().value
    val recipeByTagState = recipesViewModel.recipeByTagState.collectAsState().value
    val errorEncountered = recipesViewModel.errorEncountered.collectAsState().value

    val showRecipesBottomSheet = remember { mutableStateOf(false) }
    val showRecipeInfoBottomSheet = remember { mutableStateOf(false) }
    val selectedRecipeItem = remember { mutableStateOf<Recipe?>(null) }
    
    val staggeredGridState = rememberLazyStaggeredGridState()
    val bottomSheetState = rememberModalBottomSheetState()

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

    when (recipeTagState) {
        is RecipeTagState.Success -> {
            Box(
                modifier = Modifier
                    .testTag(tag = "tags-screen-main-container")
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(color = CoolWhite)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(vertical = 16.dp)
                ) {
                    item {
                        RecipeItemTags(
                            recipeItemTags = recipeTagState.response,
                            modifier = Modifier
                                .testTag(tag = "tags-screen-recipe-item-tags")
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .padding(horizontal = 16.dp)
                        ) { tagItem ->
                            recipesViewModel.getRecipeByTagAsync(tag = tagItem)
                            showRecipesBottomSheet.value = true
                        }
                    }
                }
            }
        }
        is RecipeTagState.Loading,
        is RecipeTagState.Default -> {
            LoadingContainer()
        }
        is RecipeTagState.Failure -> {
            recipesViewModel.setErrorEncountered(error = true)
        }
    }

    if (showRecipeInfoBottomSheet.value && selectedRecipeItem.value !== null) {
        ModalBottomSheet(
            onDismissRequest = { showRecipeInfoBottomSheet.value = false },
            sheetState = bottomSheetState
        ) {
            RecipeItem(recipeItem = selectedRecipeItem.value!!)
        }
    }

    if (showRecipesBottomSheet.value) {
        ModalBottomSheet(
            onDismissRequest = { showRecipesBottomSheet.value = false },
            sheetState = bottomSheetState
        ) {
            Column(
                modifier = Modifier
                    .testTag(tag = "recipes-screen-container")
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(color = CoolWhite)
            ) {
                when (recipeByTagState) {
                    is RecipeState.Success -> {
                        if (recipeByTagState.response.recipes.isNotEmpty()) {
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
                                items(recipeByTagState.response.recipes) { recipe ->
                                    RecipeItemOverview(
                                        recipeItem = recipe,
                                        modifier = Modifier.padding(bottom = 12.dp),
                                        onClick = { recipeItem ->
                                            selectedRecipeItem.value = recipeItem
                                            showRecipesBottomSheet.value = false
                                            showRecipeInfoBottomSheet.value = true
                                        }
                                    )
                                }
                            }
                        } else {
                            LoadingContainer(modifier = Modifier.wrapContentHeight())
                        }
                    }
                    is RecipeState.Loading,
                    is RecipeState.Default -> {
                        LoadingContainer(modifier = Modifier.wrapContentHeight())
                    }
                    else -> { }
                }
            }
        }
    }
}
