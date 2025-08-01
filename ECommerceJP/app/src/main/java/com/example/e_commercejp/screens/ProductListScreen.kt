package com.example.e_commercejp.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.e_commercejp.Resource
import com.example.e_commercejp.data.Product
import com.example.e_commercejp.viewmodel.ProductViewModel


@Composable
fun ProductListScreen(navController: NavController, viewModel: ProductViewModel = hiltViewModel()) {

    val products = viewModel.products
    val isLoading = viewModel.isLoading


    LaunchedEffect(Unit) {
        viewModel.fetchProducts()
    }

    if (isLoading){
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(color = Color.Red)
        }
    }
    else{
        LazyColumn {
            items(products) { product ->
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable {
                        navController.navigate("product_detail/${product.id}")
                        Log.d("CheckData", "ProductListScreen: ${product.id}")
                    }
                ) {
                    Row(modifier = Modifier.padding(8.dp)) {
                        Image(
                            painter = rememberImagePainter(product.image),
                            contentDescription = product.title,
                            modifier = Modifier.size(60.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(product.title, fontWeight = FontWeight.Bold)
                            Text("$${product.price}")
                        }
                    }
                }
            }
        }
    }


}
