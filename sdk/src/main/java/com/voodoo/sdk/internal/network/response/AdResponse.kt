package com.voodoo.sdk.internal.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AdResponse(
    @SerialName("static")
    val adUrl: String,
    @SerialName("close_delay")
    val duration: Long,
    @SerialName("tracking")
    val trackingURl: String,
    @SerialName("clickthrough")
    val target: String
)
