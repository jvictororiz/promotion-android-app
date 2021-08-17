package br.com.common.login.data.remote

import br.com.common.login.data.remote.contract.AuthenticationRemoteDataSource
import br.com.promotion.firebaseservice.UserService
import br.com.promotion.model.data.UserDTO
import br.com.promotion.model.domain.User
import io.reactivex.Completable
import io.reactivex.Single

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