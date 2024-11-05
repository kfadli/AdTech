package com.voodoo.sdk.internal.repository

import com.voodoo.sdk.model.AdState
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun loadAdvertisement(): Flow<AdState>
}