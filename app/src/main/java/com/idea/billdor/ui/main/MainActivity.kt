package com.idea.billdor.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.idea.billdor.ui.components.BottomAppBarNavigation
import com.idea.billdor.ui.components.IBottomAppBarNavigation
import com.idea.billdor.ui.components.ITopAppBarNavigation
import com.idea.billdor.ui.components.TopAppBarNavigation
import com.idea.billdor.ui.main.components.MainNavigation
import com.idea.billdor.ui.theme.BillDorTheme
import com.idea.billdor.ui.theme.CoolWhite
import com.idea.billdor.viewmodels.navigation.BottomAppBarViewModel
import com.idea.billdor.viewmodels.navigation.state.BottomAppBarState
import com.idea.billdor.viewmodels.recipes.RecipesViewModel
import com.idea.billdor.viewmodels.recipes.state.MealTypeState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity(), ITopAppBarNavigation, IBottomAppBarNavigation {

    private val bottomAppBarViewModel by viewModels<BottomAppBarViewModel>()
    private val recipesViewModel by viewModels<RecipesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { BillDorTheme { InternalMainScreenBuilder() } }
        lifecycleScope.launch { collectMealTypeState() }
    }

    @Composable
    private fun InternalMainScreenBuilder() {
        val navHostController = rememberNavController()
        val snackBarHostState = remember { SnackbarHostState() }
        Surface(color = CoolWhite) {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                topBar = { TopAppBarNavigation(this) },
                bottomBar = {
                    BottomAppBarNavigation(
                        bottomAppBarViewModel,
                        navHostController,
                        this
                    )
                },
                snackbarHost = { SnackbarHost(hostState = snackBarHostState) }
            ) { paddingValues ->
                MainNavigation(
                    navHostController,
                    snackBarHostState,
                    bottomAppBarViewModel,
                    recipesViewModel,
                    paddingValues
                )
            }
        }
    }

    override fun onNavigationIconClick() { }
    
    override fun onSearchFieldClick() { }

    override fun onHomeClick() {
        bottomAppBarViewModel.setSelectedItem(BottomAppBarState.Recipes())
    }

    override fun onTagsClick() {
        bottomAppBarViewModel.setSelectedItem(BottomAppBarState.Tags())
        recipesViewModel.getAllRecipeTagsAsync()
    }

    override fun onMyProfileClick() {
        bottomAppBarViewModel.setSelectedItem(BottomAppBarState.MyProfile())
    }

    private suspend fun collectMealTypeState() {
        recipesViewModel.selectedMealType.collectLatest { state ->
            when (state) {
                is MealTypeState.All -> {
                    recipesViewModel.setErrorEncountered(error = false)
                    recipesViewModel.getAllRecipesAsync()
                    recipesViewModel.getLocalRecipes()
                }
                is MealTypeState.Snacks,
                is MealTypeState.Dinner,
                is MealTypeState.Lunch,
                is MealTypeState.Breakfast -> {
                    recipesViewModel.setErrorEncountered(error = false)
                    recipesViewModel.getMealRecipesAsync(meal = state.type)
                    recipesViewModel.getLocalMealRecipes(meal = state.type)
                }
            }
        }
    }
}
