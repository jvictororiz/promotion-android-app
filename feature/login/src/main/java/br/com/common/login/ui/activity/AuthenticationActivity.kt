package br.com.common.login.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.common.login.R
import br.com.common.login.di.authenticationModule
import br.com.promotion.ui_component.di.commonModule
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class AuthenticationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)
        loadKoinModules(listOf(authenticationModule, commonModule))
    }

    override fun onDestroy() {
        unloadKoinModules(listOf(authenticationModule, commonModule))
        super.onDestroy()
    }
}