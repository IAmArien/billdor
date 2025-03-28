package com.idea.billdor.ui.main.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.idea.billdor.ui.main.screens.MyProfileScreen
import com.idea.billdor.ui.main.screens.RecipesScreen
import com.idea.billdor.ui.main.screens.TagsScreen
import com.idea.billdor.viewmodels.navigation.BottomAppBarViewModel
import com.idea.billdor.viewmodels.navigation.state.BottomAppBarState
import com.idea.billdor.viewmodels.recipes.RecipesViewModel

@Composable
fun MainNavigation(
    navHostController: NavHostController,
    snackBarHostState: SnackbarHostState,
    bottomAppBarViewModel: BottomAppBarViewModel = viewModel<BottomAppBarViewModel>(),
    recipesViewModel: RecipesViewModel = viewModel<RecipesViewModel>(),
    paddingValues: PaddingValues
) {
    val selectedItem = bottomAppBarViewModel.selectedItem
        .collectAsState(BottomAppBarState.Recipes())
    NavHost(
        navController = navHostController,
        startDestination = selectedItem.value.state,
        modifier = Modifier.padding(paddingValues)
    ) {
        composable(route = BottomAppBarState.Recipes().name) {
            RecipesScreen(
                recipesViewModel = recipesViewModel,
                snackBarHostState = snackBarHostState
            )
        }
        composable(route = BottomAppBarState.Tags().name) {
            TagsScreen(
                recipesViewModel = recipesViewModel,
                snackBarHostState = snackBarHostState
            )
        }
        composable(route = BottomAppBarState.MyProfile().name) {
            MyProfileScreen()
        }
    }
}
