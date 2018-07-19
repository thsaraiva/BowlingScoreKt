package com.examples.thsaraiva.roomwordsamplekt.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.examples.thsaraiva.roomwordsamplekt.data.dao.WordDao

@Database(entities = [(Word::class)], version = 1)
abstract class WordRoomDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao

    companion object {
        private const val DB_NAME = "wordRoom.db"
        private var INSTANCE: WordRoomDatabase? = null

        fun getInstance(ctx: Context): WordRoomDatabase {
            if (INSTANCE == null) {
                synchronized(WordRoomDatabase::class) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(ctx.applicationContext,
                                WordRoomDatabase::class.java,
                                WordRoomDatabase.DB_NAME).build()
                    }
                }
            }
            return INSTANCE ?: throw RuntimeException("Fatal Error. Did not create database.")
        }
    }
}