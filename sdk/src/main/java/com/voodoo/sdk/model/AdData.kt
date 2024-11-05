package com.voodoo.sdk.model

import kotlin.time.Duration


data class AdData(
    val url: String,
    val duration: Duration,
    val target: String,
    val trackerUrl: String,
)