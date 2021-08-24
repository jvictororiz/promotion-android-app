package br.com.promotion.login.ui.fragment

import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.transition.TransitionManager
import br.com.promotion.login.R
import br.com.promotion.login.ui.fragment.AuthenticationFragment.Companion.POSITION_LOGIN
import br.com.promotion.login.ui.fragment.AuthenticationFragment.Companion.POSITION_REGISTER
import br.com.promotion.login.ui.viewmodel.model.AuthenticationState
import br.com.promotion.model.domain.User
import br.com.promotion.ui_component.extension.setOnTouchOrFocusListener
import com.google.android.material.snackbar.Snackbar

fun AuthenticationFragment.stateScreenResetPassword(authenticationState: AuthenticationState) {
    with(binding) {
        pbLoad.isVisible = false
        btnNext.isVisible = true
        newRegisterInclude.root.isVisible = false
        loginInclude.root.isVisible = false
        loginInclude.root.isInvisible = true
        TransitionManager.beginDelayedTransition(binding.root as ViewGroup)
        resetPasswordInclude.root.isVisible = true
        btnNext.text = getString(R.string.text_subtitle_reset_password_button)
        labelSubtitle.text = getString(R.string.welcome_subtitle_reset_password)
        if (authenticationState.hasError()) {
            showError(
                authenticationState.error?.messageError,
                authenticationState.error?.retryMessage
            )
        }
    }
}

fun AuthenticationFragment.stateScreenLogin(authenticationState: AuthenticationState) {
    with(binding) {
        pbLoad.isVisible = false
        btnNext.isVisible = true
        resetPasswordInclude.root.isVisible = false
        newRegisterInclude.root.isVisible = false
        loginInclude.root.isInvisible = true
        TransitionManager.beginDelayedTransition(binding.root as ViewGroup)
        loginInclude.root.isVisible = true
        btnNext.text = getString(R.string.text_enter_login)
        labelSubtitle.text = getString(R.string.welcome_subtitle_login)
        with(tabbar.getTabAt(POSITION_LOGIN)) {
            if (this?.isSelected == false) select()
        }
        if (authenticationState.hasError()) {
            pbLoad.isVisible = false
            showError(
                authenticationState.error?.messageError,
                authenticationState.error?.retryMessage
            )
        }
    }
}


fun AuthenticationFragment.stateScreenRegister(authenticationState: AuthenticationState) {
    with(binding) {
        pbLoad.isVisible = false
        btnNext.isVisible = true
        resetPasswordInclude.root.isVisible = false
        loginInclude.root.isVisible = false
        newRegisterInclude.root.isInvisible = true
        TransitionManager.beginDelayedTransition(binding.root as ViewGroup)
        newRegisterInclude.root.isVisible = true
        btnNext.text = getString(R.string.text_title_new_register)
        labelSubtitle.text = getString(R.string.welcome_subtitle_register)
        with(tabbar.getTabAt(POSITION_REGISTER)) {
            if (this?.isSelected == false) select()
        }
        if (authenticationState.hasError()) {
            showError(
                authenticationState.error?.messageError,
                authenticationState.error?.retryMessage
            )
        }
    }
}

fun AuthenticationFragment.stateLoading() {
    binding.pbLoad.isVisible = true
    binding.btnNext.isVisible = false
}

fun AuthenticationFragment.doOnLogin() {
    val email = binding.loginInclude.etEmail.text.toString()
    val password = binding.loginInclude.etPassword.text.toString()
    if (binding.loginInclude.checkboxRememberPassword.isChecked) {
        viewModel.doOnLogin(
            email,
            password,
            binding.loginInclude.checkboxRememberPassword.isChecked
        )
    } else {
        viewModel.doOnBiometricLogin(binding.loginInclude.checkboxRememberPassword.isChecked)
    }
}

fun AuthenticationFragment.doRegister() {
    val name = binding.newRegisterInclude.etName.text.toString()
    val email = binding.newRegisterInclude.etEmail.text.toString()
    val password = binding.newRegisterInclude.etPassword.text.toString()
    val confirmPassword = binding.newRegisterInclude.confirmPassword.text.toString()

    val user = User(name, email, password)
    viewModel.registerUser(user,confirmPassword)
}

fun AuthenticationFragment.doResetPassword() {
    val email = binding.resetPasswordInclude.etEmail.text.toString()
    viewModel.resetPassword(email)
}

fun AuthenticationFragment.showError(messageError: String?, buttonError: String?) {
    binding.includeFooterError.root.isVisible = true
    binding.includeFooterError.tvMessageError.text = messageError
    binding.includeFooterError.btnRetry.text = buttonError

}

fun AuthenticationFragment.showSnackBarDialog(text: String) {
    binding.pbLoad.isVisible = false
    Snackbar.make(binding.body, text, Snackbar.LENGTH_SHORT).show()
}