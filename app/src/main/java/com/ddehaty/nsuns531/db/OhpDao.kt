package com.ddehaty.nsuns531.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ddehaty.nsuns531.Ohp

@Dao
interface OhpDao {

    @Query("SELECT * FROM Ohp WHERE id > 0")
    fun getAll(): List<Ohp>

    @Query("SELECT weight FROM Ohp WHERE id = (SELECT max(id) FROM Ohp)")
    suspend fun getLatestWeight(): String

    @Insert
    suspend fun save(ohp: Ohp)
}