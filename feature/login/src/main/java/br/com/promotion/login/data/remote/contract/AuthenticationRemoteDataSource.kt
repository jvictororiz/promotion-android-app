package br.com.promotion.login.data.remote.contract

import br.com.promotion.model.data.UserDTO
import br.com.promotion.model.domain.User
import io.reactivex.Completable

interface AuthenticationRemoteDataSource {
    fun doLogin(email: String, password: String): Completable
    fun resetPassword(email: String): Completable
    fun registerUser(user: UserDTO): Completable
}