package br.com.common.login.domain.exception

data class NetworkException(val errorMessage: String) : Exception()
data class DefaultException(val errorMessage: String) : Exception()
object EmailNullException : Exception()
object PasswordNullException : Exception()