package br.com.promotion.login.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.promotion.login.databinding.FragmentAuthenticationBinding
import br.com.promotion.login.ui.viewmodel.AuthenticationViewModel
import br.com.promotion.login.ui.viewmodel.model.AuthenticationAction
import br.com.promotion.login.ui.viewmodel.model.AuthenticationState
import br.com.promotion.ui_component.extension.addOnSelecteListener
import org.koin.androidx.viewmodel.ext.android.viewModel


class AuthenticationFragment : Fragment() {
    lateinit var binding: FragmentAuthenticationBinding
    val viewModel: AuthenticationViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthenticationBinding.inflate(inflater, container, false)
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
                    doOnLogin()
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
                is AuthenticationAction.ShowMessage -> {
                    showSnackBarDialog(it.message)
                }
                AuthenticationAction.OnBackPressed -> {
                    requireActivity().finish()
                }
            }
        }
    }

    private fun setupListeners() {
        prepareViews()

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            viewModel.onBackPressed()
        }

        with(binding) {
            tabbar.addOnSelecteListener {
                when (it.position) {
                    POSITION_LOGIN -> viewModel.tapToLogin()
                    POSITION_REGISTER -> viewModel.tapToNewRegister()
                }
            }

            btnNext.setOnClickListener {
                viewModel.tapOnNext()
            }

            loginInclude.textForgotPassword.setOnClickListener {
                viewModel.tapOnResetPassword()
            }
        }
    }

    companion object {
        const val POSITION_LOGIN = 0
        const val POSITION_REGISTER = 1
    }
}
