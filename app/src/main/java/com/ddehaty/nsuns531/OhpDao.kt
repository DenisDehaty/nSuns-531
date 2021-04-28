package com.ddehaty.nsuns531

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface OhpDao {

    @Query("SELECT * FROM Ohp WHERE id > 0")
    fun getAll(): List<Ohp>

    @Insert
    fun save(ohp: Ohp)
}