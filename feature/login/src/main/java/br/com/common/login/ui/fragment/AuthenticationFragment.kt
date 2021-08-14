package br.com.common.login.ui.fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.common.login.R
import br.com.common.login.databinding.FragmentAuthenticatorBinding


class AuthenticationFragment : Fragment() {
    private lateinit var binding: FragmentAuthenticatorBinding

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

    }

    private fun setupListeners(){
        binding.btnWelcome.setOnClickListener {
            startNewRegister()
        }
    }

    private fun startLogin(){
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.container_authentication_fragment, LoginFragment())
        transaction?.disallowAddToBackStack()
        transaction?.commit()
    }

    private fun startNewRegister(){
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.container_authentication_fragment, NewRegisterFragment())
        transaction?.disallowAddToBackStack()
        transaction?.commit()
    }

    private fun resetPassword(){

    }
}
