package com.example.vendingmacinejp.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.vendingmacinejp.viewmodel.HomeVM
import com.google.gson.Gson

@Composable
fun SplashScreen(navController: NavController,viewModel: HomeVM = hiltViewModel()) {
    val adData = viewModel.getAdListResponse
    val isLoading = viewModel.isLoading

    LaunchedEffect(Unit) {
        viewModel.getAdList(
            "c0d8c6f8045c45c68e7e159de76f4067",
            "5fc889aee02448afa4b9af5f766c5b74",
            "afa32ac3-7faf-4048-b7e2-3be079f2d04a",
            "8F040955-8038-49D7-93E1-6A9C3B4F9EEC",
            "30","0"
        )
    }

    if (isLoading){
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(color = Color.Red)
        }

    }else{
        Log.d("CheckData", "SplashScreen: ${Gson().toJson(adData)}")
    }
}