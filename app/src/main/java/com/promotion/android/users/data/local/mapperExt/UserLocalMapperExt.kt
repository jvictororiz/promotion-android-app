package com.promotion.android.users.data.local.mapperExt

import com.promotion.android.users.data.local.entity.UserDB
import com.promotion.android.users.domain.model.User

fun List<UserDB>.toListUser() = map {
    User(
        id = it.id,
        img = it.img,
        name = it.name,
        username = it.username,
    )
}

fun List<User>.toListUserDB() = map {
    UserDB(
        id = it.id,
        img = it.img,
        name = it.name,
        username = it.username,
    )
}
