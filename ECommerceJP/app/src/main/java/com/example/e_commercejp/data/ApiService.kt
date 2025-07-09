package com.example.e_commercejp.data

import com.example.e_commercejp.data.Product
import retrofit2.http.GET

interface ApiService {
    @GET("products")
    suspend fun getProducts(): List<Product>
}