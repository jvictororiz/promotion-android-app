package com.promotion.android.login.data.local

import com.promotion.android.login.data.local.dao.UserDao
import com.promotion.android.login.data.local.entity.UserDB
import com.promotion.android.login.data.local.contract.UserLocalDataSource
import com.promotion.android.login.domain.exception.DefaultException
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