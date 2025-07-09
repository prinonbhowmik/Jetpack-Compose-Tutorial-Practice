package com.example.profilecardlayout

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.profilecardlayout.screens.UserListScreen
import com.example.profilecardlayout.screens.UserProfileDetailsScreen
import com.google.gson.Gson

sealed class Screen(val route: String) {
    object UserList : Screen("user_list")
    object UserDetail : Screen("user_details/{user}")
}

@Composable
fun UserApplication(userProfiles: List<UserProfile> = userProfileList){
    val navController = rememberNavController()
    NavHost(navController= navController, startDestination = Screen.UserList.route){
        composable("user_list"){
            UserListScreen(userProfiles,navController)
        }
        composable("user_details/{user}") { backStackEntry ->
            val userJson = backStackEntry.arguments?.getString("user")
            val user = Gson().fromJson(userJson, UserProfile::class.java)
            UserProfileDetailsScreen(user,navController)
        }
    }
}