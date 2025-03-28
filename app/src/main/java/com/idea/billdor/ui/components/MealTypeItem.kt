package com.idea.billdor.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.idea.billdor.R
import com.idea.billdor.ui.theme.BridalHealth
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
            .width(80.dp)
            .height(80.dp)
            .border(
                width = if (isSelected) 2.dp else 1.dp,
                color = if (isSelected) SelectiveYellow else SilverSand,
                shape = RoundedCornerShape(12.dp)
            )
            .background(color = if (isSelected) BridalHealth else White)
            .clip(RoundedCornerShape(12.dp))
            .clickable(
                enabled = true,
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
    }
}

@Composable
fun MealType(recipesViewModel: RecipesViewModel) {
    Column(modifier = Modifier.fillMaxWidth().background(color = White)) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
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
            }
        }
    }
}
