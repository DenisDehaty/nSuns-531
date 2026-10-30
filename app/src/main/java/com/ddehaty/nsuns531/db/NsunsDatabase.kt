package com.ddehaty.nsuns531.db

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ddehaty.nsuns531.*

@Database(
    entities = [Benchpress::class, Deadlift::class, Ohp::class, Squat::class, User::class],
    version = 4,
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
            .addMigrations(MIGRATION_2_4, MIGRATION_3_4)
            .build()

        // Version 3 (schemas/3.json) only renamed the User table to UserWeights and it is not known
        // whether it was released, so version 4 has the same schema as version 2 and both can upgrade to it.
        private val MIGRATION_2_4 = object : Migration(2, 4) {
            override fun migrate(db: SupportSQLiteDatabase) {
            }
        }

        private val MIGRATION_3_4 = object : Migration(3, 4) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE `UserWeights` RENAME TO `User`")
            }
        }
    }
}