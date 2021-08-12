package com.promotion.android.users.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val img: String,
    val name: String,
    val id: Int,
    val username: String
) : Parcelable