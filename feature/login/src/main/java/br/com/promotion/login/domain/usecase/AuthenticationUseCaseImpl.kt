package br.com.promotion.login.domain.usecase

import br.com.promotion.lib.builders.ResourceManager
import br.com.promotion.login.R
import br.com.promotion.login.domain.exception.BusinessLoginException
import br.com.promotion.login.domain.repository.contract.AuthenticationRepository
import br.com.promotion.login.domain.usecase.contract.AuthenticationUseCase
import br.com.promotion.model.domain.User
import br.com.promotion.ui_component.extension.isEmailValid
import io.reactivex.Completable

class AuthenticationUseCaseImpl(
    private val repository: AuthenticationRepository,
    private val resource: ResourceManager
) : AuthenticationUseCase {

    override fun doLogin(
        email: String,
        password: String,
        remember: Boolean
    ): Completable {
        return if (email.isEmpty() || password.isEmpty()) {
            Completable.error(BusinessLoginException(resource.message(R.string.message_email_or_password_empty)))
        } else if (email.isEmailValid().not()) {
            Completable.error(BusinessLoginException(resource.message(R.string.message_email_invalid)))
        } else if (password.length < MAX_LENGTH_PASSWORD) {
            Completable.error(BusinessLoginException(resource.message(R.string.message_password_invalid)))
        } else {
            repository.doLogin(email, password, remember)
        }
    }

    override fun doLogin(remember: Boolean): Completable {
        return repository.doLogin(remember)
    }

    override fun resetPassword(email: String): Completable {
        return if (email.isEmailValid().not()) {
            Completable.error(BusinessLoginException(resource.message(R.string.message_email_invalid)))
        } else {
            repository.resetPassword(email)
        }
    }

    override fun registerUser(user: User, password: String): Completable {
        return if (user.email.isEmpty() || user.name.isEmpty() || password.isEmpty() || password.isEmpty()) {
            Completable.error(BusinessLoginException(resource.message(R.string.message_data_empty_register)))
        } else if (user.email.isEmailValid().not()) {
            Completable.error(BusinessLoginException(resource.message(R.string.message_email_invalid)))
        } else if (password.length < MAX_LENGTH_PASSWORD) {
            Completable.error(BusinessLoginException(resource.message(R.string.message_password_invalid)))
        } else if (user.password != password) {
            Completable.error(BusinessLoginException(resource.message(R.string.message_password_is_not_equals)))
        } else {
            repository.registerUser(user, password)
        }
    }

    override fun isRemember() = repository.isRemember()

    companion object {
        const val MAX_LENGTH_PASSWORD = 6
    }
}