package com.voodoo.sdk.internal.mapper

import com.voodoo.sdk.internal.network.response.AdResponse
import com.voodoo.sdk.model.AdData
import com.voodoo.sdk.model.TrackEvent
import kotlin.time.DurationUnit
import kotlin.time.toDuration

internal fun AdResponse.toAddData() = AdData(
    url = adUrl,
    duration = duration.toDuration(DurationUnit.SECONDS),
    target = target,
    trackerUrl = trackingURl
)

internal fun TrackEvent.toValue() = when (this) {
    TrackEvent.Click -> "ad_click"
    TrackEvent.Close -> "ad_click"
    TrackEvent.Load -> "ad_load" // ???
    TrackEvent.Open -> "ad_shown"
}