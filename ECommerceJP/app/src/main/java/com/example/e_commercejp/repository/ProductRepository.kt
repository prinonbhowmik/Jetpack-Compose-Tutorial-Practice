package com.example.e_commercejp.repository

import com.example.e_commercejp.data.ApiService
import com.example.e_commercejp.data.Product
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getProducts(): List<Product> = apiService.getProducts()
}