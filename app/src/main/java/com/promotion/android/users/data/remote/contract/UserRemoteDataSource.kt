package com.promotion.android.users.data.remote.contract

import com.promotion.android.users.data.remote.dto.response.UserDto
import io.reactivex.Single

interface UserRemoteDataSource {
    fun  getAllUserDto(): Single<List<UserDto>>
}