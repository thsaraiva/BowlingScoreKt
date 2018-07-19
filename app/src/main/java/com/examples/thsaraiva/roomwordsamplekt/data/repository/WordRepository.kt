package com.examples.thsaraiva.roomwordsamplekt.data.repository

import android.content.Context
import com.examples.thsaraiva.roomwordsamplekt.data.Word
import com.examples.thsaraiva.roomwordsamplekt.data.WordRoomDatabase

class WordRepository(ctx: Context) {

    private val database = WordRoomDatabase.getInstance(ctx)
    private var wordDao = database.wordDao()
    private var wordList: List<Word> = ArrayList()

    fun getAllWords(): List<Word> {
        wordList = wordDao.getAllWords()
        return wordList
    }

    fun insert(word: Word) {
        wordDao.insert(word)
    }

    interface GetWordsCallback {
        fun onResult(wordList: List<Word>)
    }

}