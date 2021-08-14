package br.com.common.login.data.local

import br.com.common.login.data.local.contract.UserLocalDataSource
import br.com.common.login.domain.exception.DefaultException
import br.com.promotion.core.dao.UserDao
import br.com.promotion.core.entity.UserDB
import io.reactivex.Completable
import io.reactivex.Single

class UserLocalDataSourceImpl(private val userDao: UserDao) :
    UserLocalDataSource {
    override fun getLocalUsers() = userDao.getAll().onErrorResumeNext {
        return@onErrorResumeNext Single.error(DefaultException(it.message ?: ""))
    }

    override fun saveLocalUsers(users: List<UserDB>) = userDao.saveAll(users).onErrorResumeNext {
        return@onErrorResumeNext Completable.error(DefaultException(it.message ?: ""))
    }
}