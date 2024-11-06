package com.voodoo.adtech.ui.popup

import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun AdImage(
    imageUrl: String,
    contentDescription: String,
    onClick: () -> Unit,
    onSuccess: () -> Unit
) {
    AsyncImage(
        modifier = Modifier.clickable { onClick() },
        model = ImageRequest
            .Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        contentDescription = contentDescription,
        contentScale = ContentScale.FillBounds,
        onSuccess = { onSuccess() }
    )

}