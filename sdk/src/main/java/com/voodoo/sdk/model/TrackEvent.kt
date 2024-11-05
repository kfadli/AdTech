package com.voodoo.sdk.model


sealed class TrackEvent {
    data object Load : TrackEvent()
    data object Open : TrackEvent()
    data object Click : TrackEvent()
    data object Close : TrackEvent()
}