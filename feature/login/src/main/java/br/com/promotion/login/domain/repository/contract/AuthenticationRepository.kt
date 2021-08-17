package br.com.promotion.login.domain.repository.contract

import br.com.promotion.model.domain.User
import io.reactivex.Completable

interface AuthenticationRepository {
    fun doLogin(email: String, password: String, remember: Boolean): Completable
    fun resetPassword(email: String): Completable
    fun registerUser(user: User): Completable
    fun doLogin(remember: Boolean): Completable
    fun isRemember(): Boolean
}