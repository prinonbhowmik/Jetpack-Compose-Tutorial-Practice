package com.example.vendingmachinejp.screens.splash.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class BrandInfoModel(
    @SerializedName("Data") val `data`: Data?,
    @SerializedName("ErrorMessage") val errorMessage: Any?,
    @SerializedName("IsSuccess") val isSuccess: Boolean?,
    @SerializedName("PropertyName") val propertyName: Any?,
    @SerializedName("StatusCode") val statusCode: Int?,
    @SerializedName("ValidationErrors") val validationErrors: Any?
)

@Keep
data class Data(
    @SerializedName("Address")
    val address: BrandAddress?,
    @SerializedName("AlternativeBuzzerName")
    val alternativeBuzzerName: String?,
    @SerializedName("AvailablePaymentMethods")
    val availablePaymentMethods: List<String?>?,
    @SerializedName("BranchUUID")
    val branchUUID: String?,
    @SerializedName("BuzzerKeyboardType")
    val buzzerKeyboardType: String?,
    @SerializedName("CloudPaymentProvider")
    val cloudPaymentProvider: CloudPaymentProvider?,
    @SerializedName("CompanyName")
    val companyName: Any?,
    @SerializedName("Currency")
    val currency: Any?,
    @SerializedName("CustomizePrint")
    val customizePrint: CustomizePrint?,
    @SerializedName("DefaultLanguage")
    val defaultLanguage: String?,
    @SerializedName("HasBarCodeSetup")
    val hasBarCodeSetup: Boolean?,
    @SerializedName("IsBackButtonEnabledForCart")
    val isBackButtonEnabledForCart: Boolean?,
    @SerializedName("IsBuzzerEnabled")
    val isBuzzerEnabled: Boolean?,
    @SerializedName("IsBuzzerEnabledForDineIn")
    val isBuzzerEnabledForDineIn: Boolean?,
    @SerializedName("IsBuzzerEnabledForTakeaway")
    val isBuzzerEnabledForTakeaway: Boolean?,
    @SerializedName("IsCashPaymentEnabled")
    val isCashPaymentEnabled: Boolean?,
    @SerializedName("IsDiscountAvailableForModifier")
    val isDiscountAvailableForModifier: Boolean?,
    @SerializedName("IsDiscountAvailableForNestedModifier")
    val isDiscountAvailableForNestedModifier: Boolean?,
    @SerializedName("IsDiscountAvailableForOption")
    val isDiscountAvailableForOption: Boolean?,
    @SerializedName("IsIntegratedWithDeliverect")
    val isIntegratedWithDeliverect: Boolean?,
    @SerializedName("IsIntegratedWithLightspeed")
    val isIntegratedWithLightspeed: Boolean?,
    @SerializedName("IsIntegratedWithTcpos")
    val isIntegratedWithTcpos: Boolean?,
    @SerializedName("IsMobileTipEnabled")
    val isMobileTipEnabled: Boolean?,
    @SerializedName("IsPreOrderAvailableForDineIn")
    val isPreOrderAvailableForDineIn: Boolean?,
    @SerializedName("IsPreOrderAvailableForTakeaway")
    val isPreOrderAvailableForTakeaway: Boolean?,
    @SerializedName("IsPullingEnabledForPrinting")
    val isPullingEnabledForPrinting: Boolean?,
    @SerializedName("IsStockEnabled")
    val isStockEnabled: Boolean?,
    @SerializedName("IsTipEnabled")
    val isTipEnabled: Boolean?,
    @SerializedName("IsWindowsKiosk")
    val isWindowsKiosk: Boolean?,
    @SerializedName("KioskPaymentInfo")
    val kioskPaymentInfo: List<Any?>?,
    @SerializedName("KioskTransactionFeePercentage")
    val kioskTransactionFeePercentage: Double?,
    @SerializedName("KitchenLanguage")
    val kitchenLanguage: String?,
    @SerializedName("LanguageList")
    val languageList: List<Language>?,
    @SerializedName("MultipleNuc")
    val multipleNuc: MultipleNuc?,
    @SerializedName("NameTranslations")
    val nameTranslations: List<NameTranslation?>?,
    @SerializedName("OrderCompletionTexts")
    val orderCompletionTexts: List<OrderCompletionText?>?,
    @SerializedName("OrganizationId")
    val organizationId: String?,
    @SerializedName("PaymentProviderType")
    val paymentProviderType: String?,
    @SerializedName("PaymentTerminalIP")
    val paymentTerminalIP: String?,
    @SerializedName("PickupTime")
    val pickupTime: Int?,
    @SerializedName("PreOrderTime")
    val preOrderTime: Int?,
    @SerializedName("PrintOnlyFoodItems")
    val printOnlyFoodItems: Boolean?,
    @SerializedName("PrinterList")
    val printerList: List<Printer?>?,
    @SerializedName("RestaurantName")
    val restaurantName: String?,
    @SerializedName("ServingVariations")
    val servingVariations: List<ServingVariation?>?,
    @SerializedName("SupportName")
    val supportName: String?,
    @SerializedName("SupportPhoneNumber")
    val supportPhoneNumber: String?,
    @SerializedName("TipList")
    val tipList: List<Tip?>?,
    @SerializedName("VatUUID")
    val vatUUID: Any?
)

