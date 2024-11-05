package com.voodoo.sdk

import com.voodoo.sdk.model.AdState
import com.voodoo.sdk.model.TrackEvent
import kotlinx.coroutines.flow.Flow

interface SDKFacade {
    fun configure(uuid: String): Result<Unit>
    fun loadAdvertisement(): Result<Flow<AdState>>
    suspend fun trackEvent(trackerUrl: String, event: TrackEvent): Result<Unit>
}
