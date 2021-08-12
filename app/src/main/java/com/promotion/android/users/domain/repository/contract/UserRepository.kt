package com.promotion.android.users.domain.repository.contract

import com.promotion.android.users.domain.model.User
import io.reactivex.Single

interface UserRepository {
    fun fetchAll(): Single<List<User>>
    fun getLocalUsers(): Single<List<User>>
}