package com.example.vendingmachinejp.screens.addKiosk.model


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class AddKioskModel(
    @SerializedName("Data") val `data`: AddKioskData?,
    @SerializedName("ErrorMessage") val errorMessage: Any?,
    @SerializedName("IsSuccess") val isSuccess: Boolean?,
    @SerializedName("PropertyName") val propertyName: Any?,
    @SerializedName("StatusCode") val statusCode: Int?,
    @SerializedName("ValidationErrors") val validationErrors: Any?
)

@Keep
data class AddKioskData(
    @SerializedName("ApiKey") val apiKey: String?,
    @SerializedName("BranchUUID") val branchUUID: String?,
    @SerializedName("OrganizationId") val organizationId: String?,
    @SerializedName("TenantId") val tenantId: String?
)