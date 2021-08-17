package br.com.common.login.di

import androidx.lifecycle.MutableLiveData
import br.com.common.login.data.local.AuthenticationLocalDataSourceImpl
import br.com.common.login.data.local.contract.AuthenticationLocalDataSource
import br.com.common.login.data.remote.UserRemoteDataSourceImpl
import br.com.common.login.data.remote.contract.AuthenticationRemoteDataSource
import br.com.common.login.domain.repository.AuthenticationRepositoryImpl
import br.com.common.login.domain.repository.contract.AuthenticationRepository
import br.com.common.login.domain.usecase.AuthenticationUseCaseImpl
import br.com.common.login.domain.usecase.contract.AuthenticationUseCase
import br.com.common.login.ui.viewmodel.AuthenticationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authenticationModule = module {
    viewModel {
        AuthenticationViewModel(
            authenticationUseCase = get(),
            resource = get(),
            state = MutableLiveData(),
            action = MutableLiveData()
        )
    }
    factory<AuthenticationUseCase> { AuthenticationUseCaseImpl(repository = get()) }

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