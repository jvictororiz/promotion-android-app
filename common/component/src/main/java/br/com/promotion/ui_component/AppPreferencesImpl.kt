package br.com.promotion.ui_component

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey


class AppPreferencesImpl(context: Context) : AppPreferences {

    private val sharedPreferences: SharedPreferences = EncryptedSharedPreferences.create(
        context,
        SECRET_KEY_VALUE,
        MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build(),
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    override fun save(key: String, value: String?) {
        with(sharedPreferences.edit()) {
            putString(key, value)
            apply()
        }
    }

    override fun get(key: String): String? {
        return sharedPreferences.getString(key, null)
    }

    companion object {
        private const val SECRET_KEY_VALUE = "secret_shared_prefs"
    }

}