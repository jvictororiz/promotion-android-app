package br.com.promotion.login.domain.usecase.contract

import br.com.promotion.model.domain.User
import io.reactivex.Completable

interface AuthenticationUseCase {
    fun doLogin(email: String, password: String, remember: Boolean): Completable
    fun resetPassword(email: String): Completable
    fun registerUser(user: User, password: String): Completable
    fun doLogin(remember: Boolean): Completable
    fun isRemember(): Boolean
}