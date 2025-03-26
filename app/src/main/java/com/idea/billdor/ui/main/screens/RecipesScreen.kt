package com.idea.billdor.ui.main.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.idea.billdor.R
import com.idea.billdor.ui.components.Divider
import com.idea.billdor.ui.components.DividerOrientation
import com.idea.billdor.ui.theme.BridalHealth
import com.idea.billdor.ui.theme.DarkLiver
import com.idea.billdor.ui.theme.FontFamily
import com.idea.billdor.ui.theme.SelectiveYellow
import com.idea.billdor.ui.theme.SilverSand
import com.idea.billdor.ui.theme.White
import com.idea.billdor.viewmodels.recipes.RecipesViewModel
import com.idea.billdor.viewmodels.recipes.state.MealTypeState

@Composable
private fun MealTypeItem(
    recipesViewModel: RecipesViewModel,
    mealTypeState: MealTypeState,
    painter: Painter
) {
    val selectedMealType = recipesViewModel.selectedMealType.collectAsState()
    val isSelected = selectedMealType.value.type === mealTypeState.type
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(115.dp)
            .height(115.dp)
            .border(
                width = if (isSelected) 2.dp else 1.dp,
                color = if (isSelected) SelectiveYellow else SilverSand,
                shape = RoundedCornerShape(12.dp)
            )
            .background(color = if (isSelected) BridalHealth else White)
            .clickable(
                enabled = true,
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = {
                    recipesViewModel.setSelectedMealType(mealTypeState)
                }
            )
    ) {
        Image(
            painter = painter,
            modifier = Modifier
                .size(70.dp),
            contentDescription = "meal-type-item",
            contentScale = ContentScale.Fit
        )
        Text(
            text = mealTypeState.type,
            style = TextStyle(
                color = if (isSelected) SelectiveYellow else DarkLiver,
                fontSize = 14.sp,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                fontStyle = FontStyle.Normal,
                fontFamily = FontFamily
            ),
            modifier = Modifier.padding(top = 5.dp)
        )
    }
}

@Composable
private fun MealType(recipesViewModel: RecipesViewModel) {
    Column(modifier = Modifier.fillMaxWidth().background(color = White)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)
        ) {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                item { Divider(orientation = DividerOrientation.Vertical) }
                item {
                    MealTypeItem(
                        recipesViewModel = recipesViewModel,
                        mealTypeState = MealTypeState.All(),
                        painter = painterResource(id = R.drawable.billdor_home_icon)
                    )
                }
                item {
                    MealTypeItem(
                        recipesViewModel = recipesViewModel,
                        mealTypeState = MealTypeState.Breakfast(),
                        painter = painterResource(id = R.drawable.breakfast_icon)
                    )
                }
                item {
                    MealTypeItem(
                        recipesViewModel = recipesViewModel,
                        mealTypeState = MealTypeState.Lunch(),
                        painter = painterResource(id = R.drawable.lunch_icon)
                    )
                }
                item {
                    MealTypeItem(
                        recipesViewModel = recipesViewModel,
                        mealTypeState = MealTypeState.Dinner(),
                        painter = painterResource(id = R.drawable.dinner_icon)
                    )
                }
                item {
                    MealTypeItem(
                        recipesViewModel = recipesViewModel,
                        mealTypeState = MealTypeState.Snacks(),
                        painter = painterResource(id = R.drawable.snacks_icon)
                    )
                }
                item { Divider(orientation = DividerOrientation.Vertical) }
            }
        }
    }
}

@Composable
fun RecipesScreen(
    recipesViewModel: RecipesViewModel = viewModel<RecipesViewModel>()
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        item { MealType(recipesViewModel) }
    }
}
