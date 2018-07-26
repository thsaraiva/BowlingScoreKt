package com.examples.thsaraiva.bowlingscorekt.scoreList.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.examples.thsaraiva.bowlingscorekt.scoreList.repository.ScoreListRepository
import com.examples.thsaraiva.bowlingscorekt.scoreList.repository.dataSource.Score
import com.nhaarman.mockitokotlin2.*
import org.junit.Rule
import org.junit.Test

class WordListViewModelTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var repository: ScoreListRepository
    private lateinit var viewModel: ScoreListViewModel

    @Test
    fun testGetAllWords_NoResults() {
        repository = mock()
        val wordListObserver = mock() as Observer<List<Score>>
        val noDataAvailableObserver = mock() as Observer<Boolean>

        with(ScoreListViewModel(repository)) {
            viewModel = this
            scoreList.observeForever(wordListObserver)
            noDataAvailable.observeForever(noDataAvailableObserver)
            getAllScores()
        }

        argumentCaptor<ScoreListRepository.LoadScoresCallback> {
            verify(repository, times(1)).getAllWords(capture())
            this.firstValue.onDataNotAvailable()
            verify(wordListObserver, never()).onChanged(any())
            verify(noDataAvailableObserver, times(1)).onChanged(true)
        }

        with(viewModel)
        {
            scoreList.removeObserver(wordListObserver)
            noDataAvailable.removeObserver(noDataAvailableObserver)
        }
    }

    @Test
    fun testGetAllWords_WithResults() {
        repository = mock()
        val wordListObserver = mock() as Observer<List<Score>>
        val noDataAvailableObserver = mock() as Observer<Boolean>

        with(ScoreListViewModel(repository)) {
            viewModel = this
            scoreList.observeForever(wordListObserver)
            noDataAvailable.observeForever(noDataAvailableObserver)
            getAllScores()
        }

        argumentCaptor<ScoreListRepository.LoadScoresCallback> {
            verify(repository, times(1)).getAllWords(capture())
            val expectedList = listOf(Score(score = "Teste", computedScore = 300))
            this.firstValue.onWordsLoaded(expectedList)
            verify(noDataAvailableObserver, never()).onChanged(any())
            verify(wordListObserver, times(1)).onChanged(expectedList)
        }

        with(viewModel)
        {
            scoreList.removeObserver(wordListObserver)
            noDataAvailable.removeObserver(noDataAvailableObserver)
        }
    }

    @Test
    fun `inserting Score in database should invoke getAllWords method`() {
        repository = mock()
        val wordListObserver = mock() as Observer<List<Score>>
        val noDataAvailableObserver = mock() as Observer<Boolean>

        val expectedWord = Score(score = "Teste", computedScore = 300)
        with(ScoreListViewModel(repository)) {
            viewModel = this
            scoreList.observeForever(wordListObserver)
            noDataAvailable.observeForever(noDataAvailableObserver)
            insert(expectedWord)
        }

        val callbackCaptor = argumentCaptor<ScoreListRepository.TransactionCompleteCallback>()
        verify(repository, times(1)).insert(any(), callbackCaptor.capture())
        callbackCaptor.firstValue.onTransactionComplete()

        val expectedList = listOf(expectedWord)
        argumentCaptor<ScoreListRepository.LoadScoresCallback> {
            verify(repository, times(1)).getAllWords(capture())
            this.firstValue.onWordsLoaded(expectedList)
        }

        verify(noDataAvailableObserver, never()).onChanged(any())
        verify(wordListObserver, times(1)).onChanged(expectedList)

        with(viewModel)
        {
            scoreList.removeObserver(wordListObserver)
            noDataAvailable.removeObserver(noDataAvailableObserver)
        }
    }

}