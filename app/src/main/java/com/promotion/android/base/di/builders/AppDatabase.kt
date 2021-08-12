package com.promotion.android.base.di.builders

import androidx.room.Database
import androidx.room.RoomDatabase
import com.promotion.android.users.data.local.dao.UserDao
import com.promotion.android.users.data.local.entity.UserDB

@Database(entities = [UserDB::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
