package com.voodoo.adtech

import android.app.Application
import com.voodoo.adtech.di.appModule
import com.voodoo.sdk.SDKVoodoo
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import timber.log.Timber

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val modules = startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(appModule)
        }

        Timber.plant(Timber.DebugTree())

        modules.koin.get<SDKVoodoo>().apply {
            configure(UUID)
        }

    }
}