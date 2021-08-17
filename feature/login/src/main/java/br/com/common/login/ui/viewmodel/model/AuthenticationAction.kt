package br.com.common.login.ui.viewmodel.model

sealed class AuthenticationAction {
    data class OnDoLogin(val remember: Boolean) : AuthenticationAction()
    object OnRegister : AuthenticationAction()
    object OnResetPassword : AuthenticationAction()
    object GoToHome : AuthenticationAction()
    data class ShowSuccessMessage(val message: String) : AuthenticationAction()
}