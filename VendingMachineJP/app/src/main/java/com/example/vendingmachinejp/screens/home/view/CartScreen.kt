package com.example.vendingmachinejp.screens.home.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.ordermonkey.roomDB.viewmodel.ProductViewModel
import com.example.vendingmachinejp.R
import com.example.vendingmachinejp.roomDB.model.ProductCartModel
import com.example.vendingmachinejp.utils.HorizontalLine
import com.example.vendingmachinejp.utils.TextUtils

@Composable
fun CartScreen(
    cartList: List<ProductCartModel>,
    currency: String,
    langCode: String,
    productViewModel: ProductViewModel
) {

    var btnEnabled by remember { mutableStateOf(false) }
    val shouldShowDialog = remember { mutableStateOf(false) }
    val showDeleteDialog = remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf<ProductCartModel?>(null) }


    if (shouldShowDialog.value) {
        ProductDialog(
            shouldShowDialog = shouldShowDialog,
            "you can add maximum 2 products at a time."

        )
    }

    if (showDeleteDialog.value && selectedItem != null){
        DeleteDialog(
            shouldShowDialog = showDeleteDialog,
            "Do you really want to\n remove items?",
            confirmClick = {
                selectedItem?.let { productViewModel.deleteUser(it) }
            }
        )
    }


    Column(
        modifier = Modifier
            .width(125.dp)
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    )
    {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Black)
                .padding(
                    start = 8.dp,
                    end = 8.dp,
                    top = 4.dp,
                    bottom = 4.dp
                ),
            text = "Your Items",
            fontSize = 8.sp,
            color = TextUtils.hexToColor("#FFFFFF"),
            style = MaterialTheme.typography.titleLarge,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis

        )

        if (cartList.isEmpty()) {
            btnEnabled = false
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                AsyncImage(
                    model = R.drawable.empty,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(32.dp)
                        .height(32.dp)
                        .aspectRatio(1.1f)
                )
                Text(
                    modifier = Modifier.padding(
                        start = 8.dp,
                        end = 8.dp,
                        top = 4.dp,
                        bottom = 8.dp
                    ),
                    text = "Cart is Empty!",
                    fontSize = 9.sp,
                    color = TextUtils.hexToColor("#212121"),
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis

                )
                Text(
                    text = "You haven’t added anything to your cart!",
                    fontSize = 7.sp,
                    textAlign = TextAlign.Center,
                    color = TextUtils.hexToColor("#8a8a8a"),
                    style = MaterialTheme.typography.displaySmall,


                    )
            }
        } else {
            btnEnabled = true
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                itemsIndexed(
                    items = cartList
                ) { index, product ->
                    Column(modifier = Modifier.padding(2.dp)) {
                        Row(modifier = Modifier
                            .fillMaxSize()
                            .padding(2.dp)) {
                            AsyncImage(
                                model = product.productImage,
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .width(30.dp)
                                    .height(30.dp)
                                    .border(
                                        1.dp,
                                        TextUtils.hexToColor("#e5e4e3"),
                                        RoundedCornerShape(8.dp)
                                    )
                                    .padding(6.dp)
                            )

                            Column {
                                Text(
                                    modifier = Modifier.padding(
                                        start = 2.dp
                                    ),
                                    text = TextUtils.languageTextConvert(product.productName, "en"),
                                    fontSize = 7.sp,
                                    textAlign = TextAlign.Start,
                                    color = TextUtils.hexToColor("#212121"),
                                    style = MaterialTheme.typography.titleLarge,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Text(
                                    modifier = Modifier.padding(
                                        start = 2.dp
                                    ),
                                    text = TextUtils.languageTextConvert(
                                        product.variation,
                                        langCode
                                    ),
                                    fontSize = 6.sp,
                                    textAlign = TextAlign.Start,
                                    color = TextUtils.hexToColor("#8a8a8a"),
                                    style = MaterialTheme.typography.bodyMedium,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Text(
                                    modifier = Modifier.padding(
                                        start = 2.dp
                                    ),
                                    text = "$currency ${
                                        String.format(
                                            "%.2f",
                                            product.actualPrice
                                        )
                                    }",
                                    fontSize = 7.sp,
                                    textAlign = TextAlign.Start,
                                    color = TextUtils.hexToColor("#212121"),
                                    style = MaterialTheme.typography.titleLarge,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 2.dp, end = 2.dp)
                                .border(
                                    1.dp,
                                    TextUtils.hexToColor("#e5e4e3"),
                                    RoundedCornerShape(4.dp)
                                ),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            if (product.quantity > 1) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_minus_red),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(18.dp)
                                        .padding(4.dp)
                                        .clickable {
                                            productViewModel.decreaseQuantity(product.productId)
                                        }
                                )
                            } else {
                                Image(
                                    painter = painterResource(id = R.drawable.delete_red),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(18.dp)
                                        .padding(4.dp)
                                        .clickable {
                                            selectedItem = product
                                            showDeleteDialog.value = true

                                        }
                                )
                            }
                            Text(
                                text = product.quantity.toString(),
                                fontSize = 7.sp,

                                color = TextUtils.hexToColor("#212121"),
                                style = MaterialTheme.typography.titleLarge,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )

                            Image(
                                painter = painterResource(id = R.drawable.ic_plus_red),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(18.dp)
                                    .padding(4.dp)
                                    .clickable {
                                        if (cartList.sumOf { it.quantity } < 2) {
                                            productViewModel.updateQuantity(product.productId)
                                        }
                                        else{
                                            shouldShowDialog.value = true
                                        }
                                    }
                            )
                        }
                    }
                }

            }
        }

        HorizontalLine(TextUtils.hexToColor("#E5E4E3"), 1.dp, Modifier.fillMaxWidth())

        Text(
            modifier = Modifier.padding(
                start = 8.dp,
                end = 8.dp,
                top = 8.dp,
                bottom = 4.dp
            ),
            text = "$currency ${cartList.sumOf { it.actualPrice }}",
            fontSize = 11.sp,
            textAlign = TextAlign.Center,
            color = TextUtils.hexToColor("#000000"),
            style = MaterialTheme.typography.titleLarge,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            modifier = Modifier.padding(
                start = 8.dp,
                end = 8.dp,
                bottom = 4.dp
            ),
            text = "(Vat Included)",
            textAlign = TextAlign.Center,
            fontSize = 6.sp,
            color = TextUtils.hexToColor("#4D4D4C"),
            style = MaterialTheme.typography.bodyMedium,

            )

        Button(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 8.dp,
                    end = 8.dp,
                    top = 8.dp,
                    bottom = 4.dp
                ),

            colors = if (btnEnabled) {
                ButtonDefaults.buttonColors(
                    containerColor = Color.Red
                )
            } else {
                ButtonDefaults.buttonColors(
                    containerColor = TextUtils.hexToColor("#E5E4E3")
                )
            },
            shape = RoundedCornerShape(10.dp)


        ) {
            Text(
                text = "Pay Now",
                color = Color.White,
                style = MaterialTheme.typography.titleLarge,
                fontSize = 10.sp
            )
        }

        HorizontalLine(TextUtils.hexToColor("#E5E4E3"), 1.dp, Modifier.fillMaxWidth())

        AsyncImage(
            model = R.drawable.order_monkey,
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .aspectRatio(1f / 1.5f)
        )
    }
}