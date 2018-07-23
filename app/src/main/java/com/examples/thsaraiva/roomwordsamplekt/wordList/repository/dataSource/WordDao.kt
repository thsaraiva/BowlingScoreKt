package com.examples.thsaraiva.roomwordsamplekt.wordList.repository.dataSource

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface WordDao {

    @Query("SELECT * FROM ${Word.TABLE_NAME} order by ${Word.LENGTH_COLUMN_NAME} ASC")
    fun getAllWords(): List<Word>

    @Insert(onConflict = REPLACE)
    fun insert(word: Word)

    @Delete
    fun delete(word: Word)

    @Query("DELETE FROM ${Word.TABLE_NAME}")
    fun deleteAll()

    @Update
    fun update(word: Word)
}
