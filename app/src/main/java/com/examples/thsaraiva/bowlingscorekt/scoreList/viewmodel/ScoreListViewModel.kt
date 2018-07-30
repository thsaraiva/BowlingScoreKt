package com.examples.thsaraiva.bowlingscorekt.scoreList.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.examples.thsaraiva.bowlingscorekt.scoreList.repository.ScoreListRepository
import com.examples.thsaraiva.bowlingscorekt.scoreList.repository.dataSource.Score

class ScoreListViewModel(private val repository: ScoreListRepository) : ViewModel() {

    var scoreList = MutableLiveData<List<Score>>()
    var noDataAvailable = MutableLiveData<Boolean>()

    private val loadScoresCallback = object : ScoreListRepository.LoadScoresCallback {
        override fun onScoresLoaded(scores: List<Score>) {
            scoreList.value = scores
        }

        override fun onDataNotAvailable() {
            noDataAvailable.value = true
        }
    }

    private val transactionCompleteCallback = object : ScoreListRepository.TransactionCompleteCallback {
        override fun onTransactionComplete() {
            getAllScores()
        }
    }

    fun getAllScores() {
        repository.getAllScores(loadScoresCallback)
    }

    fun insert(score: Score) {
        repository.insert(score, transactionCompleteCallback)
    }

    fun deleteAll() {
        repository.deleteAll(transactionCompleteCallback)
    }

    fun delete(score: Score) {
        repository.delete(score, transactionCompleteCallback)
    }

    fun update(score: Score) {
        repository.update(score, transactionCompleteCallback)
    }

}