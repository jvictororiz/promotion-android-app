package com.promotion.android.login.ui.fragment.basetest

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.os.StrictMode
import androidx.room.Room
import androidx.test.runner.AndroidJUnitRunner
import br.com.promotion.core.builders.AppDatabase
import br.com.promotion.core.builders.ResourceManager
import br.com.promotion.core.builders.buildRetrofit
import com.promotion.android.login.di.usersModule
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.KoinTest

class MockTestRunner : AndroidJUnitRunner() {
    override fun onCreate(arguments: Bundle?) {
        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder().permitAll().build())
        super.onCreate(arguments)
    }

    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, BaseApplicationTest::class.java.name, context)
    }
}

class BaseApplicationTest : Application(), KoinTest {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BaseApplicationTest)
            modules(mockModule, usersModule)
        }
    }

    fun getBaseUrl(): String {
        return "http://127.0.0.1:8080"
    }
}

val mockModule = module {
    factory { ResourceManager(context = get()) }
    factory { buildRetrofit((androidApplication() as BaseApplicationTest).getBaseUrl()) }
    factory {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "DATABASE_TEST"
        ).allowMainThreadQueries().build()
    }
}