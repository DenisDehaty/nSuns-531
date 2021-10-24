package com.ddehaty.nsuns531.db

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ddehaty.nsuns531.*

@Database(
    entities = [Benchpress::class, Deadlift::class, Ohp::class, Squat::class, User::class],
    version = 2,
    autoMigrations = [
        AutoMigration (from = 1, to = 2)
    ],
    exportSchema = true
)
abstract class NsunsDatabase : RoomDatabase() {
    abstract fun BenchpressDao(): BenchpressDao
    abstract fun DeadliftDao(): DeadliftDao
    abstract fun OhpDao(): OhpDao
    abstract fun SquatDao(): SquatDao
    abstract fun UserDao(): UserDao


    companion object {
        @Volatile
        private var instance: NsunsDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            NsunsDatabase::class.java, "nSunsDatabase.db"
        )
            .build()
    }
}