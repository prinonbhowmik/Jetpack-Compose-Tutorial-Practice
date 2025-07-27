package com.example.ordermonkey.roomDB.repository

import androidx.lifecycle.LiveData
import com.example.vendingmachinejp.roomDB.dao.ProductDAO
import com.example.vendingmachinejp.roomDB.model.ProductCartModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductRepository @Inject constructor(private val productDAO: ProductDAO) {

    fun getAllProducts(): Flow<List<ProductCartModel>> = productDAO.getAllProducts()

    suspend fun insertUser(product: ProductCartModel) {
        productDAO.insertUser(product)
    }

    fun updateQuantity(productId: String) {
        productDAO.updateQuantity(productId)
    }

    fun decreaseQuantity(productId: String) {
        productDAO.decreaseQuantity(productId)
    }

    fun deleteUser(product: ProductCartModel) {
        productDAO.deleteUser(product)
    }

    fun deleteAll() {
        productDAO.deleteAll()
    }

}