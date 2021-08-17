package br.com.common.login.domain.usecase.contract

import br.com.promotion.model.domain.User
import io.reactivex.Completable
import io.reactivex.Single

interface AuthenticationUseCase {
    fun doLogin(email: String, password: String, remember: Boolean): Completable
    fun resetPassword(email: String): Completable
    fun registerUser(user: User): Completable
    fun doLogin(remember: Boolean): Completable
    fun isRemember(): Boolean
}