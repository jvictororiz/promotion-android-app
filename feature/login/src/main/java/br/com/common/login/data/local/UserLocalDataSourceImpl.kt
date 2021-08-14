package br.com.common.login.data.local

import br.com.common.login.data.local.contract.UserLocalDataSource
import br.com.common.login.domain.exception.DefaultException
import br.com.promotion.lib.dao.UserDao
import br.com.promotion.lib.entity.UserDB
import io.reactivex.Completable
import io.reactivex.Single

class UserLocalDataSourceImpl(private val userDao: br.com.promotion.lib.dao.UserDao) :
    UserLocalDataSource {
    override fun getLocalUsers() = userDao.getAll().onErrorResumeNext {
        return@onErrorResumeNext Single.error(DefaultException(it.message ?: ""))
    }

    override fun saveLocalUsers(users: List<br.com.promotion.lib.entity.UserDB>) = userDao.saveAll(users).onErrorResumeNext {
        return@onErrorResumeNext Completable.error(DefaultException(it.message ?: ""))
    }
}