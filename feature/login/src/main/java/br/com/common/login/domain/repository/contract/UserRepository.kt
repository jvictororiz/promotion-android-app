package br.com.common.login.domain.repository.contract

import br.com.promotion.model.domain.User
import io.reactivex.Single

interface UserRepository {
    fun fetchAll(): Single<List<User>>
    fun getLocalUsers(): Single<List<User>>
}