@Keep
data class BrandAddress(
    @SerializedName("AddressDetail")
    val addressDetail: Any?,
    @SerializedName("City")
    val city: Any?,
    @SerializedName("CityTranslations")
    val cityTranslations: List<Any?>?,
    @SerializedName("Country")
    val country: Any?,
    @SerializedName("CountryTranslations")
    val countryTranslations: List<Any?>?,
    @SerializedName("HouseNo")
    val houseNo: Any?,
    @SerializedName("Latitude")
    val latitude: Any?,
    @SerializedName("Longitude")
    val longitude: Any?,
    @SerializedName("MapUrl")
    val mapUrl: Any?,
    @SerializedName("Province")
    val province: Any?,
    @SerializedName("ProvinceTranslations")
    val provinceTranslations: List<Any?>?,
    @SerializedName("StreetNo")
    val streetNo: Any?,
    @SerializedName("ZipCode")
    val zipCode: Any?
)

@Keep
data class CloudPaymentProvider(
    @SerializedName("CurrencyCode")
    val currencyCode: String?,
    @SerializedName("IsReceiptPrintInTerminal")
    val isReceiptPrintInTerminal: Boolean?,
    @SerializedName("ProviderName")
    val providerName: String?,
    @SerializedName("ReceiptWidth")
    val receiptWidth: Int?,
    @SerializedName("TerminalId")
    val terminalId: String?
)

@Keep
data class CustomizePrint(
    @SerializedName("Email")
    val email: Boolean?,
    @SerializedName("Name")
    val name: Boolean?,
    @SerializedName("Phone")
    val phone: Boolean?,
    @SerializedName("Tip")
    val tip: Boolean?
)

@Keep
data class Language(
    @SerializedName("IsActive")
    val isActive: Boolean?,
    @SerializedName("LanguageCode")
    val languageCode: String?,
    @SerializedName("LanguageName")
    val languageName: String?,
    var flag: Int,
    var isSelected : Boolean = false
)

@Keep
data class MultipleNuc(
    @SerializedName("IsActiveMultipleNuc")
    val isActiveMultipleNuc: Boolean?,
    @SerializedName("NucList")
    val nucList: List<Any?>?
)

@Keep
data class NameTranslation(
    @SerializedName("LanguageCodeType")
    val languageCodeType: String?,
    @SerializedName("Text")
    val text: String?
)

@Keep
data class OrderCompletionText(
    @SerializedName("NumberOfLines")
    val numberOfLines: Int?,
    @SerializedName("ThankYouTexts")
    val thankYouTexts: List<String?>?,
    @SerializedName("Type")
    val type: String?
)

@Keep
data class Printer(
    @SerializedName("CategoryList")
    val categoryList: List<String?>?,
    @SerializedName("MarkedAsDefaultPrinter")
    val markedAsDefaultPrinter: Boolean?,
    @SerializedName("PrinterName")
    val printerName: String?
)

@Keep
data class ServingVariation(
    @SerializedName("IsDefault")
    val isDefault: Boolean?,
    @SerializedName("IsEnabled")
    val isEnabled: Boolean?,
    @SerializedName("Type")
    val type: String?
)

@Keep
data class Tip(
    @SerializedName("IsDefault")
    val isDefault: Boolean?,
    @SerializedName("Name")
    val name: String?,
    @SerializedName("TipId")
    val tipId: String?,
    @SerializedName("Type")
    val type: String?,
    @SerializedName("Value")
    val value: Int?
)