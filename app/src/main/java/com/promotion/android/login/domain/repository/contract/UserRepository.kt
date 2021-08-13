package com.promotion.android.login.domain.repository.contract

import com.promotion.android.login.domain.model.User
import io.reactivex.Single

interface UserRepository {
    fun fetchAll(): Single<List<User>>
    fun getLocalUsers(): Single<List<User>>
}