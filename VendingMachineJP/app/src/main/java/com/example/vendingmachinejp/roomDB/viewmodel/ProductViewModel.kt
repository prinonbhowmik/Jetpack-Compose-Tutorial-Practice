package com.example.ordermonkey.roomDB.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ordermonkey.roomDB.repository.ProductRepository
import com.example.vendingmachinejp.roomDB.dao.ProductDAO
import com.example.vendingmachinejp.roomDB.model.ProductCartModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProductViewModel @Inject constructor(private val productRepository: ProductRepository): ViewModel() {

//    val getAllProducts: LiveData<List<ProductCartModel>> = productRepository.getAllProducts

//    val getAllProducts : LiveData<List<ProductCartModel>> = productRepository.getAllProducts



    val products: StateFlow<List<ProductCartModel>> = productRepository.getAllProducts()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())



    fun insertUser(product: ProductCartModel) {
        viewModelScope.launch(Dispatchers.IO) {
            productRepository.insertUser(product)
        }
    }

    fun updateQuantity(productId: String) {
        viewModelScope.launch (Dispatchers.IO) {
            productRepository.updateQuantity(productId)
        }
    }

    fun decreaseQuantity(productId: String) {
        viewModelScope.launch (Dispatchers.IO) {
            productRepository.decreaseQuantity(productId)
        }
    }

    fun deleteUser(product: ProductCartModel) {
        viewModelScope.launch (Dispatchers.IO) {
            productRepository.deleteUser(product)
        }
    }

    fun deleteAll() {
        viewModelScope.launch (Dispatchers.IO) {
            productRepository.deleteAll()
        }
    }
    
}