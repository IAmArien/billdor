package com.idea.billdor.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp

sealed class DividerOrientation {
    object Vertical : DividerOrientation()
    object Horizontal : DividerOrientation()
}

@Composable
fun Divider(
    modifier: Modifier = Modifier,
    orientation: DividerOrientation = DividerOrientation.Horizontal
) {
    val reusableModifier = if (orientation === DividerOrientation.Horizontal) {
        modifier.width(12.dp).testTag(tag = "divider-horizontal")
    } else {
        modifier.height(12.dp).testTag(tag = "divider-vertical")
    }
    Box(reusableModifier) {
        Text(text = "Test")
    }
}
