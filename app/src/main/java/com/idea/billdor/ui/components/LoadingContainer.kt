package com.idea.billdor.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.idea.billdor.ui.theme.SelectiveYellow

@Composable
fun LoadingContainer(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .testTag(tag = "loading-container")
            .fillMaxWidth()
            .fillMaxHeight()
            .then(modifier)
    ) {
        CircularProgressIndicator(
            color = SelectiveYellow,
            modifier = Modifier
                .testTag(tag = "loading-container-circular-progress-indicator")
                .align(Alignment.Center)
        )
    }
}
