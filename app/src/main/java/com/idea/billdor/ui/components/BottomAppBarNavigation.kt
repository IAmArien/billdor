package com.idea.billdor.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable

interface IBottomAppBarNavigation {
    fun onHomeClick()
    fun onTagsClick()
    fun onMyProfileClick()
    fun onShareClick()
}

@Composable
fun BottomAppBarNavigation(callback: IBottomAppBarNavigation) {
    BottomAppBar(
        actions = {
            IconButton(onClick = { callback.onHomeClick() }) {
                Icon(
                    imageVector = Icons.Filled.Home,
                    contentDescription = "Home"
                )
            }
            IconButton(onClick = { callback.onTagsClick() }) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "Tags"
                )
            }
            IconButton(onClick = { callback.onMyProfileClick() }) {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "My Profile"
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { callback.onShareClick() },
                containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
            ) {
                Icon(
                    imageVector = Icons.Filled.Share,
                    contentDescription = "Share"
                )
            }
        }
    )
}
