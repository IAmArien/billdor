package com.idea.billdor.ui.components

import android.content.Intent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ShareCompat
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.idea.billdor.app.BillDorApplication
import com.idea.billdor.ui.theme.Black
import com.idea.billdor.ui.theme.DarkLiver
import com.idea.billdor.ui.theme.FontFamily
import com.idea.billdor.ui.theme.RollingStone
import com.idea.billdor.ui.theme.SelectiveYellow
import com.idea.billdor.ui.theme.SilverSand
import com.idea.billdor.ui.theme.White
import com.idea.core.recipes.data.Recipe

@Composable
private fun RecipeItemImage(height: Int = 250, recipeItem: Recipe) {
    Box(
        modifier = Modifier
            .testTag(tag = "recipe-item-image-box-container")
            .fillMaxWidth()
            .height(height.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(recipeItem.image)
                .crossfade(true)
                .build(),
            contentDescription = "recipe-async-image",
            contentScale = ContentScale.Crop,
            clipToBounds = true,
            modifier = Modifier
                .testTag(tag = "recipe-item-async-image")
                .fillMaxWidth()
                .height(height.dp)
        )
        Row(
            modifier = Modifier
                .testTag(tag = "recipe-item-rating-container")
                .wrapContentWidth()
                .wrapContentHeight()
                .align(Alignment.BottomStart)
                .padding(start = 10.dp, bottom = 10.dp)
        ) {
            Row(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight()
                    .background(color = White, shape = RoundedCornerShape(6.dp))
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(3.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 3.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = "Ratings",
                        tint = SelectiveYellow,
                        modifier = Modifier.size(15.dp)
                    )
                    Text(
                        text = "${recipeItem.rating}",
                        style = TextStyle(
                            color = DarkLiver,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            fontStyle = FontStyle.Normal,
                            fontFamily = FontFamily
                        )
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun RecipeItemTags(recipeItem: Recipe) {
    if (recipeItem.tags.isNotEmpty()) {
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier.testTag(tag = "recipe-item-tags-flow-row-container")
        ) {
            recipeItem.tags.forEachIndexed { index, tagItem ->
                Box(
                    modifier = Modifier
                        .testTag(tag = "recipe-item-tags-$tagItem-$index")
                        .wrapContentWidth()
                        .wrapContentHeight()
                        .border(
                            width = 1.dp,
                            color = SilverSand,
                            shape = RoundedCornerShape(6.dp)
                        )
                ) {
                    Text(
                        text = tagItem,
                        style = TextStyle(
                            color = RollingStone,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            fontStyle = FontStyle.Normal,
                            fontFamily = FontFamily
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .testTag(tag = "recipe-item-tag-$tagItem")
                            .padding(horizontal = 10.dp, vertical = 3.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun RecipeItemIngredients(title: String? = null, isOverView: Boolean, recipeItem: Recipe) {
    Text(
        text = buildAnnotatedString {
            title?.let { titleLabel ->
                withStyle(
                    style = SpanStyle(
                        color = Black,
                        fontSize = if (isOverView) 12.sp else 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontStyle = FontStyle.Normal,
                        fontFamily = FontFamily
                    ),
                ) {
                    append("$titleLabel:")
                    append("\n")
                }
            }
            recipeItem.ingredients.forEachIndexed { index, ingredientItem ->
                if (index.plus(1) == recipeItem.ingredients.size) {
                    withStyle(
                        style = SpanStyle(
                            color = RollingStone,
                            fontSize = if (isOverView) 12.sp else 14.sp,
                            fontWeight = FontWeight.Normal,
                            fontStyle = FontStyle.Normal,
                            fontFamily = FontFamily
                        ),
                    ) {
                        append(ingredientItem)
                    }
                } else {
                    withStyle(
                        style = SpanStyle(
                            color = RollingStone,
                            fontSize = if (isOverView) 12.sp else 14.sp,
                            fontWeight = FontWeight.Normal,
                            fontStyle = FontStyle.Normal,
                            fontFamily = FontFamily
                        ),
                    ) {
                        append(ingredientItem)
                        append(", ")
                    }
                }
            }
        },
        style = TextStyle(
            color = RollingStone,
            fontSize = if (isOverView) 12.sp else 14.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            fontFamily = FontFamily
        ),
        maxLines = if (isOverView) 2 else Int.MAX_VALUE,
        overflow = if (isOverView) TextOverflow.Ellipsis else TextOverflow.Clip,
        modifier = Modifier
            .testTag(tag = "recipe-item-ingredients-text")
            .padding(top = 8.dp)
    )
}

@Composable
private fun RecipeItemInstructions(recipeItem: Recipe) {
    Text(
        text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = Black,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontStyle = FontStyle.Normal,
                    fontFamily = FontFamily
                ),
            ) {
                append("Instructions:")
                append("\n")
            }
            recipeItem.instructions.forEachIndexed { index, instructionsItem ->
                if (index.plus(1) == recipeItem.ingredients.size) {
                    withStyle(
                        style = SpanStyle(
                            color = RollingStone,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            fontStyle = FontStyle.Normal,
                            fontFamily = FontFamily
                        ),
                    ) {
                        append(instructionsItem)
                    }
                } else {
                    withStyle(
                        style = SpanStyle(
                            color = RollingStone,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            fontStyle = FontStyle.Normal,
                            fontFamily = FontFamily
                        ),
                    ) {
                        append(instructionsItem)
                        append("\n")
                    }
                }
            }
        },
        style = TextStyle(
            color = Black,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            fontFamily = FontFamily
        )
    )
}

@Composable
private fun RecipeItemNutritionFactsDescription(isOverView: Boolean, recipeItem: Recipe) {
    Column(
        verticalArrangement = Arrangement.spacedBy(3.dp),
        modifier = Modifier
            .testTag(tag = "recipe-item-nutrition-facts-description")
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        recipeItem.caloriesPerServing?.let { caloriesPerServing ->
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Black,
                            fontSize = if (isOverView) 12.sp else 14.sp,
                            fontWeight = FontWeight.Normal,
                            fontStyle = FontStyle.Normal,
                            fontFamily = FontFamily
                        )
                    ) {
                        append(text = "Calories per serving: ")
                    }
                    withStyle(
                        style = SpanStyle(
                            color = RollingStone,
                            fontSize = if (isOverView) 12.sp else 14.sp,
                            fontWeight = FontWeight.Normal,
                            fontStyle = FontStyle.Normal,
                            fontFamily = FontFamily
                        )
                    ) {
                        append(text = "${caloriesPerServing}g")
                    }
                },
                style = TextStyle(
                    color = RollingStone,
                    fontSize = if (isOverView) 12.sp else 14.sp,
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Normal,
                    fontFamily = FontFamily
                ),
                maxLines = if (isOverView) 2 else Int.MAX_VALUE,
                overflow = if (isOverView) TextOverflow.Ellipsis else TextOverflow.Clip
            )
        }
        recipeItem.cuisine?.let { cuisine->
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Black,
                            fontSize = if (isOverView) 12.sp else 14.sp,
                            fontWeight = FontWeight.Normal,
                            fontStyle = FontStyle.Normal,
                            fontFamily = FontFamily
                        )
                    ) {
                        append(text = "Cuisine: ")
                    }
                    withStyle(
                        style = SpanStyle(
                            color = RollingStone,
                            fontSize = if (isOverView) 12.sp else 14.sp,
                            fontWeight = FontWeight.Normal,
                            fontStyle = FontStyle.Normal,
                            fontFamily = FontFamily
                        )
                    ) {
                        append(text = cuisine)
                    }
                },
                style = TextStyle(
                    color = RollingStone,
                    fontSize = if (isOverView) 12.sp else 14.sp,
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Normal,
                    fontFamily = FontFamily
                ),
                maxLines = if (isOverView) 2 else Int.MAX_VALUE,
                overflow = if (isOverView) TextOverflow.Ellipsis else TextOverflow.Clip
            )
        }
        recipeItem.difficulty?.let { difficulty ->
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Black,
                            fontSize = if (isOverView) 12.sp else 14.sp,
                            fontWeight = FontWeight.Normal,
                            fontStyle = FontStyle.Normal,
                            fontFamily = FontFamily
                        )
                    ) {
                        append(text = "Difficulty: ")
                    }
                    withStyle(
                        style = SpanStyle(
                            color = RollingStone,
                            fontSize = if (isOverView) 12.sp else 14.sp,
                            fontWeight = FontWeight.Normal,
                            fontStyle = FontStyle.Normal,
                            fontFamily = FontFamily
                        )
                    ) {
                        append(text = difficulty)
                    }
                },
                style = TextStyle(
                    color = RollingStone,
                    fontSize = if (isOverView) 12.sp else 14.sp,
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Normal,
                    fontFamily = FontFamily
                ),
                maxLines = if (isOverView) 2 else Int.MAX_VALUE,
                overflow = if (isOverView) TextOverflow.Ellipsis else TextOverflow.Clip
            )
        }
        if (isOverView == false) {
            recipeItem.prepTimeMinutes?.let { prepTimeMinutes ->
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = Black,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Normal,
                                fontStyle = FontStyle.Normal,
                                fontFamily = FontFamily
                            )
                        ) {
                            append(text = "Preparation Time (min): ")
                        }
                        withStyle(
                            style = SpanStyle(
                                color = RollingStone,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Normal,
                                fontStyle = FontStyle.Normal,
                                fontFamily = FontFamily
                            )
                        ) {
                            append(text = "$prepTimeMinutes minutes")
                        }
                    },
                    style = TextStyle(
                        color = RollingStone,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        fontStyle = FontStyle.Normal,
                        fontFamily = FontFamily
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
            recipeItem.cookTimeMinutes?.let { cookTimeMinutes ->
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = Black,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Normal,
                                fontStyle = FontStyle.Normal,
                                fontFamily = FontFamily
                            )
                        ) {
                            append(text = "Cooking Time (min): ")
                        }
                        withStyle(
                            style = SpanStyle(
                                color = RollingStone,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Normal,
                                fontStyle = FontStyle.Normal,
                                fontFamily = FontFamily
                            )
                        ) {
                            append(text = "$cookTimeMinutes minutes")
                        }
                    },
                    style = TextStyle(
                        color = RollingStone,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        fontStyle = FontStyle.Normal,
                        fontFamily = FontFamily
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
            recipeItem.servings?.let { servings ->
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = Black,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Normal,
                                fontStyle = FontStyle.Normal,
                                fontFamily = FontFamily
                            )
                        ) {
                            append(text = "Servings: ")
                        }
                        withStyle(
                            style = SpanStyle(
                                color = RollingStone,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Normal,
                                fontStyle = FontStyle.Normal,
                                fontFamily = FontFamily
                            )
                        ) {
                            append(text = "$servings")
                        }
                    },
                    style = TextStyle(
                        color = RollingStone,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        fontStyle = FontStyle.Normal,
                        fontFamily = FontFamily
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
fun RecipeItemPlaceholder() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(230.dp)
                .wrapContentHeight()
                .background(
                    color = Color(0xFFFFFFFF),
                    shape = RoundedCornerShape(12.dp)
                )
                .clip(RoundedCornerShape(12.dp))
        ) {
            Box(modifier = Modifier.height(230.dp))
        }
        Box(modifier = Modifier.height(12.dp))
    }
}

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalLayoutApi::class)
@Composable
fun RecipeItemOverview(
    modifier: Modifier = Modifier,
    recipeItem: Recipe,
    onClick: ((item: Recipe) -> Unit)? = null
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(
                color = White,
                shape = RoundedCornerShape(12.dp)
            )
            .border(
                width = 1.dp,
                color = SilverSand,
                shape = RoundedCornerShape(12.dp)
            )
            .clip(RoundedCornerShape(12.dp))
            .clickable(enabled = true) {
                onClick?.invoke(recipeItem)
            }
    ) {
        RecipeItemImage(recipeItem = recipeItem, height = 130)
        Column(
            verticalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier
                .testTag(tag = "recipe-item-description-container")
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(10.dp)
        ) {
            Text(
                text = recipeItem.name,
                style = TextStyle(
                    color = DarkLiver,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontStyle = FontStyle.Normal,
                    fontFamily = FontFamily
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            RecipeItemTags(recipeItem = recipeItem)
            RecipeItemIngredients(isOverView = true, recipeItem = recipeItem)
            RecipeItemNutritionFactsDescription(isOverView = true, recipeItem = recipeItem)
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalLayoutApi::class)
@Composable
fun RecipeItem(modifier: Modifier = Modifier, recipeItem: Recipe) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(color = White)
    ) {
        item { RecipeItemImage(recipeItem = recipeItem, height = 250) }
        item {
            Column(
                verticalArrangement = Arrangement.spacedBy(6.dp),
                modifier = Modifier
                    .testTag(tag = "recipe-item-description-container")
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(10.dp)
            ) {
                Text(
                    text = recipeItem.name,
                    style = TextStyle(
                        color = DarkLiver,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontStyle = FontStyle.Normal,
                        fontFamily = FontFamily
                    )
                )
                RecipeItemTags(recipeItem = recipeItem)
                RecipeItemIngredients(
                    title = "Ingredients",
                    isOverView = false,
                    recipeItem = recipeItem
                )
                RecipeItemInstructions(recipeItem = recipeItem)
                RecipeItemNutritionFactsDescription(isOverView = false, recipeItem = recipeItem)
            }
        }
        item {
            Button(
                onClick = {
                    val intent = ShareCompat.IntentBuilder(BillDorApplication.getContext())
                        .setType("text/plain")
                        .setText("https://dummyjson.com/docs/recipes")
                        .createChooserIntent()
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    BillDorApplication.getContext().startActivity(intent)
                },
                shape = RoundedCornerShape(99.dp),
                border = BorderStroke(width = 1.dp, color = DarkLiver),
                colors = ButtonDefaults.buttonColors(
                    contentColor = DarkLiver,
                    containerColor = White
                ),
                modifier = Modifier
                    .testTag(tag = "recipe-item-button-share")
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(
                        bottom = 12.dp,
                        start = 10.dp,
                        end = 10.dp
                    )
            ) {
                Icon(imageVector = Icons.Filled.Share, contentDescription = "Share")
                Text(
                    text = "Share this Recipe",
                    style = TextStyle(
                        color = DarkLiver,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontStyle = FontStyle.Normal,
                        fontFamily = FontFamily
                    ),
                    modifier = Modifier.padding(start = 12.dp)
                )
            }
        }
    }
}
