package com.example.vendingmachinejp.screens.splash.view

import android.annotation.SuppressLint
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.vendingmachinejp.navigation.Screen
import com.example.vendingmachinejp.screens.splash.model.Language
import com.example.vendingmachinejp.screens.splash.viewmodel.SplashViewModel
import com.example.vendingmachinejp.screens.videoItems.model.ReelsVideoData
import com.example.vendingmachinejp.screens.videoItems.view.AutoPlayVideoLazyRow
import com.example.vendingmachinejp.utils.DataStorePref
import com.google.gson.Gson
import kotlinx.coroutines.launch


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SplashScreen(navController: NavController, viewModel: SplashViewModel = hiltViewModel()) {


    val adData = viewModel.adData
    val isLoading = viewModel.isLoading
    val error = viewModel.errorMessage
    val brandData = viewModel.brandInfoData
    val brandLoading = viewModel.isLoading
    val brandError = viewModel.errorMessage
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    var adList = mutableListOf<ReelsVideoData>()

    val pref = DataStorePref(context)

    LaunchedEffect(Unit) {

        if (pref.kioskAdded()) {
            coroutineScope.launch {
                pref.apply {
                    viewModel.getAdList(
                        getAPIKey(),
                        getBranchId(),
                        getOrgId(),
                        getTenantId(),
                        30.toString(), 1.toString()
                    )
                    viewModel.getBrandInfo(
                        getAPIKey(),
                        getBranchId(),
                        getOrgId(),
                        getTenantId(),)
                }
            }
        } else {
            navController.navigate(Screen.addKiosk.route) {
                popUpTo(navController.graph.id) {
                    inclusive = true
                }
            }
        }


    }

    when {
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
                Button(onClick = {
                    coroutineScope.launch {
                        pref.apply {
                            viewModel.getAdList(
                                getAPIKey(),
                                getBranchId(),
                                getOrgId(),
                                getTenantId(),
                                30.toString(), 1.toString()
                            )
                        }
                    }
                }) {
                    Text("Retry")
                }
            }
        }

        adData != null -> {
            Log.d("AdList", "SplashScreen: ${Gson().toJson(adData.data?.data)}")

            adData.data?.data?.forEach {
                it?.medias?.forEach { media ->
                    if (media?.displaySection == "SPLASH_SCREEN") {
                        adList.add(ReelsVideoData(media.fileUrl, media.mediaType))
                    }
                }
            }


            AutoPlayVideoLazyRow(adList, navController, customModifier = Modifier.fillMaxSize(),true)

        }
    }

    when {
        brandLoading -> {
            /*Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = Color.Red)
            }*/
        }

        brandError != null -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = brandError, color = Color.Red)
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = {
                    coroutineScope.launch {
                        pref.apply {
                            viewModel.getBrandInfo(
                                getAPIKey(),
                                getBranchId(),
                                getOrgId(),
                                getTenantId(),)
                        }
                    }
                }) {
                    Text("Retry")
                }
            }
        }

        brandData != null -> {
            Log.d("BrandInfo", "SplashScreen: ${Gson().toJson(brandData.data)}")

            if (brandData.isSuccess == true){
                coroutineScope.launch {
                    brandData.data.apply {
                        pref.let {
                            it.addSupportName(this?.supportName ?: "No Support assigned")
                            it.addSupportContact(this?.supportPhoneNumber ?: "No Contact assigned")
                            it.addCurrency(this?.currency.toString())
                            it.addCloudProvider(this?.cloudPaymentProvider?.providerName.toString())
                            it.addTerminalId(this?.cloudPaymentProvider?.terminalId.toString())
                            it.addLanguage(this?.defaultLanguage.toString())
                            this?.languageList?.let { list -> it.saveLangList(list ) }
                        }
                    }
                }
            }

        }
    }


}

