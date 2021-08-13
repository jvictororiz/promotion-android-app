package com.promotion.android.login.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserDB(
    @ColumnInfo(name = "img_user")
    val img: String,
    @ColumnInfo(name = "name_user")
    val name: String,
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "username_user")
    val username: String
)