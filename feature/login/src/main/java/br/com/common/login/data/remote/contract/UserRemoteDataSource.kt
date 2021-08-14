package br.com.common.login.data.remote.contract

import br.com.promotion.model.data.UserDTO
import io.reactivex.Single

interface UserRemoteDataSource {
    fun getAllUserDto(): Single<List<UserDTO>>
}