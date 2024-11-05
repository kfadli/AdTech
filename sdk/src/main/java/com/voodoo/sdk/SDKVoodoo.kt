package com.voodoo.sdk

import com.voodoo.sdk.model.AdvertisementData
import kotlin.time.DurationUnit
import kotlin.time.toDuration

class SDKVoodoo : SDKFacade {
    override fun configure(uuid: String) {
    }

    override fun getAdvertisement(): AdvertisementData {
        return AdvertisementData(
            url = "",
            duration = 5L.toDuration(DurationUnit.SECONDS),
            target = ""
        )
    }
}