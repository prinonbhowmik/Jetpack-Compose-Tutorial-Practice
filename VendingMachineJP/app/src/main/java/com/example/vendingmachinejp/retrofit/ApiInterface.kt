package com.example.vendingmachinejp.retrofit

import com.example.vendingmachinejp.base.AppConstants
import com.example.vendingmachinejp.screens.splash.model.AdListModel
import com.example.vendingmachinejp.screens.addKiosk.model.AddKioskModel
import com.example.vendingmachinejp.screens.home.model.CategoryWiseProductModel
import com.example.vendingmachinejp.screens.splash.model.BrandInfoModel
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiInterface {

    @POST(AppConstants.ENDPOINT_ADD_KIOSK)
    suspend fun addKiosk(
        @Body body: RequestBody
    ): Response<AddKioskModel>

    @GET(AppConstants.ENDPOINT_GET_TRANSLATION)
    suspend fun getTranslation(
        @Header("ApiKey") apiKey:String,
        @Query("ModuleName") moduleName: String,
        @Query("Language") language: String,
    ): Response<ResponseBody>

    @GET(AppConstants.ENDPOINT_AD_LIST)
    suspend fun getAdList(
        @Header("ApiKey") apiKey:String,
        @Header("BranchId") branchId:String,
        @Header("OrganizationId") organizationId:String,
        @Header("TenantId") tenantId:String,
        @Query("pageSize") pageSize: String,
        @Query("pageNumber") pageNumber: String,
    ): Response<AdListModel>

    @GET(AppConstants.ENDPOINT_HOME)
    suspend fun getHome(
        @Header("ApiKey") apiKey:String,
        @Header("BranchId") branchId:String,
        @Header("OrganizationId") organizationId:String,
        @Header("TenantId") tenantId:String,
        @Query("Type") type: String,
        @Query("ProductMediaRequest") productMediaRequest: String,
        @Query("IsVendingMachine") isVendingMachine: Boolean,
        /*@Query("BranchId") branchIdQuery:String,
        @Query("OrganizationId") OrganizationIdQuery:String,*/
    ): Response<CategoryWiseProductModel>

    @GET(AppConstants.ENDPOINT_SLOTS)
    suspend fun getProductSlots(
        @Header("ApiKey") apiKey:String,
        @Header("OrganizationId") organizationId:String,
        @Header("TenantId") tenantId:String,
        @Query("BranchUUID") branchIdQuery:String,
        @Query("OrganizationId") organizationIdQuery:String,
        @Query("TenantId") tenantIdQuery:String,
    ): Response<ResponseBody>

    @POST(AppConstants.ENDPOINT_CREATE_ORDER)
    suspend fun createOrder(
        @Header("ApiKey") apiKey:String,
        @Header("BranchId") branchId:String,
        @Header("OrganizationId") organizationId:String,
        @Header("TenantId") tenantId:String,
        @Body body: RequestBody
    ): Response<ResponseBody>

   /* @POST(AppConstants.ENDPOINT_CREATE_PAYMENT)
    suspend fun createPayment(
        @Header("ApiKey") apiKey:String,
        @Header("BranchId") branchId:String,
        @Header("OrganizationId") organizationId:String,
        @Header("TenantId") tenantId:String,
        @Body body: RequestBody
    ): Response<ResponseBody>*/

    @GET(AppConstants.ENDPOINT_BRAND_INFO)
    suspend fun getSupportContact(
        @Header("ApiKey") apiKey:String,
        @Header("BranchId") branchId:String,
        @Header("OrganizationId") organizationId:String,
        @Header("TenantId") tenantId:String,
    ): Response<BrandInfoModel>
}