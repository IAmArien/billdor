package com.idea.billdor.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.ui.BottomNavigation
import com.idea.billdor.R
import com.idea.billdor.ui.theme.DarkLiver
import com.idea.billdor.ui.theme.FontFamily
import com.idea.billdor.ui.theme.RollingStone
import com.idea.billdor.ui.theme.SelectiveYellow
import com.idea.billdor.ui.theme.White
import com.idea.billdor.viewmodels.navigation.BottomAppBarViewModel
import com.idea.billdor.viewmodels.navigation.state.BottomAppBarState

interface IBottomAppBarNavigation {
    fun onHomeClick()
    fun onTagsClick()
    fun onMyProfileClick()
}

private fun getIconColor(
    currentState: BottomAppBarState,
    itemState: BottomAppBarState
): Color {
    return if (currentState.state === itemState.state) {
        SelectiveYellow
    } else {
        RollingStone
    }
}

private fun getTextStyle(
    currentState: BottomAppBarState,
    itemState: BottomAppBarState
): TextStyle {
    return if (currentState.state === itemState.state) {
        TextStyle(
            color = SelectiveYellow,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            fontStyle = FontStyle.Normal,
            fontFamily = FontFamily
        )
    } else {
        TextStyle(
            color = RollingStone,
            fontSize = 10.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            fontFamily = FontFamily
        )
    }
}

@Composable
fun BottomAppBarNavigation(
    bottomAppBarViewModel: BottomAppBarViewModel = viewModel<BottomAppBarViewModel>(),
    navHostController: NavHostController,
    callback: IBottomAppBarNavigation
) {
    val selectedItem = bottomAppBarViewModel.selectedItem
        .collectAsState(initial = BottomAppBarState.Recipes())
    BottomNavigation(
        backgroundColor = White,
        contentColor = DarkLiver,
        modifier = Modifier
            .testTag(tag = "bottom-app-bar-navigation")
            .navigationBarsWithImePadding()
            .height(70.dp)
    ) {
        BottomNavigationItem(
            selected = true,
            onClick = {
                navHostController.navigate(BottomAppBarState.Recipes().name)
                callback.onHomeClick()
            },
            icon = {
                if (selectedItem.value.state === BottomAppBarState.Recipes().name) {
                    Image(
                        painter = painterResource(R.drawable.billdor_home_icon),
                        contentDescription = "Recipes",
                        modifier = Modifier.size(30.dp)
                    )
                } else {
                    Icon(
                        imageVector = Icons.Filled.Home,
                        contentDescription = "Recipes",
                        tint = getIconColor(
                            selectedItem.value,
                            BottomAppBarState.Recipes()
                        )
                    )
                }
            },
            label = {
                Text(
                    text = "Recipes",
                    style = getTextStyle(
                        selectedItem.value,
                        BottomAppBarState.Recipes()
                    )
                )
            },
            modifier = Modifier.testTag(tag = "bottom-app-bar-item-recipes")
        )
        BottomNavigationItem(
            selected = true,
            onClick = {
                navHostController.navigate(BottomAppBarState.Tags().name)
                callback.onTagsClick()
            },
            icon = {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "Tags",
                    tint = getIconColor(
                        selectedItem.value,
                        BottomAppBarState.Tags()
                    )
                )
            },
            label = {
                Text(
                    text = "Tags",
                    style = getTextStyle(
                        selectedItem.value,
                        BottomAppBarState.Tags()
                    )
                )
            },
            modifier = Modifier.testTag(tag = "bottom-app-bar-item-tags")
        )
        BottomNavigationItem(
            selected = true,
            onClick = {
                navHostController.navigate(BottomAppBarState.MyProfile().name)
                callback.onMyProfileClick()
            },
            icon = {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "My Profile",
                    tint = getIconColor(
                        selectedItem.value,
                        BottomAppBarState.MyProfile()
                    )
                )
            },
            label = {
                Text(
                    text = "My Profile",
                    style = getTextStyle(
                        selectedItem.value,
                        BottomAppBarState.MyProfile()
                    )
                )
            },
            modifier = Modifier.testTag(tag = "bottom-app-bar-item-my-profile")
        )
    }
}
