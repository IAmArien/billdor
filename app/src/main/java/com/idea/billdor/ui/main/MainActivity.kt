package com.idea.billdor.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.idea.billdor.ui.components.BottomAppBarNavigation
import com.idea.billdor.ui.components.IBottomAppBarNavigation
import com.idea.billdor.ui.components.ITopAppBarNavigation
import com.idea.billdor.ui.components.TopAppBarNavigation
import com.idea.billdor.ui.theme.BillDorTheme
import com.idea.billdor.ui.theme.CoolWhite
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity(), ITopAppBarNavigation, IBottomAppBarNavigation {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { BillDorTheme { InternalMainScreenBuilder() } }
    }

    @Composable
    private fun InternalMainScreenBuilder() {
        Surface(color = CoolWhite) {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                topBar = { TopAppBarNavigation(this) },
                bottomBar = { BottomAppBarNavigation(this) }
            ) { paddingValues ->
                LazyColumn(contentPadding = paddingValues) {
                    item { }
                }
            }
        }
    }
    
    override fun onNavigationIconClick() { }
    
    override fun onSearchFieldClick() { }

    override fun onHomeClick() { }

    override fun onTagsClick() { }

    override fun onMyProfileClick() { }

    override fun onShareClick() { }
}
