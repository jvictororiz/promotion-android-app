package com.promotion.android.base.di

import androidx.room.Room
import br.com.promotion.core.builders.AppDatabase
import br.com.promotion.core.builders.ResourceManager
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module


val baseModule = module {
    factory { ResourceManager(context = get()) }
    factory {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java,
            "AppDatabase"
        ).build()
    }
}