package com.examples.thsaraiva.roomwordsamplekt.data

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "words_table")
class Word(@PrimaryKey(autoGenerate = true) var id: Long, var word: String, var lenght: Int = word.length) {

    @Ignore
    var translation_br: String? = null

}