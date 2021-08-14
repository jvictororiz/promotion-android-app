package com.promotion.android.base.di

import androidx.room.Room
import br.com.promotion.lib.builders.AppDatabase
import br.com.promotion.lib.builders.ResourceManager
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module


val baseModule = module {
    factory { br.com.promotion.lib.builders.ResourceManager(context = get()) }
    factory {
        Room.databaseBuilder(
            androidApplication(),
            br.com.promotion.lib.builders.AppDatabase::class.java,
            "AppDatabase"
        ).build()
    }
}