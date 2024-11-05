package com.voodoo.sdk.model

import kotlin.time.Duration

data class AdvertisementData(
    val url: String,
    val duration: Duration,
    val target: String
)
