package com.promotion.android.base

import android.app.Application
import com.promotion.android.BuildConfig
import com.promotion.android.base.di.baseModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BaseApplication)
            modules(baseModule)
        }
    }

    fun getBaseUrl() = BuildConfig.BASE_URL
}