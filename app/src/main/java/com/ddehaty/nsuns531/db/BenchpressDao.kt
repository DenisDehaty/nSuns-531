package com.ddehaty.nsuns531.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ddehaty.nsuns531.Benchpress

@Dao
interface BenchpressDao {

    @Query("SELECT * FROM Benchpress WHERE id > 0")
    fun getAll(): List<Benchpress>

    @Insert
    suspend fun save(benchpress: Benchpress)

}