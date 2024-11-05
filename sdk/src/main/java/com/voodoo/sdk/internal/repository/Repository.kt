package com.voodoo.sdk.internal.repository

import com.voodoo.sdk.model.AdState
import com.voodoo.sdk.model.TrackEvent
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun loadAdvertisement(): Flow<AdState>
    suspend fun trackEvent(trackerUrl: String, event: TrackEvent): Result<Unit>
}