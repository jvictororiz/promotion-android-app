package br.com.promotion.firebaseservice

interface LogService {
    fun trackClick(nameAction: String)
    fun trackScreen(nameScreen: String)
    fun trackOnBack(nameScreen: String)
    fun trackSuccess(nameService: String)
    fun trackError(local: String, exception: Exception)
}