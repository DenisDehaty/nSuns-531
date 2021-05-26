package com.ddehaty.nsuns531.db

import com.ddehaty.nsuns531.Benchpress
import com.ddehaty.nsuns531.Deadlift
import com.ddehaty.nsuns531.Ohp
import com.ddehaty.nsuns531.Squat

class WeightRepository(
    val db: NsunsDatabase
) {

    fun getAllBenchpressWeights() = db.BenchpressDao().getAll()

    suspend fun getLatestBenchpressWeight() = db.BenchpressDao().getLatestWeight()

    suspend fun saveBenchpressWeight(benchpress: Benchpress) = db.BenchpressDao().save(benchpress)

    fun getAllDeadliftWeights() = db.DeadliftDao().getAll()

    suspend fun getLatestDeadliftWeight() = db.DeadliftDao().getLatestWeight()


    suspend fun saveDeadliftWeight(deadlift: Deadlift) = db.DeadliftDao().save(deadlift)

    fun getAllOhpWeights() = db.OhpDao().getAll()

    suspend fun getLatestOhpWeight() = db.OhpDao().getLatestWeight()


    suspend fun saveOhpWeights(ohp: Ohp) = db.OhpDao().save(ohp)

    fun getAllSquatWeights() = db.SquatDao().getAll()

    suspend fun getLatestSquatWeight() = db.SquatDao().getLatestWeight()


    suspend fun saveSquatWeight(squat: Squat) = db.SquatDao().save(squat)


}