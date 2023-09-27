package com.codandotv.streamplayerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.codandotv.streamplayerapp.core_shared_ui.theme.StreamPlayerTheme
import com.codandotv.streamplayerapp.navigation.NavigationGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StreamPlayerApp()
        }
    }
}

@Composable
fun StreamPlayerApp() {
    var backgroundColor: Color? by remember {
        mutableStateOf(null)
    }
    var onBackgroundColor: Color? by remember {
        mutableStateOf(null)
    }

    var errorColor: Color? by remember {
        mutableStateOf(null)
    }

    var currentColorList by remember { mutableStateOf(listOf(Pair(Color.Unspecified, ""))) }


    LaunchedEffect(currentColorList) {
        if (currentColorList.size > 4) {
            backgroundColor = currentColorList[5].first // 5 is the index of vibrantSwatch
            onBackgroundColor = currentColorList[1].first // 1 is the index of darkVibrantSwatch
            errorColor = currentColorList[4].first // 4 is the index of mutedSwatch
        }
    }

    StreamPlayerTheme(
        backgroundColor = currentColorList[5].first, // 5 is the index of vibrantSwatch,
        onBackgroundColor = currentColorList[1].first, // 1 is the index of darkVibrantSwatch
        errorColor = currentColorList[4].first // 4 is the index of mutedSwatch
    ) {
        val navController = rememberNavController()
        NavigationGraph(
            navController = navController,
            onChangeThemeColors = {
                currentColorList = it
            }
        )
    }
}
