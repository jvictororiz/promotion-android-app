package br.com.promotion.core.builders

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.promotion.core.dao.UserDao
import br.com.promotion.core.entity.UserDB

@Database(entities = [UserDB::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
