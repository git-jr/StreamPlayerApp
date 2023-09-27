package com.codandotv.streamplayerapp.feature_profile.profile.presentation.navigation

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.codandotv.streamplayerapp.core_navigation.routes.BottomNavRoutes.HOME
import com.codandotv.streamplayerapp.core_navigation.routes.BottomNavRoutes.PARAM.PROFILE_ID
import com.codandotv.streamplayerapp.core_navigation.routes.Routes
import com.codandotv.streamplayerapp.feature_profile.profile.di.ProfilePickerStreamModule
import com.codandotv.streamplayerapp.feature_profile.profile.presentation.screens.ProfilePickerStreamScreen
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

fun NavGraphBuilder.profilePickerStreamNavGraph(
    navController: NavHostController,
    onChangeThemeColors: (List<Pair<Color, String>>) -> Unit
) {
    composable(Routes.PROFILE_PICKER) { nav ->
        if (nav.getLifecycle().currentState == Lifecycle.State.STARTED) {
            loadKoinModules(ProfilePickerStreamModule.module)
        }
        ProfilePickerStreamScreen(
            onNavigateListStreams = { colors, profilePic ->
                navController.navigate("$HOME?$PROFILE_ID=$profilePic")
                onChangeThemeColors(colors)
            }
        ) {
            unloadKoinModules(ProfilePickerStreamModule.module)
        }
    }
}
