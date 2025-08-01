package com.example.vendingmachinejp.screens.home.view

import android.annotation.SuppressLint
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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.ordermonkey.roomDB.viewmodel.ProductViewModel
import com.example.vendingmachinejp.R
import com.example.vendingmachinejp.base.AppConstants
import com.example.vendingmachinejp.navigation.Screen
import com.example.vendingmachinejp.roomDB.model.ProductCartModel
import com.example.vendingmachinejp.screens.home.model.CategoryProduct
import com.example.vendingmachinejp.screens.home.model.Data
import com.example.vendingmachinejp.screens.splash.viewmodel.SplashViewModel
import com.example.vendingmachinejp.screens.videoItems.model.ReelsVideoData
import com.example.vendingmachinejp.screens.videoItems.view.AutoPlayVideoLazyRow
import com.example.vendingmachinejp.utils.DataStorePref
import com.example.vendingmachinejp.utils.TextUtils
import com.example.vendingmachinejp.utils.TextUtils.Companion.sdp
import com.example.vendingmachinejp.utils.TextUtils.Companion.sspTextUnit
import com.google.gson.Gson
import kotlinx.coroutines.launch


@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: SplashViewModel = hiltViewModel(),
    productViewModel: ProductViewModel = hiltViewModel()
) {
    val adData = viewModel.adData
    val productData = viewModel.categoryData
    val isLoading = viewModel.isLoading
    val productLoading = viewModel.productLoading
    val error = viewModel.errorMessage
    val productError = viewModel.productErrorMessage
    val context = LocalContext.current
    val pref = DataStorePref(context)
    val coroutineScope = rememberCoroutineScope()

    var adList = remember { mutableListOf<ReelsVideoData>() }
    var dataList = remember { mutableListOf<Data>() }



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

            ShowData(adList, dataList, navController,productViewModel)


        }
    }

    when {
        productLoading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = Color.Red)
            }
        }

        productError != null -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = productError, color = Color.Red)
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

            ShowData(adList, dataList, navController, productViewModel)

        }
    }

}

@Composable
private fun ShowData(
    adList: MutableList<ReelsVideoData>,
    dataList: MutableList<Data>,
    navController: NavHostController,
    productViewModel: ProductViewModel,
) {
    var selectedIndex by remember { mutableStateOf(0) }

    var selectedCatId by remember { mutableStateOf("") }

    val productList = remember(dataList, selectedIndex) {
        dataList.getOrNull(selectedIndex)?.categoryProducts ?: emptyList()
    }


    Column(modifier = Modifier.fillMaxSize()) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2f / 1.1f)
        )
        {
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

        )
        {
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            )
            {
                itemsIndexed(items = dataList) { index, product ->
                    selectedCatId = product.categoryId.toString()
                    Card(
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(dimensionResource(com.intuit.sdp.R.dimen._6sdp))
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
                        shape = RoundedCornerShape(dimensionResource(com.intuit.sdp.R.dimen._20sdp))
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(
                                    start = dimensionResource(com.intuit.sdp.R.dimen._9sdp),
                                    end = dimensionResource(com.intuit.sdp.R.dimen._9sdp),
                                    top = dimensionResource(com.intuit.sdp.R.dimen._2sdp),
                                    bottom = dimensionResource(com.intuit.sdp.R.dimen._2sdp)),
                            verticalAlignment = Alignment.CenterVertically
                            ) {

                            AsyncImage(
                                modifier = Modifier.size(dimensionResource(com.intuit.sdp.R.dimen._20sdp)),
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
                                    start = dimensionResource(com.intuit.sdp.R.dimen._2sdp),
                                    end = dimensionResource(com.intuit.sdp.R.dimen._2sdp),
                                ),
                                text = TextUtils.languageTextConvert(
                                    product.categoryName.toString(),
                                    "en"
                                ),
                                color = if (product.isSelected) TextUtils.hexToColor("#000000") else TextUtils.hexToColor(
                                    "#8a8a8a"
                                ),
                                fontSize = 6.sspTextUnit(),
                                style = MaterialTheme.typography.titleLarge
                            )
                        }
                    }
                }
            }
        }

        ProductView(productList,productViewModel,selectedCatId)
    }
}

