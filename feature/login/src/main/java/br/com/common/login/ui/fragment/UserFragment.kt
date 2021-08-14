package br.com.common.login.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.common.login.ui.viewmodel.UserViewModel

class UserFragment : Fragment() {
//    private lateinit var binding: FragmentUsersBinding
//    private val viewModel: UserViewModel by viewModel()

//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        binding = FragmentUsersBinding.inflate(inflater, container, false)
//        return binding.root
//    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        setupViews()
//        viewModel.stateLiveData.observe(viewLifecycleOwner) { state ->
//            when (state) {
//                is UserState.SuccessState -> stateSuccess(state.users, false)
//                is UserState.SuccessLocalState -> stateSuccess(state.users, true)
//                is UserState.ErrorState -> stateError(state)
//                is UserState.LoadingState -> stateLoading()
//            }
//        }
//    }

//    private fun setupViews() {
//        with(binding) {
//            swipeRefresh.setOnRefreshListener {
//                viewModel.tapOnRetry()
//            }
//            binding.contentError.btnRetry.setOnClickListener {
//                viewModel.tapOnRetry()
//            }
//        }
//    }
//
//    private fun stateLoading() {
//        with(binding) {
//            contentError.body.isVisible = false
//            pbLoad.isVisible = true
//            swipeRefresh.isRefreshing = true
//        }
//    }
//
//    private fun stateError(errorState: UserState.ErrorState) {
//        with(binding) {
//            pbLoad.isVisible = false
//            swipeRefresh.isRefreshing = false
//            contentError.body.isVisible = true
//            contentError.tvMessageError.text = errorState.messageError
//            contentError.btnRetry.text = errorState.retryMessage
//        }
//    }
//
//    private fun stateSuccess(users: List<User>, showContentError: Boolean) {
//        with(binding) {
//            if (recyclerView.adapter == null) {
//                recyclerView.adapter = UserListAdapter()
//            }
//            (recyclerView.adapter as UserListAdapter).users = users
//            contentError.body.isVisible = showContentError
//            pbLoad.isVisible = false
//            swipeRefresh.isRefreshing = false
//        }
//    }

}