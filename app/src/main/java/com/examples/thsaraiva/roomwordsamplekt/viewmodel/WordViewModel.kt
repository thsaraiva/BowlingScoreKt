package com.examples.thsaraiva.roomwordsamplekt.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.examples.thsaraiva.roomwordsamplekt.data.Word
import com.examples.thsaraiva.roomwordsamplekt.data.repository.WordRepository
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class WordViewModel(app: Application) : AndroidViewModel(app), AnkoLogger {

    private var wordRepository = WordRepository(app.applicationContext)
    private var wordList: List<Word> = ArrayList()

    fun getAllWords(callback: WordRepository.GetWordsCallback) {
        debug("Fetching all words")
        doAsync {
            debug("Asynchronously getting word list")
            wordList = wordRepository.getAllWords()
            debug("Aha! Got the list!!")
            uiThread {
                callback.onResult(wordList)
            }
        }
        debug("Finishing getAllWords() method")
    }

    fun insert(word: Word) {
        debug("Inserting new word")
        doAsync {
            wordRepository.insert(word)
        }
    }


}