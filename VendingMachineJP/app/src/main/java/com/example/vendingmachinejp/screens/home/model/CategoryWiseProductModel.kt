package com.example.vendingmachinejp.screens.home.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import java.io.Serializable

@Keep
data class CategoryWiseProductModel(
    @SerializedName("Data") val `data`: List<Data>,
    @SerializedName("IsSuccess") val isSuccess: Boolean?,
    @SerializedName("StatusCode") val statusCode: Int?,
    @SerializedName("ErrorMessage") val errorMessage: Any?,
    @SerializedName("PropertyName") val propertyName: Any?,
    @SerializedName("ValidationErrors") val validationErrors: Any?
) : Serializable

data class Data(
    @SerializedName("CategoryId") val categoryId: String?,
    @SerializedName("CategoryName") var categoryName: String?,
    @SerializedName("CategoryDescription") val categoryDescription: String?,
    @SerializedName("CategoryMedias") val categoryMedias: List<CategoryMedia?>?,
    @SerializedName("CategoryProducts") val categoryProducts: List<CategoryProduct>,
    @SerializedName("CategorySortOrder") val categorySortOrder: Int?,
    @SerializedName("IsForQrCodeProduct") val isForQrCodeProduct: Boolean?,
    var isSelected: Boolean = false
) : Serializable

data class CategoryMedia(
    @SerializedName("FileId") val fileId: String?,
    @SerializedName("ContentDuration") val contentDuration: Int?,
    @SerializedName("FileUrl") val fileUrl: String?,
    @SerializedName("MediaType") val mediaType: String?,
    @SerializedName("ThumbnailFileId") val thumbnailFileId: String?,
    @SerializedName("ThumbnailFileUrl") val thumbnailFileUrl: String?,
    @SerializedName("FileUploadDate") val fileUploadDate: String?,
    @SerializedName("FileModifiedDate") val fileModifiedDate: String?,
    @SerializedName("FileName") val fileName: Any?,
    @SerializedName("MediaLength") val mediaLength: Int?
) : Serializable

data class CategoryProduct(
    @SerializedName("ProductId") val productId: String?,
    @SerializedName("ProductName") val productName: String?,
    @SerializedName("NormalPrice") val normalPrice: Double?,
    @SerializedName("Ingredients") val ingredients: String?,
    @SerializedName("Medias") val medias: List<Media?>?,
    @SerializedName("ServingVariations") val servingVariations: List<ServingVariation?>?,
    @SerializedName("DiscountValue") val discountValue: List<Any?>?,
    @SerializedName("DiscountPrice") val discountPrice: Double?,
    @SerializedName("ProductSortOrder") val productSortOrder: Int?,
    @SerializedName("HasModifierWithoutZeroPrice") val hasModifierWithoutZeroPrice: Boolean?,
    @SerializedName("IsProductAvailable") val isProductAvailable: Boolean?,
    @SerializedName("LongDescription") val longDescription: Any?,
    @SerializedName("PriceLevelInfoList") val priceLevelInfoList: List<Any?>?,
    @SerializedName("VendingMachineSlotDetails") val vendingMachineSlotDetails: List<VendingMachineSlotDetailsItem?>?,
    @SerializedName("Taxes") val taxes : List<TaxesItem>,
    var isAdded :Boolean = false,
    var currentCount:Int = 0,
) : Serializable

@Keep
data class VendingMachineSlotDetailsItem(
    @SerializedName("SlotNumber") val slotNumber: Int?,
    @SerializedName("SlotId") val slotId: String?,
    @SerializedName("SlotItemCapacity") val slotItemCapacity: Int?,
    @SerializedName("FilledItemCount") val filledItemCount: Int?
) : Serializable


data class Media(
    @SerializedName("FileId") val fileId: String?,
    @SerializedName("FileUrl") val fileUrl: String?,
    @SerializedName("ThumbnailFileId") val thumbnailFileId: String?,
    @SerializedName("ThumbnailFileUrl") val thumbnailFileUrl: String?
) : Serializable

data class ServingVariation(
    @SerializedName("Type") val type: String?,
    @SerializedName("IsEnabled") val isEnabled: Boolean?,
    @SerializedName("IsDefault") val isDefault: Boolean?
) : Serializable

@Keep
data class TaxesItem(
    @SerializedName("IsActive") val isActive: Boolean?,
    @SerializedName("IsForQrCodeProduct") val isForQrCodeProduct: Boolean?,
    @SerializedName("ItemId") val itemId: String?,
    @SerializedName("Name") val name: String?,
    @SerializedName("Rate") val rate: Double?,
    @SerializedName("TaxType") val taxType: String?,
    @SerializedName("ThirdPartyRefId") val thirdPartyRefId: String?
)
