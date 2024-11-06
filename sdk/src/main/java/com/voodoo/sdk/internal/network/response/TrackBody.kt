package com.voodoo.sdk.internal.network.response

import kotlinx.serialization.Serializable


@Serializable
internal data class TrackBody(val event: String)