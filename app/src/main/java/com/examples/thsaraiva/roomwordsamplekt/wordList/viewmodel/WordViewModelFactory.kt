package com.examples.thsaraiva.roomwordsamplekt.wordList.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.examples.thsaraiva.roomwordsamplekt.wordList.repository.WordListRepository

class WordViewModelFactory(private val repository: WordListRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WordListViewModel::class.java)) {
            return WordListViewModel(repository) as T
        } else throw IllegalArgumentException("Unknown ViewModel class")
    }
}