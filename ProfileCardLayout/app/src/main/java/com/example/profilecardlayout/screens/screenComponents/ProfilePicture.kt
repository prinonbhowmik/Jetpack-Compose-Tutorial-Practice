package com.example.profilecardlayout.screens.screenComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation

@Composable
fun ProfilePicture(pictureUrl: String, status: Boolean,imageSize: Dp) {
    Image(
        painter = rememberImagePainter(
            data = pictureUrl,
            builder = {
                transformations(CircleCropTransformation())
            },
        ),
        contentDescription = "Circle Image",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(imageSize)
            .clip(CircleShape) // clip to the circle shape
            .border(
                2.dp,
                color = if (status)
                    Color("#008000".toColorInt())
                else
                    Color("#FF0000".toColorInt()),
                CircleShape
            )//optional
    )
}