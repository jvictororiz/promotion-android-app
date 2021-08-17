package br.com.promotion.login.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.promotion.login.R
import br.com.promotion.login.databinding.FragmentAuthenticatorBinding
import br.com.promotion.login.ui.viewmodel.AuthenticationViewModel
import br.com.promotion.login.ui.viewmodel.model.AuthenticationAction
import br.com.promotion.login.ui.viewmodel.model.AuthenticationState
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel


class AuthenticationFragment : Fragment() {
    lateinit var binding: FragmentAuthenticatorBinding
    val viewModel: AuthenticationViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthenticatorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        setupViewModel()
    }

    private fun setupViewModel() {
        viewModel.stateLiveData.observe(viewLifecycleOwner) {
            when (it) {
                AuthenticationState.ResetPasswordState -> {
                    stateScreenResetPassword(it)
                }
                is AuthenticationState.RegisterState -> {
                    stateScreenRegister(it)
                }
                is AuthenticationState.LoginState -> {
                    stateScreenLogin(it)
                }
                is AuthenticationState.LoadingState -> {
                    stateLoading()
                }
            }

        }

        viewModel.actionLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is AuthenticationAction.OnDoLogin -> {
                    doOnLogin(it.remember)
                }
                is AuthenticationAction.OnRegister -> {
                    doRegister()
                }
                is AuthenticationAction.OnResetPassword -> {
                    doResetPassword()
                }
                is AuthenticationAction.GoToHome -> {
                    findNavController()
                }
                is AuthenticationAction.ShowSuccessMessage -> {
                    showSnackBarDialog(it.message)
                }
            }
        }
    }

    private fun setupListeners() {
        with(binding) {
            btnNext.setOnClickListener {
                viewModel.tapOnNext()
            }
            tabbar.addTab(R.string.text_login_title) {
                viewModel.tapToLogin()
            }
            tabbar.addTab(R.string.text_title_new_register) {
                viewModel.tapToNewRegister()
            }

            loginInclude.textForgotPassword.setOnClickListener {
                viewModel.tapOnResetPassword()
            }
        }
    }
}
