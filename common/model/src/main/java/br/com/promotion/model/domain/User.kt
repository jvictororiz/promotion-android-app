package br.com.promotion.model.domain

import com.sun.org.apache.xpath.internal.operations.Bool

data class User(
    val name: String = "",
    val email: String="",
    val password:String=""
)