package br.com.common.login.ui.viewmodel.model

sealed class AuthenticationState(
    private var hasError: Boolean = false,
    private var error: ErrorMessage? = null
) {
    data class LoginState(val hasFingerprint: Boolean) : AuthenticationState()
    object RegisterState : AuthenticationState()
    object ResetPasswordState : AuthenticationState()
    object LoadingState : AuthenticationState()

    fun setError(message: String, buttonMessage: String) = apply{
        this.hasError = true
        this.error = ErrorMessage(message, buttonMessage)
    }

    fun hasError() = hasError


    fun setSuccess() = apply {
        this.hasError = false
        this.error = null
    }

    private data class ErrorMessage(
        val messageError: String,
        val retryMessage: String
    )
}