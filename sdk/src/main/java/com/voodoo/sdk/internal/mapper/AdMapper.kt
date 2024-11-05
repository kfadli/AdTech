package com.voodoo.sdk.internal.mapper

import com.voodoo.sdk.internal.network.response.AdResponse
import com.voodoo.sdk.model.AdData

fun AdResponse.toAddData() = AdData(
    url = adUrl,
    duration = duration,
    target = target
)
