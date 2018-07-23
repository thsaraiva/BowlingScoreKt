package com.examples.thsaraiva.roomwordsamplekt.wordList.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.examples.thsaraiva.roomwordsamplekt.wordList.repository.WordListRepository
import com.examples.thsaraiva.roomwordsamplekt.wordList.repository.dataSource.Word
import org.jetbrains.anko.doAsync
import java.util.concurrent.Future

class WordListViewModel(private val repository: WordListRepository) : ViewModel() {

    private var wordList = MutableLiveData<List<Word>>()

    init {
        forceReloadData()
    }

    fun getAllWords(forceReloadData: Boolean = false): LiveData<List<Word>> {
        if (forceReloadData) {
            Log.d("THIAGO", "FORCE RELOAD")
            forceReloadData()
        }
        Log.d("THIAGO", "RETURNING LIST")
        return wordList
    }

    private fun forceReloadData(): Future<Unit> {
        return doAsync {
            val result = repository.getAllWords()
            wordList.postValue(result)
        }
    }

    fun insert(word: Word) = doAsync {
        repository.insert(word)
        wordList.postValue(repository.getAllWords())
    }

    fun delete(word: Word) = doAsync {
        repository.delete(word)
        wordList.postValue(repository.getAllWords())
    }

    fun deleteAll() = doAsync {
        repository.deleteAll()
        wordList.postValue(repository.getAllWords())
    }

    fun update(word: Word) = doAsync {
        repository.update(word)
        wordList.postValue(repository.getAllWords())
    }

}