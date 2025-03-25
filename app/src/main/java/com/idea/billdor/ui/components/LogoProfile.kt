package com.idea.billdor.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.idea.billdor.R
import com.idea.billdor.ui.theme.SelectiveYellow

@Composable
fun LogoProfile(
    modifier: Modifier = Modifier,
    containerModifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
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
}
