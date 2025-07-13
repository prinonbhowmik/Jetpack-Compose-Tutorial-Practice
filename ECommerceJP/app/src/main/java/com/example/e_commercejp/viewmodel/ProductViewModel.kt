package com.example.e_commercejp.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commercejp.Resource
import com.example.e_commercejp.data.Product
import com.example.e_commercejp.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {


    var products by mutableStateOf<List<Product>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    fun fetchProducts() {
        viewModelScope.launch {
//            _products.value = repository.getProducts()
            isLoading = true

            try {
                products = repository.getProducts()
            } catch (e: Exception) {
                Log.e("ProductViewModel", "Error: ${e.message}")
            } finally {
                isLoading = false
            }

        }
    }
}