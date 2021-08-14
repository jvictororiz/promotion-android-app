package br.com.common.login.ui.viewmodel.model

import br.com.promotion.model.domain.User

sealed class UserState {
    data class SuccessState(val users: List<User>) : UserState()
    data class SuccessLocalState(val users: List<User>) : UserState()
    data class ErrorState(val messageError: String, val retryMessage: String) : UserState()
    object LoadingState : UserState()
}