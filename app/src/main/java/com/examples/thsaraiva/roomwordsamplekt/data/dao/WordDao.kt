package com.examples.thsaraiva.roomwordsamplekt.data.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.examples.thsaraiva.roomwordsamplekt.data.Word

@Dao
interface WordDao {

    @Insert(onConflict = REPLACE)
    fun insert(word: Word)

    @Query("DELETE FROM ${Word.TABLE_NAME}")
    fun deleteAll()

    @Query("SELECT * FROM ${Word.TABLE_NAME} order by ${Word.LENGTH_COLUMN_NAME} ASC")
    fun getAllWords(): List<Word>

    @Delete
    fun delete(word: Word)

    @Update
    fun update(word: Word)

}
