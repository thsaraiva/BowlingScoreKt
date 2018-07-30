package com.examples.thsaraiva.bowlingscorekt.scoreList.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.examples.thsaraiva.bowlingscorekt.scoreList.repository.ScoreListRepository
import com.examples.thsaraiva.bowlingscorekt.scoreList.repository.dataSource.Score
import com.nhaarman.mockitokotlin2.*
import org.junit.Rule
import org.junit.Test

class ScoreListViewModelTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var repository: ScoreListRepository
    private lateinit var viewModel: ScoreListViewModel

    @Test
    fun testGetAllScores_NoResults() {
        repository = mock()
        val scoreListObserver = mock() as Observer<List<Score>>
        val noDataAvailableObserver = mock() as Observer<Boolean>

        with(ScoreListViewModel(repository)) {
            viewModel = this
            scoreList.observeForever(scoreListObserver)
            noDataAvailable.observeForever(noDataAvailableObserver)
            getAllScores()
        }

        argumentCaptor<ScoreListRepository.LoadScoresCallback> {
            verify(repository, times(1)).getAllScores(capture())
            this.firstValue.onDataNotAvailable()
            verify(scoreListObserver, never()).onChanged(any())
            verify(noDataAvailableObserver, times(1)).onChanged(true)
        }

        with(viewModel)
        {
            scoreList.removeObserver(scoreListObserver)
            noDataAvailable.removeObserver(noDataAvailableObserver)
        }
    }

    @Test
    fun testGetAllScores_WithResults() {
        repository = mock()
        val scoreListObserver = mock() as Observer<List<Score>>
        val noDataAvailableObserver = mock() as Observer<Boolean>

        with(ScoreListViewModel(repository)) {
            viewModel = this
            scoreList.observeForever(scoreListObserver)
            noDataAvailable.observeForever(noDataAvailableObserver)
            getAllScores()
        }

        argumentCaptor<ScoreListRepository.LoadScoresCallback> {
            verify(repository, times(1)).getAllScores(capture())
            val expectedList = listOf(Score(score = "Teste", computedScore = 300))
            this.firstValue.onScoresLoaded(expectedList)
            verify(noDataAvailableObserver, never()).onChanged(any())
            verify(scoreListObserver, times(1)).onChanged(expectedList)
        }

        with(viewModel)
        {
            scoreList.removeObserver(scoreListObserver)
            noDataAvailable.removeObserver(noDataAvailableObserver)
        }
    }

    @Test
    fun `inserting Score in database should invoke getAllScores method`() {
        repository = mock()
        val scoreListObserver = mock() as Observer<List<Score>>
        val noDataAvailableObserver = mock() as Observer<Boolean>

        val expectedScore = Score(score = "Teste", computedScore = 300)
        with(ScoreListViewModel(repository)) {
            viewModel = this
            scoreList.observeForever(scoreListObserver)
            noDataAvailable.observeForever(noDataAvailableObserver)
            insert(expectedScore)
        }

        val callbackCaptor = argumentCaptor<ScoreListRepository.TransactionCompleteCallback>()
        verify(repository, times(1)).insert(any(), callbackCaptor.capture())
        callbackCaptor.firstValue.onTransactionComplete()

        val expectedList = listOf(expectedScore)
        argumentCaptor<ScoreListRepository.LoadScoresCallback> {
            verify(repository, times(1)).getAllScores(capture())
            this.firstValue.onScoresLoaded(expectedList)
        }

        verify(noDataAvailableObserver, never()).onChanged(any())
        verify(scoreListObserver, times(1)).onChanged(expectedList)

        with(viewModel)
        {
            scoreList.removeObserver(scoreListObserver)
            noDataAvailable.removeObserver(noDataAvailableObserver)
        }
    }

}