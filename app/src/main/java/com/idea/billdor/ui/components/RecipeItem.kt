package com.idea.billdor.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.idea.billdor.ui.theme.Black
import com.idea.billdor.ui.theme.DarkLiver
import com.idea.billdor.ui.theme.FontFamily
import com.idea.billdor.ui.theme.RollingStone
import com.idea.billdor.ui.theme.SelectiveYellow
import com.idea.billdor.ui.theme.SilverSand
import com.idea.billdor.ui.theme.White
import com.idea.core.recipes.data.Recipe

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun RecipeItem(
    modifier: Modifier = Modifier,
    recipeItem: Recipe,
    onClick: ((item: Recipe) -> Unit)? = null
) {
    Column(
        modifier = modifier
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
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp)
                .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
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
                    .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
                    .fillMaxWidth()
                    .height(130.dp)
            )
            Row(
                modifier = Modifier
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
        Column(
            verticalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier
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
            if (recipeItem.tags.isNotEmpty()) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier.wrapContentWidth(unbounded = false)
                ) {
                    recipeItem.tags.forEach { tagItem ->
                        Box(
                            modifier = Modifier
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
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 3.dp)
                            )
                        }
                    }
                }
            }
            Text(
                text = buildAnnotatedString {
                    recipeItem.ingredients.forEachIndexed { index, ingredientItem ->
                        if (index.plus(1) == recipeItem.ingredients.size) {
                            withStyle(
                                style = SpanStyle(
                                    color = RollingStone,
                                    fontSize = 12.sp,
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
                                    fontSize = 12.sp,
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
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Normal,
                    fontFamily = FontFamily
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(3.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                recipeItem.caloriesPerServing?.let { caloriesPerServing ->
                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = Black,
                                    fontSize = 12.sp,
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
                                    fontSize = 12.sp,
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
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            fontStyle = FontStyle.Normal,
                            fontFamily = FontFamily
                        ),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                recipeItem.cuisine?.let { cuisine->
                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = Black,
                                    fontSize = 12.sp,
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
                                    fontSize = 12.sp,
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
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            fontStyle = FontStyle.Normal,
                            fontFamily = FontFamily
                        ),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                recipeItem.difficulty?.let { difficulty ->
                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = Black,
                                    fontSize = 12.sp,
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
                                    fontSize = 12.sp,
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
                            fontSize = 12.sp,
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
}
