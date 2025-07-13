package com.example.vendingmacinejp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.vendingmacinejp.ui.screens.SplashScreen

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
}

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController, startDestination = Screen.Splash.route){
        composable(Screen.Splash.route) {
            SplashScreen(navController)
        }
    }
}