package com.idea.billdor.ui.main.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.idea.billdor.ui.components.LoadingContainer
import com.idea.billdor.ui.components.RecipeItemTags
import com.idea.billdor.ui.theme.CoolWhite
import com.idea.billdor.viewmodels.recipes.RecipesViewModel
import com.idea.billdor.viewmodels.recipes.state.RecipeTagState

@Composable
fun TagsScreen(
    recipesViewModel: RecipesViewModel = viewModel<RecipesViewModel>(),
    snackBarHostState: SnackbarHostState
) {
    val recipeTagState = recipesViewModel.recipeTagsState.collectAsState().value
    val errorEncountered = recipesViewModel.errorEncountered.collectAsState().value
    
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
}
