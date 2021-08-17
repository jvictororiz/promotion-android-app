package br.com.common.login.domain.repository

import br.com.common.login.data.local.contract.AuthenticationLocalDataSource
import br.com.common.login.data.remote.contract.AuthenticationRemoteDataSource
import br.com.common.login.domain.repository.contract.AuthenticationRepository
import br.com.promotion.model.domain.User
import br.com.promotion.model.mapper.toUserDTO
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AuthenticationRepositoryImpl(
    private val localDataSource: AuthenticationLocalDataSource,
    private val remoteDataSource: AuthenticationRemoteDataSource
) : AuthenticationRepository {

    override fun doLogin(email: String, password: String, remember: Boolean): Completable {
        return remoteDataSource.doLogin(email, password)
            .toSingleDefault(null)
            .flatMapCompletable {
                if (remember) {
                    return@flatMapCompletable localDataSource.savePreferenceRemember(
                        email,
                        password
                    )
                } else {
                    return@flatMapCompletable localDataSource.clearPreferenceRemember()
                }
            }
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun isRemember() = localDataSource.isUserRemember()

    override fun doLogin(remember: Boolean) = Completable.create {
        val email = localDataSource.getEmailUser()
        val password = localDataSource.getEmailUser()
        doLogin(email, password, remember)
    }.subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())

    override fun resetPassword(email: String): Completable {
        return remoteDataSource.resetPassword(email)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun registerUser(user: User): Completable {
        return remoteDataSource.registerUser(user.toUserDTO())
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
    }

}