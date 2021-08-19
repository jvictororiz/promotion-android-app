package br.com.promotion.login.di

import androidx.lifecycle.MutableLiveData
import br.com.promotion.login.data.local.AuthenticationLocalDataSourceImpl
import br.com.promotion.login.data.local.contract.AuthenticationLocalDataSource
import br.com.promotion.login.data.remote.UserRemoteDataSourceImpl
import br.com.promotion.login.data.remote.contract.AuthenticationRemoteDataSource
import br.com.promotion.login.domain.repository.AuthenticationRepositoryImpl
import br.com.promotion.login.domain.repository.contract.AuthenticationRepository
import br.com.promotion.login.domain.usecase.AuthenticationUseCaseImpl
import br.com.promotion.login.domain.usecase.contract.AuthenticationUseCase
import br.com.promotion.login.ui.viewmodel.AuthenticationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authenticationModule = module {
    viewModel {
        AuthenticationViewModel(
            authenticationUseCase = get(),
            resource = get(),
            logService = get(),
            state = MutableLiveData(),
            action = MutableLiveData()
        )
    }
    factory<AuthenticationUseCase> { AuthenticationUseCaseImpl(repository = get(), get()) }

    factory<AuthenticationRepository> {
        AuthenticationRepositoryImpl(
            localDataSource = get(),
            remoteDataSource = get()
        )
    }

    factory<AuthenticationLocalDataSource> {
        AuthenticationLocalDataSourceImpl(appPreference = get())
    }

    factory<AuthenticationRemoteDataSource> {
        UserRemoteDataSourceImpl(service = get())
    }

}