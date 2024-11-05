package com.voodoo.sdk.internal.repository

import com.voodoo.sdk.internal.datasource.ApiDataSource
import com.voodoo.sdk.internal.mapper.toAddData
import com.voodoo.sdk.model.AdState
import com.voodoo.sdk.model.TrackEvent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber


class AdRepository(
    private val advertisementUrl: String,
    private val api: ApiDataSource,
    private val dispatcher: CoroutineDispatcher
) : Repository {

    override fun loadAdvertisement(): Flow<AdState> {
        Timber.v("[loadAdvertisement]")

        return flow {
            api.getAdvertisement(advertisementUrl)
                .map { it.toAddData() }
                .onSuccess {
                    Timber.d("[loadAdvertisement] fetch with Success")
                    emit(AdState.Loaded(it))
                }
                .onFailure { error ->
                    Timber.w(
                        message = "[loadAdvertisement] Failed to fetch Advertisement",
                        t = error
                    )
                    emit(AdState.Error(error))
                }
        }.flowOn(dispatcher)
    }

    override suspend fun trackEvent(trackerUrl: String, event: TrackEvent): Result<Unit> {
        Timber.v("[trackEvent] event: $event")

        return api.postTrackEvent(url = trackerUrl, event = event)
    }

}