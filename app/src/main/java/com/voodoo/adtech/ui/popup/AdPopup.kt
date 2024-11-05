package com.voodoo.adtech.ui.popup

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.voodoo.sdk.model.AdData
import com.voodoo.sdk.model.AdState
import kotlinx.coroutines.delay
import kotlin.time.DurationUnit


@Composable
fun AdPopup(
    adState: AdState,
    onDismiss: () -> Unit = {},
    onClick: () -> Unit = {},
) = Box(
    contentAlignment = Alignment.TopEnd,
    modifier = Modifier
        .fillMaxSize()
        .background(Color.Cyan)
        .clickable { onClick() }
) {
    when (adState) {
        is AdState.Loaded -> {
            OnAdDataAvailable(
                onDismiss = onDismiss,
                data = adState.data
            )
        }

        is AdState.Error -> {
            Text(adState.throwable.message ?: "Unknown failure")
        }

        AdState.Loading -> CircularProgressIndicator()
        else -> {}
    }
}

@Composable
fun OnAdDataAvailable(data: AdData, onDismiss: () -> Unit) {
    var timeLeft by remember { mutableIntStateOf(data.duration.toInt(DurationUnit.SECONDS)) }
    var isExpired by remember { mutableStateOf(false) }
    var redirect by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit) {
        while (timeLeft > 0) {
            delay(1000L)
            timeLeft--
        }
        isExpired = true
    }

    if (!redirect) {
        AdImage(
            imageUrl = data.url,
            contentDescription = "Advertisement",
            onClick = { redirect = true }
        )

        if (isExpired)
            OutlinedIconButton(
                onClick = onDismiss,
                modifier = Modifier
                    .padding(16.dp)
                    .clip(CircleShape)
                    .size(32.dp)
                    .background(Color.Black),
                border = BorderStroke(2.5.dp, Color.Black)
            ) {

                Icon(
                    imageVector = Icons.Outlined.Close,
                    contentDescription = "Close",
                )
            }
    } else {
        AdWebView(data.target)
    }
}
