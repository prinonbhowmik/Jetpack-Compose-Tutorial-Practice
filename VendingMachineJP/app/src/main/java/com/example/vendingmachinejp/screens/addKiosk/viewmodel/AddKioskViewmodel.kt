package com.example.vendingmachinejp.screens.addKiosk.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vendingmachinejp.repository.HomeRepository
import com.example.vendingmachinejp.screens.addKiosk.model.AddKioskModel
import com.example.vendingmachinejp.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.RequestBody
import javax.inject.Inject

@HiltViewModel
class AddKioskViewmodel @Inject constructor(private val repository: HomeRepository) : ViewModel() {
    var adData by mutableStateOf<AddKioskModel?>(null)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    var isLoading by mutableStateOf(false)
        private set

    fun getAddKiosk(body: RequestBody) {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            when (val result = repository.getAddKiosk(body)) {
                is DataState.Success -> adData = result.data
                is DataState.Error -> errorMessage = result.message
                is DataState.Loading<*> -> isLoading
            }
            isLoading = false
        }
    }
}