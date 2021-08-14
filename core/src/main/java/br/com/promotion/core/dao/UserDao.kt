package br.com.promotion.core.dao

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface UserDao {
    @Query("SELECT * FROM userdb")
    fun getAll(): Single<List<br.com.promotion.core.entity.UserDB>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAll(users: List<br.com.promotion.core.entity.UserDB>): Completable

    @Delete
    fun delete(user: br.com.promotion.core.entity.UserDB): Completable

    @Query("DELETE FROM UserDB")
    fun deleteAll()
}
    