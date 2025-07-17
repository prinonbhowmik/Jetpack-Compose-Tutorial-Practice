package com.example.vendingmachinejp.screens.splash.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class AdListModel(
    @SerializedName("Data") val `data`: AdData?,
    @SerializedName("ErrorMessage") val errorMessage: Any?,
    @SerializedName("IsSuccess") val isSuccess: Boolean?,
    @SerializedName("PropertyName") val propertyName: Any?,
    @SerializedName("StatusCode") val statusCode: Int?,
    @SerializedName("ValidationErrors") val validationErrors: Any?
)

@Keep
data class AdData(
    @SerializedName("Data") val `data`: List<AdListData?>?,
    @SerializedName("PageNumber") val pageNumber: Int?,
    @SerializedName("PageSize") val pageSize: Int?,
    @SerializedName("TotalData") val totalData: Int?
)

@Keep
data class AdListData(
    @SerializedName("Devices") val devices: List<Any?>?,
    @SerializedName("EndingDate") val endingDate: String?,
    @SerializedName("IdleTime") val idleTime: Int?,
    @SerializedName("IsActive") val isActive: Boolean?,
    @SerializedName("IsDateRangeEnabled") val isDateRangeEnabled: Boolean?,
    @SerializedName("ItemId") val itemId: String?,
    @SerializedName("LastUpdateDate") val lastUpdateDate: String?,
    @SerializedName("Medias") val medias: List<Media?>?,
    @SerializedName("Schedule") val schedule: List<Any?>?,
    @SerializedName("StartingDate") val startingDate: String?,
    @SerializedName("TimeInterval") val timeInterval: Int?
)

@Keep
data class Media(
    @SerializedName("ContentDuration") val contentDuration: Int?,
    @SerializedName("DisplaySection") val displaySection: String?,
    @SerializedName("FileId") val fileId: String?,
    @SerializedName("FileModifiedDate") val fileModifiedDate: String?,
    @SerializedName("FileName") val fileName: Any?,
    @SerializedName("FileUploadDate") val fileUploadDate: String?,
    @SerializedName("FileUrl") val fileUrl: String?,
    @SerializedName("MediaLength") val mediaLength: Int?,
    @SerializedName("MediaType") val mediaType: String?,
    @SerializedName("ThumbnailFileId") val thumbnailFileId: Any?,
    @SerializedName("ThumbnailFileUrl") val thumbnailFileUrl: Any?,
)