@SuppressLint("DefaultLocale")
@Composable
fun ProductView(
    categoryProducts: List<CategoryProduct>,
    productViewModel: ProductViewModel,
    selectedCatId: String
) {
    Log.d("CheckProduct", "ProductView: ${Gson().toJson(categoryProducts)}")

    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val pref = DataStorePref(context)

    var currency by remember { mutableStateOf("") }
    var langCode by remember { mutableStateOf("") }

    val shouldShowDialog = remember { mutableStateOf(false) }

    val cartList by productViewModel.products.collectAsState()

    Log.d("CheckCartList", "${Gson().toJson(cartList)}" )

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            currency = pref.getCurrency()
            langCode = pref.getLanguage()

            Log.d("LaunchCgeck", "ProductView: ${pref.getCurrency()}")

        }
    }
    if (shouldShowDialog.value) {
        ProductDialog(
            shouldShowDialog = shouldShowDialog,
            "you can add maximum 2 products at a time."

        )
    }

    Box(modifier = Modifier.background(color = TextUtils.hexToColor("#FAF9F9"))) {
        Row(modifier = Modifier.fillMaxWidth()) {
            LazyVerticalGrid(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(
                        start = dimensionResource(com.intuit.sdp.R.dimen._5sdp),
                        end = dimensionResource(com.intuit.sdp.R.dimen._5sdp),
                        top = dimensionResource(com.intuit.sdp.R.dimen._2sdp),
                        bottom = dimensionResource(com.intuit.sdp.R.dimen._2sdp)
                    ),
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(4.dp)
            )
            {
                items(categoryProducts) { product ->
                    Card(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(dimensionResource(com.intuit.sdp.R.dimen._4sdp))
                            .border(
                                width = 1.dp,
                                color = TextUtils.hexToColor("#F4F3F2"),
                                shape = RoundedCornerShape(dimensionResource(com.intuit.sdp.R.dimen._4sdp))
                            )
                            .background(color = Color.White)
                            .clickable {
                                if (cartList.sumOf { it.quantity } < 2){
                                    if (cartList.any { it.productId == product.productId }){
                                        productViewModel.updateQuantity(product.productId.toString())
                                    }else{
                                        product.apply {
                                            productViewModel.insertUser(

                                                ProductCartModel(
                                                    0,
                                                    productId.toString(),
                                                    productName.toString(),
                                                    vendingMachineSlotDetails?.get(0)?.slotNumber ?: 0,
                                                    vendingMachineSlotDetails?.get(0)?.slotId ?: "",
                                                    discountPrice ?: 0.0,
                                                    discountPrice ?: 0.0,
                                                    normalPrice ?: 0.0,
                                                    1,
                                                    medias?.get(0)?.thumbnailFileUrl,
                                                    selectedCatId,
                                                    product.ingredients ?: "",
                                                    vendingMachineSlotDetails?.get(0)?.filledItemCount ?: 1,
                                                    0.0
                                                )
                                            )
                                        }
                                    }

                                }
                                else{
                                    shouldShowDialog.value = true

                                }
                            },
                        elevation = CardDefaults.cardElevation(2.dp)
                    )
                    {

                        Box (modifier = Modifier
                            .fillMaxSize()){
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(color = Color.White)
                                    .padding(4.dp)
                            )
                            {

                                Box {
                                    Card() {

                                    }
                                }

                                AsyncImage(
                                    model = product.medias?.get(0)?.thumbnailFileUrl,
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .aspectRatio(1.17f) // 1.17:1 aspect ratio
//                                  .clip(RoundedCornerShape(8.dp))
                                )

                                Text(
                                    modifier = Modifier.padding(
                                        start = 4.dp,
                                        top = 2.dp,
                                    ),
                                    text = if (product.productName?.contains("\"") == true)
                                        TextUtils.languageTextConvert(
                                            product.productName.toString(),
                                            langCode
                                        ) else product.productName.toString(),
                                    fontSize = 6.sspTextUnit(),
                                    color = TextUtils.hexToColor("#212121"),
                                    style = MaterialTheme.typography.titleLarge,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis

                                )

                                Text(
                                    modifier = Modifier.padding(
                                        start = 4.dp,
                                    ),
                                    text =
                                        if (product.ingredients?.contains("\"") == true)
                                            TextUtils.languageTextConvert(
                                                product.ingredients.toString(),
                                                langCode
                                            ) else product.ingredients.toString(),
                                    fontSize = 4.sspTextUnit(),
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = TextUtils.hexToColor("#8a8a8a")
                                )

                                Row(
                                    modifier = Modifier.padding(
                                        start = 0.dp,
                                    ),
                                ) {
                                    Text(
                                        modifier = Modifier.padding(
                                            start = 2.dp,
                                            top = 2.dp,
                                        ),
                                        text = "$currency ${
                                            String.format(
                                                "%.2f",
                                                product.discountPrice
                                            )
                                        }",
                                        fontSize = 5.sspTextUnit(),
                                        color = TextUtils.hexToColor("#212121"),
                                        style = MaterialTheme.typography.titleLarge,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                    if (product.normalPrice != product.discountPrice) {
                                        Box(
                                            modifier = Modifier.padding(
                                                start = 2.dp,
                                                top = 2.dp,
                                            ),
                                        ) {
                                            Text(
                                                text = "$currency ${
                                                    String.format(
                                                        "%.2f",
                                                        product.normalPrice
                                                    )
                                                }",
                                                fontSize = 9.sp,
                                                color = TextUtils.hexToColor("#4D4D4C"),
                                                style = MaterialTheme.typography.titleLarge,
                                                maxLines = 1,
                                                overflow = TextOverflow.Ellipsis
                                            )

                                        }
                                    }
                                }

                            }

                            if (cartList.isNotEmpty()){
                                if (cartList.any { it.productId == product.productId }){

                                    AsyncImage(
                                        model = R.drawable.selected_product,
                                        contentDescription = null,
                                        contentScale = ContentScale.Crop,
                                       modifier = Modifier
                                            .align(Alignment.TopEnd)
                                            .padding(4.dp)
                                           .width(14.dp)
                                           .height(14.dp)

                                    )



                                }
                            }
                        }

                    }
                }
            }

            CartScreen(cartList,currency,langCode,productViewModel)
        }
    }

}




