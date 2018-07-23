package com.examples.thsaraiva.roomwordsamplekt.wordList.repository.dataSource

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Word.TABLE_NAME)
class Word(@PrimaryKey(autoGenerate = true) @ColumnInfo(name = ID_COLUMN_NAME) var id: Long? = null,
           @ColumnInfo(name = WORD_COLUMN_NAME) var word: String,
           @ColumnInfo(name = LENGTH_COLUMN_NAME) var length: Int = word.length) {

    companion object {
        const val TABLE_NAME = "words_table"
        const val ID_COLUMN_NAME = "id"
        const val WORD_COLUMN_NAME = "word"
        const val LENGTH_COLUMN_NAME = "length"
    }

}