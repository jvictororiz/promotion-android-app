package br.com.promotion.login.ui.viewmodel.model

sealed class AuthenticationState(
    private var hasError: Boolean = false,
    var error: ErrorMessage? = null
) {
    data class LoginState(val hasFingerprint: Boolean = false) : AuthenticationState()
    object RegisterState : AuthenticationState()
    object ResetPasswordState : AuthenticationState()
    object LoadingState : AuthenticationState()

    fun setError(message: String, buttonMessage: String) = apply {
        this.hasError = true
        this.error = ErrorMessage(message, buttonMessage)
    }

    fun hasError() = hasError


    fun setSuccess() = apply {
        this.hasError = false
        this.error = null
    }

    data class ErrorMessage(
        val messageError: String,
        val retryMessage: String
    )
}