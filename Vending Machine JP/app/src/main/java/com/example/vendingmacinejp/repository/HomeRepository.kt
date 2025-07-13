package com.example.vendingmacinejp.repository

import com.example.ordermonkey.retrofit.home.ApiInterface
import com.example.ordermonkey.retrofit.terminal.ApiTerminal
import okhttp3.RequestBody
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val api: ApiInterface,
    private val terminal: ApiTerminal
) {


    suspend fun getAddKiosk(
        body: RequestBody
    ) = api.addKiosk(
        body
    )

    suspend fun getTranslation(
        apiKey: String,
        moduleName: String,
        language: String,
    ) = api.getTranslation(
        apiKey, moduleName, language
    )

    suspend fun getAdList(
        apiKey: String,
        branchId: String,
        organizationId: String,
        tenantId: String,
        pageSize: String,
        pageNumber: String,
    ) = api.getAdList(
        apiKey, branchId, organizationId, tenantId, pageSize, pageNumber
    )


    // Home
    suspend fun getHome(
        apiKey: String,
        branchId: String,
        organizationId: String,
        tenantId: String,
        type: String,
        productMediaRequest: String,
        isVendingMachine: Boolean,
        /*branchIdQuery:String,
        OrganizationIdQuery:String,*/
    ) =
        api.getHome(
            apiKey, branchId, organizationId, tenantId, type, productMediaRequest, isVendingMachine
        )


    suspend fun getSlots(
        apiKey: String,
        organizationId: String,
        tenantId: String,
        branchIdQuery: String,
        organizationIdQuery: String,
        tenantIdQuery: String,
    ) =
        api.getProductSlots(
            apiKey, organizationId, tenantId, branchIdQuery, organizationIdQuery, tenantIdQuery
        )


    suspend fun getCreateOrder(
        apiKey: String,
        branchId: String,
        organizationId: String,
        tenantId: String,
        body: RequestBody
    ) =
        api.createOrder(
            apiKey, branchId, organizationId, tenantId, body
        )

    suspend fun getCreatePayment(
        apiKey: String,
        branchId: String,
        organizationId: String,
        tenantId: String,
        body: RequestBody
    ) =
        terminal.createPayment(
            apiKey, branchId, organizationId, tenantId, body
        )


    suspend fun getSupportInfo(
        apiKey: String,
        branchId: String,
        organizationId: String,
        tenantId: String,
    ) =
        api.getSupportContact(
            apiKey, branchId, organizationId, tenantId
        )

}