package br.com.common.login.domain.usecase

import br.com.common.login.domain.repository.contract.UserRepository
import br.com.common.login.domain.usecase.contract.UserUseCase
import br.com.promotion.model.domain.User
import io.reactivex.Single

class UserUseCaseImpl(private val repository: UserRepository) : UserUseCase {
    override fun getAll(): Single<List<User>> = repository.fetchAll()
    override fun getAllLocal(): Single<List<User>> = repository.getLocalUsers()
}