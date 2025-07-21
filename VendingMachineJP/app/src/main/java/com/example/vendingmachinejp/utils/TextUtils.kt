package com.example.vendingmachinejp.utils

import androidx.compose.ui.graphics.Color
import org.json.JSONObject

class TextUtils {
    companion object{

        fun languageTextConvert(string: String, detectedLanguage: String): String {
            val jsonObject = JSONObject(string)
            val value = jsonObject.optString(detectedLanguage)

            return if (value.isNullOrEmpty()) jsonObject.optString("en") else value
        }


        fun hexToColor(hex: String): Color {
            // Ensure the string starts with #
            val formattedHex = if (hex.startsWith("#")) hex else "#$hex"
            return Color(android.graphics.Color.parseColor(formattedHex))
        }


    }
}