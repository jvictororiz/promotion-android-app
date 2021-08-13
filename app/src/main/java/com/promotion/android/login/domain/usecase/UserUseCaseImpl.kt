package com.promotion.android.login.domain.usecase

import com.promotion.android.login.domain.model.User
import com.promotion.android.login.domain.repository.contract.UserRepository
import com.promotion.android.login.domain.usecase.contract.UserUseCase
import io.reactivex.Single

class UserUseCaseImpl(private val repository: UserRepository) : UserUseCase {
    override fun getAll(): Single<List<User>> = repository.fetchAll()
    override fun getAllLocal(): Single<List<User>> = repository.getLocalUsers()
}