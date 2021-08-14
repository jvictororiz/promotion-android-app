package br.com.common.login.di

import androidx.lifecycle.MutableLiveData
import br.com.common.login.data.remote.UserRemoteDataSourceImpl
import br.com.common.login.data.remote.contract.UserRemoteDataSource
import br.com.common.login.domain.usecase.UserUseCaseImpl
import br.com.common.login.domain.usecase.contract.UserUseCase
import br.com.common.login.ui.viewmodel.UserViewModel
import br.com.promotion.core.builders.AppDatabase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val usersModule = module {
    viewModel {
        UserViewModel(
            userUseCase = get(),
            resource = get(),
            state = MutableLiveData()
        )
    }
    factory<UserUseCase> { UserUseCaseImpl(repository = get()) }
//    factory<UserRepository> {
//        UserRepositoryImpl(
//            localDataSource = get(),
//            remoteDataSource = get()
//        )
//    }

    factory<br.com.common.login.data.local.contract.UserLocalDataSource> {
        br.com.common.login.data.local.UserLocalDataSourceImpl(userDao = get())
    }
    factory<UserRemoteDataSource> {
        UserRemoteDataSourceImpl(service = get())
    }

    factory { get<AppDatabase>().userDao() }
}