package br.com.promotion.login.data.remote

import br.com.promotion.firebaseservice.UserService
import br.com.promotion.login.data.remote.contract.AuthenticationRemoteDataSource
import br.com.promotion.login.domain.exception.DefaultException
import br.com.promotion.login.domain.exception.NetworkException
import br.com.promotion.model.data.UserDTO
import io.reactivex.Completable

class UserRemoteDataSourceImpl(private val service: UserService) : AuthenticationRemoteDataSource {
    override fun doLogin(email: String, password: String): Completable {
        return service.doLogin(email, password)
    }

    override fun resetPassword(email: String): Completable {
        return service.sendPasswordResetEmail(email)
    }

    override fun registerUser(user: UserDTO): Completable {
        return service.registerUser(user)
    }


}