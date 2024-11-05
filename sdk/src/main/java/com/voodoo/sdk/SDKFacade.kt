package com.voodoo.sdk

import com.voodoo.sdk.model.AdState
import kotlinx.coroutines.flow.Flow

interface SDKFacade {
    fun configure(uuid: String): Result<Unit>
    fun loadAdvertisement(): Result<Flow<AdState>>
}
