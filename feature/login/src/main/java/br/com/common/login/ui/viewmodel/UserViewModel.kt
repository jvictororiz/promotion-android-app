package br.com.common.login.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.common.login.R
import br.com.common.login.domain.exception.NetworkException
import br.com.common.login.domain.usecase.contract.UserUseCase
import br.com.common.login.ui.viewmodel.model.UserState
import br.com.promotion.core.builders.ResourceManager
import br.com.promotion.model.domain.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UserViewModel(
    private val resource: ResourceManager,
    private val userUseCase: UserUseCase,
    private val state: MutableLiveData<UserState>
) : BaseViewModel<UserState>() {

    val stateLiveData: LiveData<UserState> get() = state

    init {
        getUsers()
    }

    fun tapOnRetry() {
        getUsers()
    }

    private fun getUsers() {
        notifyScreen { UserState.LoadingState }
        disposable.add(
            userUseCase.getAll()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    usersOnSuccess(it)
                }, {
                    usersOnError(it)
                })
        )
    }

    private fun getLocalUsers() {
        userUseCase.getAllLocal()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnSubscribe(disposable::add)
            .doOnSuccess(::onLocalSuccess)
            .subscribe()
    }

    private fun usersOnSuccess(users: List<User>) = notifyScreen {
        UserState.SuccessState(users)
    }

    private fun onLocalSuccess(users: List<User>) = notifyScreen {
        UserState.SuccessLocalState(users)
    }

    private fun usersOnError(throwable: Throwable) {
        when (throwable) {
            is NetworkException -> notifyScreen {
                UserState.ErrorState(
                    messageError = resource.message(R.string.error_without_connection),
                    retryMessage = resource.message(R.string.retry)
                )
            }
            else -> notifyScreen {
                UserState.ErrorState(
                    messageError = resource.message(R.string.error_default_server),
                    retryMessage = resource.message(R.string.retry)
                )
            }
        }
        getLocalUsers()
    }

    private fun notifyScreen(block: () -> UserState) {
        state.value = ((block()))
    }
}