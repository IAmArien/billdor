package com.idea.billdor.ui.main.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.idea.billdor.ui.theme.CoolWhite
import com.idea.billdor.ui.theme.DarkLiver
import com.idea.billdor.ui.theme.FontFamily

@Composable
fun MyProfileScreen() {
    Box(
        modifier = Modifier
            .testTag(tag = "my-profile-screen-container")
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = CoolWhite)
    ) {
        Text(
            text = "This is just a dummy screen for Navigation",
            style = TextStyle(
                color = DarkLiver,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                fontStyle = FontStyle.Normal,
                fontFamily = FontFamily
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .testTag(tag = "my-profile-screen-label")
                .align(Alignment.Center)
        )
    }
}
