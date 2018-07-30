package com.examples.thsaraiva.bowlingscorekt.scoreList.repository

import com.examples.thsaraiva.bowlingscorekt.scoreList.repository.dataSource.Score
import com.examples.thsaraiva.bowlingscorekt.scoreList.repository.dataSource.ScoreDao
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class ScoreListRepository(private val dataSource: ScoreDao) {

    interface LoadScoresCallback {

        fun onScoresLoaded(scores: List<Score>)

        fun onDataNotAvailable()
    }

    interface TransactionCompleteCallback {

        fun onTransactionComplete()

    }

    fun getAllScores(callback: LoadScoresCallback) {
        doAsync {
            val scores = dataSource.getAllScores()
            uiThread {
                if (scores.isEmpty())
                    callback.onDataNotAvailable()
                else
                    callback.onScoresLoaded(scores)
            }
        }
    }

    fun insert(score: Score, callback: TransactionCompleteCallback) {
        doAsync {
            dataSource.insert(score)
            uiThread {
                callback.onTransactionComplete()
            }
        }
    }

    fun delete(score: Score, callback: TransactionCompleteCallback) {
        doAsync {
            dataSource.delete(score)
            uiThread {
                callback.onTransactionComplete()
            }
        }
    }

    fun deleteAll(callback: TransactionCompleteCallback) {
        doAsync {
            dataSource.deleteAll()
            uiThread {
                callback.onTransactionComplete()
            }
        }
    }

    fun update(score: Score, callback: TransactionCompleteCallback) {
        doAsync {
            dataSource.update(score)
            uiThread {
                callback.onTransactionComplete()
            }
        }
    }
}