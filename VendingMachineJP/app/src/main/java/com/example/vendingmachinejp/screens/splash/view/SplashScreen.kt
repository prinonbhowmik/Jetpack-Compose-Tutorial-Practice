package com.example.vendingmachinejp.screens.splash.view

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.vendingmachinejp.models.Media
import com.example.vendingmachinejp.navigation.Screen
import com.example.vendingmachinejp.utils.DataStorePref
import com.example.vendingmachinejp.screens.splash.viewmodel.SplashViewModel
import com.google.gson.Gson


@Composable
fun SplashScreen(navController: NavController,viewModel: SplashViewModel = hiltViewModel()) {


    val adData = viewModel.adData
    val isLoading = viewModel.isLoading
    val error = viewModel.errorMessage
    val context = LocalContext.current

    var adList =  mutableListOf<Media>()

    LaunchedEffect(Unit) {

        val pref = DataStorePref(context)

        if (pref.kioskAdded()){
            viewModel.getAdList(
                "c0d8c6f8045c45c68e7e159de76f4067",
                "5fc889aee02448afa4b9af5f766c5b74",
                "afa32ac3-7faf-4048-b7e2-3be079f2d04a",
                "8F040955-8038-49D7-93E1-6A9C3B4F9EEC",
                30.toString(),1.toString()
            )
        }else{
            navController.navigate(Screen.addKiosk.route){
                popUpTo(navController.graph.id){
                    inclusive = true
                }
            }
        }


    }

    when{
        isLoading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = Color.Red)
            }
        }

        error != null -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = error, color = Color.Red)
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = { viewModel.getAdList(
                    "c0d8c6f8045c45c68e7e159de76f4067",
                    "5fc889aee02448afa4b9af5f766c5b74",
                    "afa32ac3-7faf-4048-b7e2-3be079f2d04a",
                    "8F040955-8038-49D7-93E1-6A9C3B4F9EEC",
                    30.toString(),1.toString()
                )}) {
                    Text("Retry")
                }
            }
        }

        adData != null -> {
            Log.d("AdList", "SplashScreen: ${Gson().toJson(adData.data?.data)}")

            
        }
    }


}

