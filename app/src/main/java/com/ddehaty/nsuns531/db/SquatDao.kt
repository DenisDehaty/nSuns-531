package com.ddehaty.nsuns531.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ddehaty.nsuns531.Squat

@Dao
interface SquatDao {

    @Query("SELECT * FROM Squat WHERE id > 0")
    fun getAll(): List<Squat>

    @Insert
    suspend fun save(squat: Squat)
}