package com.idea.billdor.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.idea.billdor.R
import com.idea.billdor.ui.main.MainActivity
import com.idea.billdor.ui.theme.BillDorTheme
import com.idea.billdor.ui.theme.CoolWhite
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@SuppressLint("CustomSplashScreen")
class SplashActivity : ComponentActivity(), Runnable {

    companion object {
        const val SPLASH_DELAY = 2000L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { BillDorTheme { InternalSplashScreenBuilder() } }
        Handler(Looper.getMainLooper()).postDelayed(this, SPLASH_DELAY)
    }

    @Composable
    private fun InternalSplashScreenBuilder() {
        Surface(color = CoolWhite) {
            Box(modifier = Modifier.fillMaxSize()) {
                Image(
                    painter = painterResource(id = R.drawable.billdor_logo_detailed),
                    contentDescription = "BillDor Logo",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(250.dp)
                        .align(Alignment.Center)
                )
            }
        }
    }

    override fun run() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
