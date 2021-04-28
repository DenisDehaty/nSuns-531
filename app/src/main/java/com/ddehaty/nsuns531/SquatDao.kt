package com.ddehaty.nsuns531

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SquatDao {

    @Query("SELECT * FROM Squat WHERE id > 0")
    fun getAll(): List<Squat>

    @Insert
    fun save(squat: Squat)
}