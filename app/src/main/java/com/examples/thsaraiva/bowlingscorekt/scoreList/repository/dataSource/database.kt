package com.examples.thsaraiva.bowlingscorekt.scoreList.repository.dataSource

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [(Score::class)], version = 1)
abstract class BowlingScoreRoomDatabase : RoomDatabase() {
    abstract fun scoreDao(): ScoreDao

    companion object {
        private const val DB_NAME = "bowlingScoreRoomDatabase.db"

        private var INSTANCE: BowlingScoreRoomDatabase? = null

        fun getInstance(ctx: Context): BowlingScoreRoomDatabase {
            if (INSTANCE == null) {
                synchronized(BowlingScoreRoomDatabase::class) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(ctx.applicationContext,
                                BowlingScoreRoomDatabase::class.java,
                                DB_NAME).build()
                    }
                }
            }
            return INSTANCE
                    ?: throw RuntimeException("Fatal Error. Did not create database.")
        }
    }
}