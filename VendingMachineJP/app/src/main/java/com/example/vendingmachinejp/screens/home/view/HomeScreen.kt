package com.example.vendingmachinejp.screens.home.view

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.vendingmachinejp.R
import com.example.vendingmachinejp.base.AppConstants
import com.example.vendingmachinejp.navigation.Screen
import com.example.vendingmachinejp.screens.home.model.CategoryProduct
import com.example.vendingmachinejp.screens.home.model.Data
import com.example.vendingmachinejp.screens.splash.viewmodel.SplashViewModel
import com.example.vendingmachinejp.screens.videoItems.model.ReelsVideoData
import com.example.vendingmachinejp.screens.videoItems.view.AutoPlayVideoLazyRow
import com.example.vendingmachinejp.utils.DataStorePref
import com.example.vendingmachinejp.utils.TextUtils
import com.google.gson.Gson
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavHostController, viewModel: SplashViewModel = hiltViewModel()) {
    val adData = viewModel.adData
    val productData = viewModel.categoryData
    val isLoading = viewModel.isLoading
    val productLoading = viewModel.productLoading
    val error = viewModel.errorMessage
    val productError = viewModel.productErrorMessage
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    var adList = remember { mutableListOf<ReelsVideoData>() }
    var dataList = remember { mutableListOf<Data>() }

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

                    viewModel.getHomeData(
                        getAPIKey(),
                        getBranchId(),
                        getOrgId(),
                        getTenantId(),
                        AppConstants.TAKEAWAY, "true", true
                    )
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
                    if (media?.displaySection == "HOME_SCREEN") {
                        adList.add(ReelsVideoData(media.fileUrl, media.mediaType))
                    }
                }
            }

            ShowData(adList, dataList, navController)


        }
    }

    when {
        productLoading -> {
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
                            viewModel.getHomeData(
                                getAPIKey(),
                                getBranchId(),
                                getOrgId(),
                                getTenantId(),
                                AppConstants.TAKEAWAY, "true", true
                            )
                        }
                    }
                }) {
                    Text("Retry")
                }
            }
        }

        productData != null -> {
            Log.d("ProductList", "HomeScreen: ${Gson().toJson(productData.data)}")

            dataList.clear()

            val allProducts = mutableListOf<CategoryProduct>()

            productData.data.forEach {
                allProducts.addAll(it.categoryProducts)
            }



            dataList.add(
                0,
                Data(
                    "0",
                    "{\"en\":\"All Items\",\"de\":\"Alle Artikel\",\"fr\":\"Tous les articles\",\"it\":\"Tutti gli elementi\"}",
                    "",
                    emptyList(),
                    allProducts,
                    0,
                    false,
                    false
                )
            )

            productData.data.let { dataList.addAll(it) }

            ShowData(adList, dataList, navController)

        }
    }

}

@Composable
private fun ShowData(
    adList: MutableList<ReelsVideoData>,
    dataList: MutableList<Data>,
    navController: NavHostController
) {
    var selectedIndex by remember { mutableStateOf(0) }

    var productList = remember { mutableStateListOf<CategoryProduct>() }



    Column(modifier = Modifier.fillMaxSize()) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        ) {
            AutoPlayVideoLazyRow(
                adList,
                navController,
                customModifier = Modifier
                    .fillMaxSize(),
                false
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(TextUtils.hexToColor("#F4F3F2"))

        ) {
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                itemsIndexed(items = dataList) { index, product ->
                    Card(
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(12.dp)
                            .clickable(
                                indication = null, // disables ripple animation
                                interactionSource = remember { MutableInteractionSource() }
                            ) {
                                selectedIndex = index



                            },
                        colors = CardDefaults.cardColors(
                            containerColor = if (index == selectedIndex) TextUtils.hexToColor("#FFFFFF") else TextUtils.hexToColor(
                                "#F4F3F2"
                            )
                        ),
                        shape = RoundedCornerShape(26.dp)
                    ) {
                        Row(modifier = Modifier.padding(8.dp)) {

                            AsyncImage(
                                modifier = Modifier.size(22.dp),
                                model =
                                    if (index == 0) R.drawable.all_items
                                    else product.categoryMedias?.getOrNull(
                                        0
                                    )?.thumbnailFileUrl,
                                contentDescription = "Category img",
                                contentScale = ContentScale.Crop
                            )

                            Text(
                                modifier = Modifier.padding(
                                    start = 8.dp,
                                    end = 8.dp,
                                    top = 3.dp,
                                    bottom = 3.dp
                                ),
                                text = TextUtils.languageTextConvert(
                                    product.categoryName.toString(),
                                    "en"
                                ),
                                color = if (product.isSelected) TextUtils.hexToColor("#000000") else TextUtils.hexToColor(
                                    "#8a8a8a"
                                ),
                                fontSize = 11.sp,
                                style = MaterialTheme.typography.titleLarge
                            )
                        }
                    }
                }
            }
        }

        ProductView(dataList[selectedIndex].categoryProducts)
    }
}

@Composable
fun ProductView(categoryProducts: List<CategoryProduct>) {

    Box(modifier = Modifier.background(color = TextUtils.hexToColor("#FAF9F9"))) {
        Row(modifier = Modifier.fillMaxSize()) {
            LazyVerticalGrid(
                modifier = Modifier.padding(
                    start = 6.dp,
                    end = 6.dp,
                    top = 2.dp,
                    bottom = 2.dp),
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(4.dp)
            ) { 
              items(categoryProducts) { product ->
                  Card(modifier = Modifier
                      .fillMaxSize()
                      .border(
                          width = 1.dp,
                          color = TextUtils.hexToColor("#F4F3F2"),
                          shape = RoundedCornerShape(6.dp)
                      )
                  ) {

                      Column(modifier = Modifier.fillMaxSize()) {
                          AsyncImage(
                              model = product.medias?.get(0)?.thumbnailFileUrl,
                              contentDescription = null,
                              contentScale = ContentScale.Crop,
                              modifier = Modifier
                                  .fillMaxWidth()
                                  .aspectRatio(1.17f) // 1.17:1 aspect ratio
                                  .clip(RoundedCornerShape(8.dp))
                          )

                          Text(
                              modifier = Modifier.padding(
                                  start = 4.dp,
                                  top = 2.dp,
                              ),
                              text = TextUtils.languageTextConvert(
                                  product.productName.toString(),
                                  "en"
                              ),
                              fontSize = 11.sp,
                              style = MaterialTheme.typography.titleLarge
                          )

                          Text(
                              modifier = Modifier.padding(
                                  start = 4.dp,
                                  top = 2.dp,
                                  ),
                              text = TextUtils.languageTextConvert(
                                  product.productName.toString(),
                                  "en"
                              ),
                              fontSize = 8.sp,
                              style = MaterialTheme.typography.bodyMedium,
                              color = TextUtils.hexToColor("#8a8a8a")
                          )
                      }

                  }
              }
            }
        }
    }

}

