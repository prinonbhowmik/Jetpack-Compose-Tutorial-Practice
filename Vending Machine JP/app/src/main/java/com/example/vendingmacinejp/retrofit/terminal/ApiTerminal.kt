package com.example.ordermonkey.retrofit.terminal

import com.example.ordermonkey.base.AppConstants
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiTerminal {

    @POST(AppConstants.ENDPOINT_CREATE_PAYMENT)
    suspend fun createPayment(
        @Header("ApiKey") apiKey:String,
        @Header("BranchId") branchId:String,
        @Header("OrganizationId") organizationId:String,
        @Header("TenantId") tenantId:String,
        @Body body: RequestBody
    ): Response<ResponseBody>
}