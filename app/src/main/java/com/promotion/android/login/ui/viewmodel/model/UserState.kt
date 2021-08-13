package com.promotion.android.login.ui.viewmodel.model

import com.promotion.android.login.domain.model.User

sealed class UserState {
    data class SuccessState(val users: List<User>) : UserState()
    data class SuccessLocalState(val users: List<User>) : UserState()
    data class ErrorState(val messageError: String, val retryMessage: String) : UserState()
    object LoadingState : UserState()
}