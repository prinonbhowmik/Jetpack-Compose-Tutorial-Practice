package com.example.vendingmachinejp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.vendingmachinejp.screens.addKiosk.view.AddKioskScreen
import com.example.vendingmachinejp.screens.splash.view.SplashScreen

sealed class Screen(val route: String) {
    object splash : Screen("splash")
    object addKiosk : Screen("addKiosk")
}

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController, startDestination = Screen.splash.route) {
        composable(Screen.splash.route) {
            SplashScreen(navController)
        }
        composable(Screen.addKiosk.route) { backStackEntry ->

            AddKioskScreen(navController)
        }

    }
}