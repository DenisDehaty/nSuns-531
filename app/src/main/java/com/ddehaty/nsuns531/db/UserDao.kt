package com.ddehaty.nsuns531.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ddehaty.nsuns531.User

@Dao
interface UserDao {

    @Query("SELECT * FROM User ORDER BY id DESC")
    fun getAll(): LiveData<List<User>>

    @Query("SELECT weight FROM User WHERE id = (SELECT max(id) FROM User)")
    fun getLatestUserWeight(): String

    @Insert
    suspend fun save(user: User)

    @Query("DELETE FROM User WHERE id = :id")
    suspend fun deleteById(id: Int)
}