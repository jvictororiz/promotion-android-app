package br.com.promotion.firebaseservice.extension


class InternetConnectionException(message: String?) : Exception(message)
class UserAlreadyRegistered(message: String?) : Exception(message)
class ResultNullNotExpected(message: String?) : Exception(message)