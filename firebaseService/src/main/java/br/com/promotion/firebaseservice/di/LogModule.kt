package br.com.promotion.firebaseservice.di

import br.com.promotion.firebaseservice.LogService
import br.com.promotion.firebaseservice.UserService
import br.com.promotion.firebaseservice.service.LogServiceImpl
import br.com.promotion.firebaseservice.service.UserServiceImpl
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.database.FirebaseDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val logModule = module {
    factory {
        FirebaseAnalytics.getInstance(androidContext())
    }

    factory<LogService> {
        LogServiceImpl(
            analytics = get()
        )
    }
}