package br.com.promotion.login.ui.fragment

import androidx.annotation.StringRes
import androidx.core.view.isVisible
import br.com.promotion.login.R
import br.com.promotion.login.ui.viewmodel.model.AuthenticationState
import br.com.promotion.model.domain.User
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout

fun AuthenticationFragment.stateScreenResetPassword(authenticationState: AuthenticationState) {
    binding.pbLoad.isVisible = false
    binding.resetPasswordInclude.root.isVisible = true
    binding.newRegisterInclude.root.isVisible = false
    binding.loginInclude.root.isVisible = false
    binding.btnNext.text = getString(R.string.text_subtitle_reset_password_button)
    if (authenticationState.hasError()) showError()

}

fun AuthenticationFragment.stateScreenRegister(authenticationState: AuthenticationState) {
    binding.pbLoad.isVisible = false
    binding.resetPasswordInclude.root.isVisible = false
    binding.newRegisterInclude.root.isVisible = true
    binding.loginInclude.root.isVisible = false
    binding.btnNext.text = getString(R.string.text_button_new_register)
    if (authenticationState.hasError()) showError()

}


fun AuthenticationFragment.stateScreenLogin(authenticationState: AuthenticationState) {
    binding.pbLoad.isVisible = false
    binding.resetPasswordInclude.root.isVisible = false
    binding.newRegisterInclude.root.isVisible = false
    binding.loginInclude.root.isVisible = true
    binding.btnNext.text = getString(R.string.text_enter_login)
    if (authenticationState.hasError()) showError()
}

fun AuthenticationFragment.stateLoading() {
    binding.pbLoad.isVisible = true
}

fun AuthenticationFragment.doOnLogin() {
    binding.btnNext.setOnClickListener {
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
    binding.includeFooterError.root.isVisible = true

}

fun AuthenticationFragment.showSnackBarDialog(text: String) {
    Snackbar.make(binding.root, text, Snackbar.LENGTH_SHORT)
        .show()
}

fun TabLayout.addTab(@StringRes idText: Int, event: () -> Unit) {
    addTab(
        newTab().setText(idText).apply {
            setOnClickListener {
                event.invoke()
            }
        }
    )
}
