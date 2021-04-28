package com.ddehaty.nsuns531

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DeadliftDao {

    @Query("SELECT * FROM Deadlift WHERE id > 0")
    fun getAll(): List<Deadlift>

    @Insert
    fun save(deadlift: Deadlift)
}