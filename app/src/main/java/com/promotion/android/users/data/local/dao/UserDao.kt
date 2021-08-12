package com.promotion.android.users.data.local.dao

import androidx.room.*
import com.promotion.android.users.data.local.entity.UserDB
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface UserDao {
    @Query("SELECT * FROM userdb")
    fun getAll(): Single<List<UserDB>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAll(users: List<UserDB>): Completable

    @Delete
    fun delete(user: UserDB): Completable

    @Query("DELETE FROM UserDB")
    fun deleteAll()
}
    