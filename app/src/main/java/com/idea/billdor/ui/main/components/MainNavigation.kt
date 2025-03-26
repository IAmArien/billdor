package com.idea.billdor.ui.main.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.idea.billdor.ui.main.screens.MyProfileScreen
import com.idea.billdor.ui.main.screens.RecipesScreen
import com.idea.billdor.ui.main.screens.TagsScreen
import com.idea.billdor.viewmodels.navigation.state.BottomAppBarState

@Composable
fun MainNavigation(
    navHostController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navHostController,
        startDestination = BottomAppBarState.Recipes().name,
        modifier = Modifier.padding(paddingValues)
    ) {
        composable(route = BottomAppBarState.Recipes().name) {
            RecipesScreen()
        }
        composable(route = BottomAppBarState.Tags().name) {
            TagsScreen()
        }
        composable(route = BottomAppBarState.MyProfile().name) {
            MyProfileScreen()
        }
    }
}
