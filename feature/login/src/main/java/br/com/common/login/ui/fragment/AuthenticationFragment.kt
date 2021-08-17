package br.com.common.login.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.common.login.R
import br.com.common.login.databinding.FragmentAuthenticatorBinding
import br.com.common.login.ui.viewmodel.AuthenticationViewModel
import br.com.common.login.ui.viewmodel.model.AuthenticationAction
import br.com.common.login.ui.viewmodel.model.AuthenticationState
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel


class AuthenticationFragment : Fragment() {
    private lateinit var binding: FragmentAuthenticatorBinding
    private val viewModel: AuthenticationViewModel by viewModel()

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
//                    if (it.hasError()) showError()
//                    with(binding){
//                        binding.pbLoad.isVisible = false
//                    }
                }

                is AuthenticationState.RegisterState -> {
//                    if (it.hasError()) showError()
//                    with(binding){
//                        pbLoad.isVisible = false
//                    }
                }
                is AuthenticationState.LoginState -> {
//                    if (it.hasError()) showError()
//                    with(binding){
//                        binding.pbLoad.isVisible = false
//                    }
                }

                is AuthenticationState.LoadingState -> {
//                    binding.pbLoad.isVisible = true
                }
            }

        }

        viewModel.actionLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is AuthenticationAction.OnDoLogin -> {
//                    if (it.remember) {
//                        viewModel.doOnLogin(
//                            "",
//                            "",
//                            binding.loginInclude.checkboxRememberPassword.isChecked
//                        )
//
//                    } else {
//                        viewModel.doOnBiometricLogin(binding.loginInclude.checkboxRememberPassword.isChecked)
//                    }
                }
                is AuthenticationAction.OnRegister -> {
                    //  viewModel.registerUser(User())
                }
                is AuthenticationAction.OnResetPassword -> {
                    viewModel.resetPassword("email")
                }
                is AuthenticationAction.GoToHome -> {
                    findNavController()
                }
                is AuthenticationAction.ShowSuccessMessage -> {
                    Snackbar.make(binding.root, it.message, Snackbar.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    private fun setupListeners() {
        with(binding) {
            btnNext.setOnClickListener {
                viewModel.tapOnNext()
            }
            binding.tabbar.addTab(R.string.text_login_title) {
                viewModel.tapToLogin()
            }
            binding.tabbar.addTab(R.string.text_title_new_register) {
                viewModel.tapToNewRegister()
            }

            binding.loginInclude.textForgotPassword.setOnClickListener {
                viewModel.tapOnResetPassword()
            }
        }
    }
}
