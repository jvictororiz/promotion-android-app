package br.com.promotion.firebaseservice.service

import android.R.attr.name
import android.os.Bundle
import br.com.promotion.firebaseservice.LogService
import com.google.firebase.analytics.FirebaseAnalytics


internal class LogServiceImpl(private val analytics: FirebaseAnalytics) : LogService {

    override fun trackClick(nameAction: String) {
        val bundle = Bundle().apply {
            putString(LOG_LOCAL, nameAction)
        }
        analytics.logEvent(LOG_TYPE_CLICK, bundle)
    }

    override fun trackScreen(nameScreen: String) {
        val bundle = Bundle().apply {
            putString(LOG_LOCAL, nameScreen)
        }
        analytics.logEvent(LOG_TYPE_OPEN_SCREEN, bundle)
    }

    override fun trackOnBack(nameScreen: String) {
        val bundle = Bundle().apply {
            putString(LOG_LOCAL, nameScreen)
        }
        analytics.logEvent(LOG_TYPE_ON_BACK_PRESS, bundle)
    }

    override fun trackSuccess(nameService: String) {
        val bundle = Bundle().apply {
            putString(LOG_LOCAL, nameService)
        }
        analytics.logEvent(LOG_TYPE_SUCCESS_SERVICE, bundle)
    }

    override fun trackError(local: String, exception: Exception) {
        val bundle = Bundle().apply {
            putString(LOG_LOCAL, local)
            putString(LOG_ERROR, exception.message)
        }
        analytics.logEvent(LOG_TYPE_ERRROR_SERVICE, bundle)
    }

    companion object{
        private const val LOG_TYPE_CLICK = "clicado"
        private const val LOG_TYPE_OPEN_SCREEN = "abrir_tela"
        private const val LOG_TYPE_ON_BACK_PRESS = "clique_voltar"
        private const val LOG_TYPE_SUCCESS_SERVICE = "sucessso_sevico"
        private const val LOG_TYPE_ERRROR_SERVICE = "erro_sevico"

        private const val LOG_LOCAL = "local"
        private const val LOG_ERROR = "erro"
    }
}