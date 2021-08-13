package br.com.promotion.firebaseservice

import br.com.promotion.model.data.UserDTO
import br.com.promotion.model.domain.User
import io.reactivex.Completable
import io.reactivex.Single

interface UserService {
    fun doLogin(email: String, password: String): Completable
    fun registerUser(user: UserDTO): Completable
    fun getUser(email: String): Single<UserDTO>
    fun updateUser(userDTO: UserDTO): Completable
}