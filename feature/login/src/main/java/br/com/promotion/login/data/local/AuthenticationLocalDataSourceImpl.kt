package br.com.promotion.login.data.local

import br.com.promotion.login.data.local.contract.AuthenticationLocalDataSource
import br.com.promotion.login.domain.exception.EmailNullException
import br.com.promotion.login.domain.exception.PasswordNullException
import br.com.promotion.ui_component.AppPreferences
import io.reactivex.Completable

class AuthenticationLocalDataSourceImpl(private val appPreference: AppPreferences) :
    AuthenticationLocalDataSource {

    override fun savePreferenceRemember(email: String, password: String) = Completable.create {
        appPreference.save(KEY_EMAIL, email)
        appPreference.save(KEY_PASSWORD, password)
    }

    override fun clearPreferenceRemember() = Completable.create {
        appPreference.save(KEY_EMAIL, null)
        appPreference.save(KEY_PASSWORD, null)
    }

    override fun isUserRemember(): Boolean {
        return appPreference.get(KEY_EMAIL) != null
                && appPreference.get(KEY_PASSWORD) != null
    }

    override fun getEmailUser() = appPreference.get(KEY_EMAIL) ?: throw EmailNullException
    override fun getPassordUser() = appPreference.get(KEY_PASSWORD) ?: throw PasswordNullException

    companion object {
        private const val KEY_EMAIL = "email"
        private const val KEY_PASSWORD = "password"
    }
}