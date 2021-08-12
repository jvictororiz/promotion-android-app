package com.promotion.android.base.di

import androidx.room.Room
import com.promotion.android.BuildConfig
import com.promotion.android.base.BaseApplication
import com.promotion.android.base.di.builders.AppDatabase
import com.promotion.android.base.di.builders.ResourceManager
import com.promotion.android.base.di.builders.buildRetrofit
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module


val baseModule = module {
    factory { ResourceManager(context = get()) }
    factory { buildRetrofit((androidApplication() as BaseApplication).getBaseUrl()) }
    factory {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java,
            BuildConfig.DATABASE_NAME
        ).build()
    }
}