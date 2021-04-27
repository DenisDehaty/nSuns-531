package com.ddehaty.nsuns531

import android.content.Context
import androidx.room.*

@Database(
    entities = [Benchpress::class],
    version =1
)
    abstract class NsunsDatabase : RoomDatabase() {
        abstract fun BenchpressDao(): BenchpressDao

    companion object {
        @Volatile private var instance: NsunsDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context)= instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also { instance = it}
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context,
            NsunsDatabase::class.java, "nSunsDatabase.db")
            .build()
    }
}