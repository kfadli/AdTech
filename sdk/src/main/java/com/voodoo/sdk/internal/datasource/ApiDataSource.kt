package com.voodoo.sdk.internal.datasource

import com.voodoo.sdk.model.TrackEvent
import com.voodoo.sdk.internal.network.response.AdResponse

internal interface ApiDataSource {
    suspend fun getAdvertisement(url: String): Result<AdResponse>
    suspend fun headTrackEvent(url: String, event: String): Result<Unit>
}