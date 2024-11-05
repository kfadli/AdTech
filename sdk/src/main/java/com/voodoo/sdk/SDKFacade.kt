package com.voodoo.sdk

import com.voodoo.sdk.model.AdvertisementData

interface SDKFacade {
    fun configure(uuid: String)
    fun getAdvertisement(): AdvertisementData
}