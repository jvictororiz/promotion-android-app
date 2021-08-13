package com.promotion.android.login.domain.repository

import com.promotion.android.login.data.local.contract.UserLocalDataSource
import com.promotion.android.login.data.local.mapperExt.toListUser
import com.promotion.android.login.data.local.mapperExt.toListUserDB
import com.promotion.android.login.data.remote.contract.UserRemoteDataSource
import com.promotion.android.login.data.remote.mapper.toListUser
import com.promotion.android.login.domain.model.User
import com.promotion.android.login.domain.repository.contract.UserRepository
import io.reactivex.Single

class UserRepositoryImpl(
    private val localDataSource: UserLocalDataSource,
    private val remoteDataSource: UserRemoteDataSource
) : UserRepository {
    override fun fetchAll(): Single<List<User>> {
        return remoteDataSource.getAllUserDto()
            .map {
                it.toListUser()
            }.doOnSuccess {
                localDataSource.saveLocalUsers(it.toListUserDB()).subscribe()
            }
    }

    override fun getLocalUsers() = localDataSource.getLocalUsers().map { it.toListUser() }

}