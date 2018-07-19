package com.examples.thsaraiva.roomwordsamplekt.data.repository

import android.content.Context
import com.examples.thsaraiva.roomwordsamplekt.data.Word
import com.examples.thsaraiva.roomwordsamplekt.data.WordRoomDatabase
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug
import org.jetbrains.anko.doAsync

class WordRepository(ctx: Context) : AnkoLogger {

    private val database = WordRoomDatabase.getInstance(ctx)
    private var wordDao = database.wordDao()
    private var wordList = getAllWords()

    fun getAllWords(): List<Word> {
        debug("Fetching all words")
        wordList = wordDao.getAllWords()
        return wordList
    }

    fun insert(word: Word) {
        doAsync {
            debug("Inserting new word")
            wordDao.insert(word)
        }
    }

}