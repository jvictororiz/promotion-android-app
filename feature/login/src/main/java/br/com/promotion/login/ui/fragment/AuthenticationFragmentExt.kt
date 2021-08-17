package br.com.promotion.login.ui.fragment

import android.view.View
import androidx.core.view.isVisible
import br.com.promotion.login.R
import br.com.promotion.login.ui.viewmodel.model.AuthenticationState
import br.com.promotion.model.domain.User
import com.google.android.material.snackbar.Snackbar

fun AuthenticationFragment.stateScreenResetPassword(authenticationState: AuthenticationState) {
    binding.pbLoad.isVisible = false
    binding.resetPasswordInclude.root.visibility = View.VISIBLE
    binding.btnNext.text = getString(R.string.text_subtitle_reset_password_button)
    if (authenticationState.hasError()) showError()

}

fun AuthenticationFragment.stateScreenRegister(authenticationState: AuthenticationState) {
    binding.pbLoad.isVisible = false
    binding.newRegisterInclude.root.visibility = View.VISIBLE
    if (authenticationState.hasError()) showError()
    binding.btnNext.text = getString(R.string.text_button_new_register)

}


fun AuthenticationFragment.stateScreenLogin(authenticationState: AuthenticationState) {
    binding.pbLoad.isVisible = false
    binding.loginInclude.root.visibility = View.VISIBLE
    if (authenticationState.hasError()) showError()
    binding.btnNext.text = getString(R.string.text_enter_login)
}

fun AuthenticationFragment.stateLoading() {
    binding.pbLoad.isVisible = true
    TODO()
}

fun AuthenticationFragment.doOnLogin() {
    binding.btnNext.setOnClickListener {
        val email = binding.loginInclude.etEmail.text.toString()
        val password = binding.loginInclude.etPassword.text.toString()
        if (binding.loginInclude.checkboxRememberPassword.isChecked) {
            viewModel.doOnLogin(email, password, binding.loginInclude.checkboxRememberPassword.isChecked)
        } else {
            viewModel.doOnBiometricLogin(binding.loginInclude.checkboxRememberPassword.isChecked)
        }
    }
}

fun AuthenticationFragment.doRegister() {
    binding.btnNext.setOnClickListener {
        val name = binding.newRegisterInclude.etName.text.toString()
        val email = binding.newRegisterInclude.etEmail.text.toString()
        val password = binding.newRegisterInclude.etPassword.text.toString()
        val user = User(name, email, password)
        viewModel.registerUser(user)
    }

}

fun AuthenticationFragment.doResetPassword() {
    binding.btnNext.setOnClickListener {
        val email = binding.resetPasswordInclude.etEmail.text.toString()
        viewModel.resetPassword(email)
    }
}

fun AuthenticationFragment.showError() {
    binding.includeFooterError.isVisible = true

}

fun AuthenticationFragment.showSnackBarDialog(text: String) {
    Snackbar.make(binding.root, text, Snackbar.LENGTH_SHORT)
        .show()
}
