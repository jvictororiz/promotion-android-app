package com.promotion.android.login.domain.usecase.contract

import com.promotion.android.login.domain.model.User
import io.reactivex.Single

interface UserUseCase {
    fun getAll(): Single<List<User>>
    fun getAllLocal(): Single<List<User>>
}