package com.examples.thsaraiva.roomwordsamplekt.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Word.TABLE_NAME)
class Word(@PrimaryKey @ColumnInfo(name = Word.WORD_COLUMN_NAME) var word: String) {
    companion object {
        const val TABLE_NAME = "words_table"
        const val WORD_COLUMN_NAME = "word"
    }

    var length = word.length
}