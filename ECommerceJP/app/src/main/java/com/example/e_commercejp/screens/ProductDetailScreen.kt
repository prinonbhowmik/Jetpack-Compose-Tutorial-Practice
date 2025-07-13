package com.example.e_commercejp.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.example.e_commercejp.viewmodel.ProductViewModel
import com.google.gson.Gson

@Composable
fun ProductDetailScreen(productId: Int, viewModel: ProductViewModel = hiltViewModel()) {
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

    }else{
        val product = products.find { it.id == productId }
        Log.d("CheckData", "ProductDetailScreen: ${productId}")

        product?.let {
            Column(modifier = Modifier.padding(16.dp)) {
                Image(
                    painter = rememberImagePainter(product.image),
                    contentDescription = product.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(product.title, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(product.description)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Price: $${product.price}", fontWeight = FontWeight.Bold)
            }
        }
    }


}
