package com.voodoo.sdk

import com.voodoo.sdk.internal.datasource.AdApiDataSource
import com.voodoo.sdk.internal.network.ClientApiFactory
import com.voodoo.sdk.internal.repository.AdRepository
import com.voodoo.sdk.internal.repository.Repository
import com.voodoo.sdk.model.AdState
import com.voodoo.sdk.model.TrackEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.json.Json

private const val ADVERTISEMENT_API_URL =
    "https://voodoo-adn-framework.s3.eu-west-1.amazonaws.com/test/ad.json"

class SDKVoodoo : SDKFacade {

    private var uuid: String? = null
    private var instance: Repository? = null

    override fun configure(uuid: String): Result<Unit> {
        if (uuid.isBlank()) {
            // Ensure that UUID is not empty
            return Result.failure(IllegalStateException("UUID can not be blank"))
        }

        if (instance != null) {
            // Ensure that the SDK is not initialized multiple times
            return Result.success(Unit)
        }

        this.uuid = uuid
        this.instance = AdRepository(
            advertisementUrl = ADVERTISEMENT_API_URL,
            api = AdApiDataSource(
                clientApi = ClientApiFactory(
                    engine = null,
                    json = Json {
                        ignoreUnknownKeys = true
                        prettyPrint = true
                    }
                ).create(type = "API"),
            ),
            dispatcher = Dispatchers.IO
        )

        return Result.success(Unit)
    }

    override fun loadAdvertisement(): Result<Flow<AdState>> = runCatching {
        instance?.loadAdvertisement()
            ?: throw IllegalStateException("SDK has not been initialized, please call configure() first")
    }

    override suspend fun trackEvent(trackerUrl: String, event: TrackEvent): Result<Unit> =
        instance?.trackEvent(
            trackerUrl = trackerUrl,
            event = event
        ) ?: throw IllegalStateException("SDK has not been initialized, please call configure() first")
}