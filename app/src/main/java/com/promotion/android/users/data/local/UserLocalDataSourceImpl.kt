package com.promotion.android.users.data.local

import com.promotion.android.users.data.local.dao.UserDao
import com.promotion.android.users.data.local.entity.UserDB
import com.promotion.android.users.data.local.contract.UserLocalDataSource
import com.promotion.android.users.domain.exception.DefaultException
import io.reactivex.Completable
import io.reactivex.Single

class UserLocalDataSourceImpl(private val userDao: UserDao) : UserLocalDataSource {
    override fun getLocalUsers() = userDao.getAll().onErrorResumeNext {
        return@onErrorResumeNext Single.error(DefaultException(it.message ?: ""))
    }

    override fun saveLocalUsers(users: List<UserDB>) = userDao.saveAll(users).onErrorResumeNext {
        return@onErrorResumeNext Completable.error(DefaultException(it.message ?: ""))
    }
}