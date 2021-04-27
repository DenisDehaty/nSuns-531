package com.ddehaty.nsuns531

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BenchpressDao {
    @Query("SELECT * FROM Benchpress WHERE id > 0")
    fun getAll(): List<Benchpress>

    @Insert
    fun save(benchpress: Benchpress)

}