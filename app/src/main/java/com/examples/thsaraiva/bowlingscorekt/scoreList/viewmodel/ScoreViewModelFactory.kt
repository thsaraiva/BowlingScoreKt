package com.examples.thsaraiva.bowlingscorekt.scoreList.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.examples.thsaraiva.bowlingscorekt.scoreList.repository.ScoreListRepository

class ScoreViewModelFactory(private val repository: ScoreListRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ScoreListViewModel::class.java)) {
            return ScoreListViewModel(repository) as T
        } else throw IllegalArgumentException("Unknown ViewModel class")
    }
}