package br.com.promotion.lib.builders

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.promotion.lib.dao.UserDao
import br.com.promotion.lib.entity.UserDB

@Database(entities = [UserDB::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
