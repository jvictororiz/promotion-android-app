package com.promotion.android.login.data.remote.mapper

import com.promotion.android.login.data.remote.dto.response.UserDto
import com.promotion.android.login.domain.model.User

fun List<UserDto>.toListUser() = map {
    User(
        id = it.id,
        img = it.img,
        name = it.name,
        username = it.username,
    )
}
