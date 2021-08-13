package com.promotion.android.login.data.remote

import com.promotion.android.login.data.remote.dto.response.UserDto
import com.promotion.android.login.data.remote.service.UserService
import com.promotion.android.login.data.remote.contract.UserRemoteDataSource
import com.promotion.android.login.domain.exception.DefaultException
import com.promotion.android.login.domain.exception.NetworkException
import io.reactivex.Single
import java.io.IOException

class UserRemoteDataSourceImpl(private val service: UserService) : UserRemoteDataSource {

    override fun getAllUserDto(): Single<List<UserDto>> = service.getUsers().onErrorResumeNext {
        return@onErrorResumeNext when (it) {
            is IOException -> Single.error(NetworkException(it.message ?: String()))
            else -> Single.error(DefaultException(it.message ?: String()))
        }
    }
}