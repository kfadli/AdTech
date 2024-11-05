package com.voodoo.sdk.internal.datasource

import com.voodoo.sdk.model.TrackEvent
import com.voodoo.sdk.response.AdResponse

interface ApiDataSource {
    suspend fun getAdvertisement(url: String): Result<AdResponse>
    suspend fun postTrackEvent(url: String, event: TrackEvent): Result<Unit>
}