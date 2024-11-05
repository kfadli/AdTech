package com.voodoo.sdk.response

import kotlinx.serialization.SerialName
import kotlin.time.Duration

data class AdResponse(
    @SerialName("static")
    val adUrl: String,
    @SerialName("close_delay")
    val duration: Duration,
    @SerialName("tracking")
    val trackingURl: String,
    @SerialName("clickthrough")
    val target: String
)
