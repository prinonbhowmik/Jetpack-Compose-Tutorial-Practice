package com.example.vendingmachinejp.repository

import com.example.vendingmachinejp.screens.splash.model.AdListModel
import com.example.vendingmachinejp.retrofit.ApiInterface
import com.example.vendingmachinejp.retrofit.ApiTerminal
import com.example.vendingmachinejp.screens.addKiosk.model.AddKioskModel
import com.example.vendingmachinejp.screens.home.model.CategoryWiseProductModel
import com.example.vendingmachinejp.screens.splash.model.BrandInfoModel
import com.example.vendingmachinejp.utils.DataState
import okhttp3.RequestBody
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class HomeRepository @Inject constructor(private val api: ApiInterface, private val terminal: ApiTerminal)  {


    /*suspend fun getAddKiosk(
        body: RequestBody
    )  {
        api.addKiosk(
          body
        )
    }*/
    suspend fun  getAddKiosk(body: RequestBody): DataState<AddKioskModel>{
        return try {
            val response = api.addKiosk(body)
            if (response.isSuccessful && response.body() != null) {
                DataState.Success(response.body()!!)
            } else {
                DataState.Error("Error ${response.code()}: ${response.message()}")
            }
        } catch (e: IOException) {
            DataState.Error("Network error: ${e.message}")
        } catch (e: HttpException) {
            DataState.Error("HTTP error: ${e.message}")
        } catch (e: Exception) {
            DataState.Error("Unknown error: ${e.message}")
        }
    }

    suspend fun getTranslation(
        apiKey:String,
        moduleName: String,
        language: String,
    )  {
        api.getTranslation(
            apiKey, moduleName, language)

    }

    suspend fun getAdList(apiKey:String,
                          branchId:String,
                          organizationId:String,
                          tenantId:String,
                          pageSize: String,
                          pageNumber: String,): DataState<AdListModel> {
        return try {
            val response = api.getAdList(apiKey, branchId, organizationId, tenantId, pageSize, pageNumber)
            if (response.isSuccessful && response.body() != null) {
                DataState.Success(response.body()!!)
            } else {
                DataState.Error("Error ${response.code()}: ${response.message()}")
            }
        } catch (e: IOException) {
            DataState.Error("Network error: ${e.message}")
        } catch (e: HttpException) {
            DataState.Error("HTTP error: ${e.message}")
        } catch (e: Exception) {
            DataState.Error("Unknown error: ${e.message}")
        }
    }


    // Home
    suspend fun getHome(
        apiKey:String,
        branchId:String,
        organizationId:String,
        tenantId:String,
        type: String,
        productMediaRequest: String,
        isVendingMachine: Boolean,
    ) : DataState<CategoryWiseProductModel>  {
        return try {
            val response = api.getHome(apiKey, branchId, organizationId, tenantId, type, productMediaRequest,isVendingMachine)
            if (response.isSuccessful && response.body() != null) {
                DataState.Success(response.body()!!)
            } else {
                DataState.Error("Error ${response.code()}: ${response.message()}")
            }
        } catch (e: IOException) {
            DataState.Error("Network error: ${e.message}")
        } catch (e: HttpException) {
            DataState.Error("HTTP error: ${e.message}")
        } catch (e: Exception) {
            DataState.Error("Unknown error: ${e.message}")
        }
    }

    suspend fun getSlots(
        apiKey:String,
        organizationId:String,
        tenantId:String,
        branchIdQuery:String,
        organizationIdQuery:String,
        tenantIdQuery:String,
    )  {
        api.getProductSlots(
            apiKey, organizationId, tenantId, branchIdQuery, organizationIdQuery, tenantIdQuery
        )
    }

    suspend fun getCreateOrder(
        apiKey:String,
        branchId:String,
        organizationId:String,
        tenantId:String,
        body: RequestBody
    )  {
        api.createOrder(
            apiKey, branchId, organizationId, tenantId, body
        )
    }
    suspend fun getCreatePayment(
        apiKey:String,
        branchId:String,
        organizationId:String,
        tenantId:String,
        body: RequestBody
    )  {
        terminal.createPayment(
            apiKey, branchId, organizationId, tenantId, body
        )
    }

    suspend fun getSupportInfo(
        apiKey:String,
        branchId:String,
        organizationId:String,
        tenantId:String,
    ) : DataState<BrandInfoModel> {

        return try {
            val response = api.getSupportContact(
                apiKey, branchId, organizationId, tenantId
            )
            if (response.isSuccessful && response.body() != null) {
                DataState.Success(response.body()!!)
            } else {
                DataState.Error("Error ${response.code()}: ${response.message()}")
            }
        } catch (e: IOException) {
            DataState.Error("Network error: ${e.message}")
        } catch (e: HttpException) {
            DataState.Error("HTTP error: ${e.message}")
        } catch (e: Exception) {
            DataState.Error("Unknown error: ${e.message}")
        }
    }
}