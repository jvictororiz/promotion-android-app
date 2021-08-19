package br.com.promotion.login.ui.viewmodel.model

sealed class AuthenticationAction {
    object OnDoLogin : AuthenticationAction()
    object OnRegister : AuthenticationAction()
    object OnResetPassword : AuthenticationAction()
    object GoToHome : AuthenticationAction()
    object OnBackPressed : AuthenticationAction()
    data class ShowMessage(val message: String) : AuthenticationAction()
}