package com.promotion.android.login.data.remote.service

import com.promotion.android.login.data.remote.dto.response.UserDto
import io.reactivex.Single
import retrofit2.http.GET

interface UserService {
    @GET("users")
    fun getUsers(): Single<List<UserDto>>
}