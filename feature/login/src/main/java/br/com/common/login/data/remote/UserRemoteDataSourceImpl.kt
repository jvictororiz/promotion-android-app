package br.com.common.login.data.remote

import br.com.common.login.data.remote.contract.UserRemoteDataSource
import br.com.promotion.firebaseservice.UserService
import br.com.promotion.model.data.UserDTO
import io.reactivex.Single

class UserRemoteDataSourceImpl(private val service: UserService) : UserRemoteDataSource {
    override fun getAllUserDto(): Single<List<UserDTO>> {
        TODO("Not yet implemented")
    }


}