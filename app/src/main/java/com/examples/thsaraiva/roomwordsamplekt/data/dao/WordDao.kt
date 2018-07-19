package com.examples.thsaraiva.roomwordsamplekt.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.examples.thsaraiva.roomwordsamplekt.data.Word

@Dao
interface WordDao {
    @Insert
    fun insert(word: Word)

    @Query("DELETE FROM ${Word.TABLE_NAME}")
    fun deleteAll()

    @Query("SELECT * FROM ${Word.TABLE_NAME} order by ${Word.WORD_COLUMN_NAME} ASC")
    fun getAllWords(): List<Word>

    //TODO: implement delete and update later
//    @Delete
//    fun delete(word: Word)
//
//    @Update
//    fun update(word: Word)


}
