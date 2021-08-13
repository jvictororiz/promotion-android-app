package com.promotion.android.login.di

import androidx.lifecycle.MutableLiveData
import com.promotion.android.base.di.builders.AppDatabase
import com.promotion.android.login.data.local.UserLocalDataSourceImpl
import com.promotion.android.login.data.local.contract.UserLocalDataSource
import com.promotion.android.login.data.remote.UserRemoteDataSourceImpl
import com.promotion.android.login.data.remote.contract.UserRemoteDataSource
import com.promotion.android.login.data.remote.service.UserService
import com.promotion.android.login.domain.repository.UserRepositoryImpl
import com.promotion.android.login.domain.repository.contract.UserRepository
import com.promotion.android.login.domain.usecase.UserUseCaseImpl
import com.promotion.android.login.domain.usecase.contract.UserUseCase
import com.promotion.android.login.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val usersModule = module {
    viewModel {
        UserViewModel(
            userUseCase = get(),
            resource = get(),
            state = MutableLiveData()
        )
    }
    factory<UserUseCase> { UserUseCaseImpl(repository = get()) }
    factory<UserRepository> {
        UserRepositoryImpl(
            localDataSource = get(),
            remoteDataSource = get()
        )
    }
    factory<UserLocalDataSource> {
        UserLocalDataSourceImpl(userDao = get())
    }
    factory<UserRemoteDataSource> {
        UserRemoteDataSourceImpl(service = get())
    }
    factory<UserService> { get<Retrofit>().create(UserService::class.java) }

    factory { get<AppDatabase>().userDao() }
}