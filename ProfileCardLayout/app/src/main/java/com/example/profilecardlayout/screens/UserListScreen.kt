package com.example.profilecardlayout.screens

import android.net.Uri
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.profilecardlayout.UserProfile
import com.example.profilecardlayout.screens.screenComponents.AppBar
import com.example.profilecardlayout.screens.screenComponents.ProfileCard
import com.google.gson.Gson

@Composable
fun UserListScreen(userProfiles: List<UserProfile>, navController: NavHostController?) {
    Scaffold(topBar = { AppBar("Friend List",icon = Icons.Default.Home){}
    })
    { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            color = Color.White
        ) {

            LazyColumn {
                items(userProfiles) { userProfile ->
                    ProfileCard(userProfile = userProfile){
                        val userJson = Uri.encode(Gson().toJson(userProfile))
                        navController?.navigate("user_details/$userJson")
                    }
                }
            }

        }
    }
}