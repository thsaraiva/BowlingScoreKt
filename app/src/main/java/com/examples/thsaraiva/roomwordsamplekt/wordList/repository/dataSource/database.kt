package com.examples.thsaraiva.roomwordsamplekt.wordList.repository.dataSource

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [(Word::class)], version = 2)
abstract class WordRoomDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao

    companion object {
        private const val DB_NAME = "wordRoom.db"

        //Simple migration to add a column to v2 of DB
        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {

                // Create the new table
                database.execSQL(
                        "CREATE TABLE NEW_TEMP_TABLE (${Word.ID_COLUMN_NAME} INTEGER," +
                                " ${Word.WORD_COLUMN_NAME} TEXT NOT NULL, ${Word.LENGTH_COLUMN_NAME} INTEGER NOT NULL," +
                                " PRIMARY KEY(${Word.ID_COLUMN_NAME}))")

                // Copy the data
                database.execSQL(
                        "INSERT INTO NEW_TEMP_TABLE (${Word.WORD_COLUMN_NAME},${Word.LENGTH_COLUMN_NAME})" +
                                " SELECT ${Word.WORD_COLUMN_NAME},${Word.LENGTH_COLUMN_NAME}" +
                                " FROM ${Word.TABLE_NAME}")

                // Remove the old table
                database.execSQL("DROP TABLE ${Word.TABLE_NAME}")

                // Change the table name to the correct one
                database.execSQL("ALTER TABLE NEW_TEMP_TABLE RENAME TO ${Word.TABLE_NAME}")
            }
        }

        private var INSTANCE: WordRoomDatabase? = null

        fun getInstance(ctx: Context): WordRoomDatabase {
            if (INSTANCE == null) {
                synchronized(WordRoomDatabase::class) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(ctx.applicationContext,
                                WordRoomDatabase::class.java,
                                DB_NAME).addMigrations(MIGRATION_1_2).build()
                    }
                }
            }
            return INSTANCE
                    ?: throw RuntimeException("Fatal Error. Did not create database.")
        }
    }
}