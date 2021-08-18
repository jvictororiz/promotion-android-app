package br.com.promotion.login.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.promotion.firebaseservice.userServiceModule
import br.com.promotion.login.R
import br.com.promotion.login.di.authenticationModule
import br.com.promotion.ui_component.di.commonModule
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class AuthenticationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)
        loadKoinModules(listOf(userServiceModule, commonModule, authenticationModule))
    }

    override fun onDestroy() {
        unloadKoinModules(listOf(commonModule, userServiceModule, authenticationModule))
        super.onDestroy()
    }
}