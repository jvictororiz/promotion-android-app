package com.promotion.android.login.data.remote.contract

import com.promotion.android.login.data.remote.dto.response.UserDto
import io.reactivex.Single

interface UserRemoteDataSource {
    fun  getAllUserDto(): Single<List<UserDto>>
}