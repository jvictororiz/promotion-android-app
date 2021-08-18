package com.promotion.android

import android.app.Application
import com.google.firebase.FirebaseApp
import com.promotion.android.base.di.baseModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        startKoin {
            androidContext(this@BaseApplication)
            modules(baseModule)
        }
    }
}