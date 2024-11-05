package com.voodoo.sdk.model

sealed class AdState {
    data object Idle : AdState()
    data object Loading : AdState()
    data class Loaded(val data: AdData) : AdState()
    data class Error(val throwable: Throwable) : AdState()
}