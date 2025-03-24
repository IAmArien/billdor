package com.idea.billdor.ui.splash

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.idea.billdor.ui.theme.White

@SuppressLint("CustomSplashScreen")
class SplashActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InternalSplashScreenBuilder()
        }
    }

    @Composable
    private fun InternalSplashScreenBuilder() {
        Surface(color = White) {
            Box(
                modifier = Modifier.Companion
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {

            }
        }
    }
}
