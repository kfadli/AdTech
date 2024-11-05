package com.voodoo.sdk.internal.mapper

import com.voodoo.sdk.internal.network.response.AdResponse
import com.voodoo.sdk.model.AdData
import kotlin.time.DurationUnit
import kotlin.time.toDuration

fun AdResponse.toAddData() = AdData(
    url = adUrl,
    duration = duration.toDuration(DurationUnit.SECONDS),
    target = target,
    trackerUrl = trackingURl
)
