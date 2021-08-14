package br.com.common.login.domain.usecase.contract

import br.com.promotion.model.domain.User
import io.reactivex.Single

interface UserUseCase {
    fun getAll(): Single<List<User>>
    fun getAllLocal(): Single<List<User>>
}