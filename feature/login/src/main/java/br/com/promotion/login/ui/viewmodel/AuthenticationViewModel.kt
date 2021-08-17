package br.com.promotion.login.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.promotion.login.domain.usecase.contract.AuthenticationUseCase
import br.com.promotion.login.ui.viewmodel.model.AuthenticationAction
import br.com.promotion.login.ui.viewmodel.model.AuthenticationState
import br.com.promotion.login.ui.viewmodel.model.AuthenticationState.LoginState
import br.com.promotion.lib.builders.ResourceManager
import br.com.promotion.model.domain.User
import br.com.promotion.ui_component.BaseViewModel


class AuthenticationViewModel(
    private val resource: ResourceManager,
    private val authenticationUseCase: AuthenticationUseCase,
    private val state: MutableLiveData<AuthenticationState>,
    private val action: MutableLiveData<AuthenticationAction>
) : BaseViewModel<AuthenticationState>() {

    val stateLiveData: LiveData<AuthenticationState> get() = state
    val actionLiveData: LiveData<AuthenticationAction> get() = action

    init {
        notifyState {
            LoginState(authenticationUseCase.isRemember())
        }
    }

    fun doOnLogin(email: String, password: String, remember: Boolean) {
        notifyState { AuthenticationState.LoadingState }
        authenticationUseCase.doLogin(email, password, remember)
            .doOnSubscribe(disposable::add)
            .doOnError {
                notifyState { state.value?.setError("teste", "tentar novamente") }
            }.doOnComplete {
                notifyAction { AuthenticationAction.GoToHome }
            }.subscribe()

    }

    fun doOnBiometricLogin(remember: Boolean) {
        notifyState { AuthenticationState.LoadingState }
        authenticationUseCase.doLogin(remember)
            .doOnSubscribe(disposable::add)
            .doOnError {
                notifyState { state.value?.setError("teste", "tentar novamente") }
            }.doOnComplete {
                notifyAction { AuthenticationAction.GoToHome }
            }.subscribe()
    }

    fun resetPassword(email: String) {
        authenticationUseCase.resetPassword(email)
            .doOnSubscribe(disposable::add)
            .doOnError {
                notifyState { state.value?.setError("teste", "tentar novamente") }
            }.doOnComplete {
                notifyAction { AuthenticationAction.ShowSuccessMessage("mensagem karina") }
            }.subscribe()
    }

    fun registerUser(user: User) {
        authenticationUseCase.registerUser(user)
            .doOnSubscribe(disposable::add)
            .doOnError {
                notifyState { state.value?.setError("teste", "tentar novamente") }
            }.doOnComplete {
                notifyAction { AuthenticationAction.ShowSuccessMessage("mensagem karina") }
                notifyAction { AuthenticationAction.OnDoLogin() }
            }
            .subscribe()
    }

    fun tapOnNext() {
        when (state.value) {
            is LoginState -> notifyAction { AuthenticationAction.OnDoLogin() }
            is AuthenticationState.RegisterState -> notifyAction { AuthenticationAction.OnRegister }
            is AuthenticationState.ResetPasswordState -> notifyAction { AuthenticationAction.OnResetPassword }
        }
    }

    fun tapToLogin() {
        notifyState {
            LoginState(authenticationUseCase.isRemember())
        }
    }

    fun tapToNewRegister() {
        notifyState {
            AuthenticationState.RegisterState
        }
    }

    fun tapOnResetPassword() {
        notifyState {
            AuthenticationState.ResetPasswordState
        }
    }

    private fun notifyState(block: () -> AuthenticationState?) {
        state.postValue(((block())))
    }

    private fun notifyAction(block: () -> AuthenticationAction) {
        action.postValue(((block())))
    }
}