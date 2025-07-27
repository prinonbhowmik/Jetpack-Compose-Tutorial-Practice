package com.example.vendingmachinejp.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun HorizontalLine(
    color: Color = Color.Gray,
    thickness: Dp = 1.dp,
    modifier: Modifier = Modifier.fillMaxWidth()
) {
    Box(
        modifier = modifier
            .height(thickness)
            .background(color)
    )
}
