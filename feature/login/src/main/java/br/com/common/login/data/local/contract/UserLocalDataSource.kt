package br.com.common.login.data.local.contract

import io.reactivex.Completable
import io.reactivex.Single

interface UserLocalDataSource {
    fun getLocalUsers(): Single<List<br.com.promotion.core.entity.UserDB>>
    fun saveLocalUsers(users: List<br.com.promotion.core.entity.UserDB>): Completable
}