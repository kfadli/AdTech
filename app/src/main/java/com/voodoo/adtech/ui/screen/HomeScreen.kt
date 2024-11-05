package com.voodoo.adtech.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.voodoo.adtech.ui.theme.VoodooTheme
import com.voodoo.adtech.ui.popup.AdPopup
import com.voodoo.sdk.model.AdState
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun Home(modifier: Modifier = Modifier, viewModel: HomeViewModel = koinViewModel()) {

    val uiState: UIState by viewModel.uiState.collectAsStateWithLifecycle()
    val adState: AdState? by viewModel.adState.collectAsStateWithLifecycle()

    var isDisplay by remember { mutableStateOf(false) }

    Box(modifier = modifier) {
        HomeLayout(
            isLoaded = uiState is UIState.Loaded,
            onLoadClick = { viewModel.getAdvertisement() },
            onShowClick = { isDisplay = true }
        )
        when (val mState = uiState) {
            is UIState.Failure -> OnError(mState.cause)
            else -> {}
        }

        if(isDisplay) {
            when (val mState = adState) {
                is AdState.Loaded -> {
                    OnPopup(adState = mState, onDismiss = {
                        isDisplay = false
                    })
                }

                is AdState.Error -> OnError(mState.throwable)
                else -> {}
            }
        }
    }
}

@Composable
fun HomeLayout(
    isLoaded: Boolean,
    onLoadClick: () -> Unit,
    onShowClick: () -> Unit
) {
    Column(modifier = Modifier) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = onLoadClick,
            ) {
                Text(
                    textAlign = TextAlign.Center,
                    text = "Load"
                )
            }

            Button(
                enabled = isLoaded,
                onClick = onShowClick,
            ) {
                Text(
                    textAlign = TextAlign.Center,
                    text = "Show"
                )
            }
        }
    }
}


@Composable
fun OnError(throwable: Throwable) {
    Toast.makeText(
        LocalContext.current,
        "${throwable.message}",
        Toast.LENGTH_SHORT
    ).show()
}

@Composable
fun OnPopup(adState: AdState, onDismiss: () -> Unit) {
    AdPopup(
        adState = adState,
        onDismiss = onDismiss,
    )

}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    VoodooTheme {
        Home()
    }
}