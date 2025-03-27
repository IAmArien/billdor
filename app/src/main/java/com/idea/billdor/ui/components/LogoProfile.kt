package com.idea.billdor.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.idea.billdor.R
import com.idea.billdor.ui.theme.Black
import com.idea.billdor.ui.theme.FontFamily
import com.idea.billdor.ui.theme.SelectiveYellow
import com.idea.billdor.viewmodels.recipes.RecipesViewModel
import com.idea.billdor.viewmodels.recipes.state.RecipeState

@Composable
fun LogoProfile(
    modifier: Modifier = Modifier,
    containerModifier: Modifier = Modifier,
    recipesViewModel: RecipesViewModel = viewModel(),
    onClick: (() -> Unit)? = null
) {
    val selectedMealType = recipesViewModel.selectedMealType.collectAsState()
    val recipeState = recipesViewModel.recipeState.collectAsState()
    val isLoading = recipeState.value is RecipeState.Loading
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight()
    ) {
        Box(
            modifier = containerModifier
                .testTag(tag = "img-logo-profile-container")
                .size(40.dp)
                .border(
                    width = 2.dp,
                    color = SelectiveYellow,
                    shape = RoundedCornerShape(99)
                )
                .clickable(
                    enabled = true,
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = { onClick?.invoke() }
                )
        ) {
            Image(
                painter = painterResource(id = R.drawable.billdor_logo),
                contentScale = ContentScale.Crop,
                contentDescription = "BillDor Logo",
                modifier = modifier
                    .testTag(tag = "img-logo-profile")
                    .size(40.dp)
                    .align(Alignment.Center)
            )
        }
        Text(
            text = if (isLoading) "Loading..." else selectedMealType.value.label,
            style = TextStyle(
                color = Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                fontStyle = FontStyle.Normal,
                fontFamily = FontFamily
            )
        )
    }
}
