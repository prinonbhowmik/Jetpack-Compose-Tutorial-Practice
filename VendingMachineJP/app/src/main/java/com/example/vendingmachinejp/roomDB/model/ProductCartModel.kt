package com.example.vendingmachinejp.roomDB.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.vendingmachinejp.base.AppConstants

@Entity(tableName = AppConstants.PRODUCT_CART,
    indices = [
        Index(
            value = [
                "productId",
                "productName",
                "slotNo",
                "slotId",
                "presentPrice",
                "actualPrice",
                "pastPrice",
                "quantity",
                "productImage",
                "productCategoryId",
                "variation",
                "maxQuantity",
            ], unique = true
        )
    ]
    )
data class ProductCartModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val productId: String,
    val productName: String,
    val slotNo: Int,
    val slotId: String,
    val presentPrice: Double,
    val actualPrice: Double,
    val pastPrice: Double,
    var quantity: Int,
    val productImage:String?,
    val productCategoryId: String,
    val variation: String,
    val maxQuantity :Int,
    val tax: Double
)
