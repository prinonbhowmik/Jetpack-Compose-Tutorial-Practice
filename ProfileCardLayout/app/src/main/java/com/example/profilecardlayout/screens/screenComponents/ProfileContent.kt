package com.example.profilecardlayout.screens.screenComponents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt

@Composable
fun ProfileContent(name: String, status: Boolean, alignment: Alignment.Horizontal) {
    Column(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(),
        horizontalAlignment = alignment
    ) {
        Text(
            name,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.W600,
            color = Color.Black
        )

        CompositionLocalProvider(
            LocalContentColor provides MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        ){
            Text(
                text = if (status)
                    "Active now"
                else "Offline",
                style = MaterialTheme.typography.bodyMedium,
                color = if (status)
                    Color("#008000".toColorInt())
                else Color("#FF0000".toColorInt()),
            )
        }
    }


}