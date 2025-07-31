package com.example.vendingmachinejp.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

        @Composable
        fun Int.sdp(): Dp {
            val context = LocalContext.current
            val resourceId = context.resources.getIdentifier("_${this}sdp", "dimen", context.packageName)
            return if (resourceId != 0) {
                context.resources.getDimensionPixelSize(resourceId).dp
            } else {
                this.dp
            }
        }

        @Composable
        fun Int.sspTextUnit(): TextUnit {
            val context = LocalContext.current
            val resourceId = context.resources.getIdentifier("_${this}ssp", "dimen", context.packageName)
            return if (resourceId != 0) {
                (context.resources.getDimensionPixelSize(resourceId) / context.resources.displayMetrics.scaledDensity).sp
            } else {
                this.sp
            }
        }


    }
}