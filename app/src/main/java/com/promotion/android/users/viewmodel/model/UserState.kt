package com.promotion.android.users.viewmodel.model

import com.promotion.android.users.domain.model.User

sealed class UserState {
    data class SuccessState(val users: List<User>) : UserState()
    data class SuccessLocalState(val users: List<User>) : UserState()
    data class ErrorState(val messageError: String, val retryMessage: String) : UserState()
    object LoadingState : UserState()
}