package com.voodoo.adtech.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.voodoo.sdk.SDKVoodoo
import com.voodoo.sdk.model.AdState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeViewModel(private val sdk: SDKVoodoo) : ViewModel() {

    private val _uiState = MutableStateFlow<UIState>(UIState.Idle)
    val uiState = _uiState

    private var _adState = MutableStateFlow<AdState>(AdState.Idle)
    val adState = _adState

    fun getAdvertisement() {
        viewModelScope.launch {
            sdk.loadAdvertisement().onSuccess { state ->
                _uiState.emit(UIState.Loaded)
                state.collectLatest { _adState.emit(it) }
            }.onFailure {
                _uiState.emit(UIState.Failure(cause = it))
            }
        }
    }
}


sealed class UIState {
    data object Idle : UIState()

    data object Loaded : UIState()

    data class Failure(
        val cause: Throwable,
    ) : UIState()
}