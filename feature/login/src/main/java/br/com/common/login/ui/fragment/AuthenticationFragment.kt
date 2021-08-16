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
    private val loginFragment by lazy {
        LoginFragment()
    }

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
        startLogin()
    }

    private fun setupListeners(){
        with(binding){
            textRegister.setOnClickListener {
                startNewRegister()
                setBackgroundColorRegister()
            }
            textLogin.setOnClickListener {
                startLogin()
            }
        }


    }

    private fun startLogin(){
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.container_authentication_fragment, LoginFragment())
        transaction?.commit()
        setBackgroundColorLogin()
    }

    private fun startNewRegister(){
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.container_authentication_fragment, NewRegisterFragment())
        transaction?.commit()

    }

    private fun setBackgroundColorRegister(){
        with(binding){
            viewHighlightRegister.setBackgroundColor(R.drawable.shape_view_toolbar)
            textRegister.setTextColor(resources.getColor(R.color.colorPrimaryPromotion,))
        }
    }

    private fun setBackgroundColorLogin(){
        with(binding){
            viewHighlightLogin.setBackgroundColor(R.drawable.shape_view_toolbar)
            textLogin.setTextColor(resources.getColor(R.color.colorPrimaryPromotion,))

        }
    }
}
