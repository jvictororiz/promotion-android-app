package br.com.promotion.login.domain.usecase

import br.com.promotion.login.domain.repository.contract.AuthenticationRepository
import br.com.promotion.login.domain.usecase.contract.AuthenticationUseCase
import br.com.promotion.model.domain.User
import io.reactivex.Completable

class AuthenticationUseCaseImpl(private val repository: AuthenticationRepository) :
    AuthenticationUseCase {
    override fun doLogin(email: String, password: String, remember: Boolean): Completable {
        return repository.doLogin(email, password, remember)
    }

    override fun doLogin(remember: Boolean): Completable {
        return repository.doLogin(remember)
    }

    override fun resetPassword(email: String): Completable {
        return repository.resetPassword(email)
    }

    override fun registerUser(user: User): Completable {
        return repository.registerUser(user)
    }

    override fun isRemember() = repository.isRemember()

}