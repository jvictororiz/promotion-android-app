package com.promotion.android.base.di.builders

import com.promotion.android.BuildConfig
import com.promotion.android.base.runIf
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

fun buildRetrofit(
    url: String,
    callMinutesTimeout: Long = 1,
    connectMinutesTimeout: Long = 1,
    readMinutesTimeout: Long = 1,
    writeMinutesTimeout: Long = 1
): Retrofit = Retrofit.Builder()
    .client(
        OkHttpClient().newBuilder()
            .runIf(BuildConfig.DEBUG) {
                it.addInterceptor(HttpLoggingInterceptor().apply {
                    level = (HttpLoggingInterceptor.Level.BODY)
                })
            }
            .callTimeout(callMinutesTimeout, TimeUnit.MINUTES)
            .connectTimeout(connectMinutesTimeout, TimeUnit.MINUTES)
            .readTimeout(readMinutesTimeout, TimeUnit.MINUTES)
            .writeTimeout(writeMinutesTimeout, TimeUnit.MINUTES)
            .build()
    )
    .baseUrl(url)
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .build()