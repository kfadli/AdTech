package com.voodoo.sdk.model


sealed class TrackEvent {
    data object Show: TrackEvent()
    data object Click: TrackEvent()
    data object Close: TrackEvent()
}