package com.example.vendingmachinejp.screens.splash.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vendingmachinejp.models.AdListModel
import com.example.vendingmachinejp.repository.HomeRepository
import com.example.vendingmachinejp.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val repository: HomeRepository): ViewModel(){

    var adData by mutableStateOf<AdListModel?>(null)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    var isLoading by mutableStateOf(false)
        private set

    fun getAdList(apiKey:String,
                            branchId:String,
                            organizationId:String,
                            tenantId:String,
                            pageSize: String,
                            pageNumber: String,) {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            when (val result = repository.getAdList(apiKey, branchId, organizationId, tenantId, pageSize, pageNumber)) {
                is DataState.Success -> adData = result.data
                is DataState.Error -> errorMessage = result.message
                is DataState.Loading<*> -> isLoading
            }
            isLoading = false
        }
    }
}