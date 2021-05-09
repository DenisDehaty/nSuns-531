package com.ddehaty.nsuns531.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ddehaty.nsuns531.Deadlift

@Dao
interface DeadliftDao {

    @Query("SELECT * FROM Deadlift WHERE id > 0")
    fun getAll(): List<Deadlift>

    @Insert
    suspend fun save(deadlift: Deadlift)
}