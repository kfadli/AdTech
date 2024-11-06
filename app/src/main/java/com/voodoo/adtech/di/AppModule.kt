package com.voodoo.adtech.di

import com.voodoo.adtech.ui.screen.HomeViewModel
import com.voodoo.sdk.SDKVoodoo
import org.koin.core.module.dsl.*
import org.koin.dsl.module
import kotlin.math.sin

val appModule = module {

    single { SDKVoodoo }
    viewModel { HomeViewModel(get()) }

}