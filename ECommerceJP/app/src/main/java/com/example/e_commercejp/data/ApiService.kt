package com.example.e_commercejp.data

import com.example.e_commercejp.Resource
import com.example.e_commercejp.data.Product
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("products")
    suspend fun getProducts(): List<Product>
}