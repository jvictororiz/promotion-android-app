package br.com.promotion.login.domain.exception

data class NetworkException(val errorMessage: String) : Exception(errorMessage)
data class DefaultException(val errorMessage: String) : Exception(errorMessage)
object EmailNullException : Exception()
object PasswordNullException : Exception()
data class BusinessLoginException(val errorMessage: String) : Exception(errorMessage)
