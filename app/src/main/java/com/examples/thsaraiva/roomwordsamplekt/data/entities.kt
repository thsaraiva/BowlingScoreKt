package com.examples.thsaraiva.roomwordsamplekt.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = Word.TABLE_NAME)
class Word(@PrimaryKey(autoGenerate = true) var id: Long,
           @ColumnInfo(name = Word.WORD_COLUMN_NAME) var word: String,
           var length: Int = word.length) {

    companion object {
        const val TABLE_NAME = "words_table"
        const val WORD_COLUMN_NAME = "word"
    }

    @Ignore
    var translation_br: String? = null

}