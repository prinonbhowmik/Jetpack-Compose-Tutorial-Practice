package com.example.vendingmacinejp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vendingmacinejp.repository.HomeRepository
import com.example.vendingmacinejp.ui.model.AdListData
import com.example.vendingmacinejp.ui.model.AdListModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HomeVM @Inject constructor (private val repository: HomeRepository) : ViewModel()  {


    var isLoading by mutableStateOf(false)
        private set


    var getAdListResponse by mutableStateOf<AdListModel?>(null)
        private set

    fun getAdList(
        apiKey:String,
        branchId:String,
        organizationId:String,
        tenantId:String,
        pageSize: String,
        pageNumber: String,
    ) = viewModelScope.launch {
//        _getAdListResponse.value = repository.getAdList(apiKey, branchId, organizationId, tenantId,pageSize, pageNumber)
        isLoading = true

        try {
            getAdListResponse = repository.getAdList(
                apiKey, branchId, organizationId, tenantId,pageSize, pageNumber
               )
        }catch (e: Exception){

        }
    }



}