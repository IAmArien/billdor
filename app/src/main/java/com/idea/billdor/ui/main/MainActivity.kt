package com.idea.billdor.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity(), ITopAppBarNavigation, IBottomAppBarNavigation {

    private val bottomAppBarViewModel by viewModels<BottomAppBarViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { BillDorTheme { InternalMainScreenBuilder() } }
    }

    @Composable
    private fun InternalMainScreenBuilder() {
        val navHostController = rememberNavController()
        Surface(color = CoolWhite) {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                topBar = { TopAppBarNavigation(this) },
                bottomBar = {
                    BottomAppBarNavigation(
                        bottomAppBarViewModel,
                        navHostController,
                        this@MainActivity
                    )
                }
            ) { paddingValues ->
                MainNavigation(navHostController, paddingValues)
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
    }

    override fun onMyProfileClick() {
        bottomAppBarViewModel.setSelectedItem(BottomAppBarState.MyProfile())
    }
}
