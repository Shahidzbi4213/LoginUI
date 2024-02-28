package com.gulehri.loginui

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.gulehri.loginui.screen.OnBoardingScreen
import com.gulehri.loginui.screen.SplashScreen
import com.gulehri.loginui.ui.theme.LoginUITheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<SharedViewModel>()

    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                Color.TRANSPARENT, Color.TRANSPARENT
            ), navigationBarStyle = SystemBarStyle.light(
                Color.TRANSPARENT, Color.TRANSPARENT
            )
        )
        super.onCreate(savedInstanceState)
        setContent {
            LoginUITheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    if (viewModel.moveNow)
                         OnBoardingScreen()
                    else SplashScreen()

                   LaunchedEffect(key1 = Unit, block = {
                       delay(3000)
                       viewModel.updateMove()
                   })
                }
            }
        }
    }
}