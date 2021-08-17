package br.com.promotion.login.data.local.contract

import io.reactivex.Completable

interface AuthenticationLocalDataSource {
    fun savePreferenceRemember(
        email: String,
        password: String
    ): Completable

    fun clearPreferenceRemember(): Completable
    fun isUserRemember(): Boolean
    fun getEmailUser(): String
    fun getPassordUser(): String
}