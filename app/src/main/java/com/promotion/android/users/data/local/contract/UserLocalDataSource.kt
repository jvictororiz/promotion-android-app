package com.promotion.android.users.data.local.contract

import com.promotion.android.users.data.local.entity.UserDB
import io.reactivex.Completable
import io.reactivex.Single

interface UserLocalDataSource {
    fun getLocalUsers(): Single<List<UserDB>>
    fun saveLocalUsers(users: List<UserDB>): Completable
}