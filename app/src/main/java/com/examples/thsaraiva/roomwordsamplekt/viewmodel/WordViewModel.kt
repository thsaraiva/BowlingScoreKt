package com.examples.thsaraiva.roomwordsamplekt.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.examples.thsaraiva.roomwordsamplekt.data.Word
import com.examples.thsaraiva.roomwordsamplekt.data.repository.WordRepository

class WordViewModel(app: Application) : AndroidViewModel(app) {

    private var wordRepository = WordRepository(app.applicationContext)
    private var wordList = wordRepository.getAllWords()

    fun getAllWords(): List<Word> {
        wordList = wordRepository.getAllWords()
        return wordList
    }

    fun insert(word: Word) = wordRepository.insert(word)


}