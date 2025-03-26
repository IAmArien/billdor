package com.idea.billdor.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.idea.billdor.ui.theme.RollingStone
import com.idea.billdor.ui.theme.SilverSand
import com.idea.billdor.ui.theme.White

interface ITopAppBarNavigation {
    fun onNavigationIconClick()
    fun onSearchFieldClick()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarNavigation(callback: ITopAppBarNavigation) {
    TopAppBar(
        title = { LogoProfile { callback.onNavigationIconClick() } },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = White
        ),
        actions = {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .testTag(tag = "search-field-container")
                    .border(
                        width = 1.dp,
                        color = SilverSand,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(horizontal = 12.dp, vertical = 8.dp)
                    .clickable(
                        enabled = true,
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = { callback.onSearchFieldClick() }
                    )
            
            ) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search Icon",
                    tint = RollingStone,
                    modifier = Modifier
                        .testTag(tag = "img-search-icon")
                        .align(Alignment.CenterVertically)
                )
                Text(
                    text = "Search",
                    color = RollingStone,
                    modifier = Modifier
                        .testTag(tag = "text-search-label")
                        .align(Alignment.CenterVertically)
                )
            }
            Box(modifier = Modifier.width(12.dp))
        },
        modifier = Modifier.testTag(tag = "header-navigation-top-app-bar")
    )
}
