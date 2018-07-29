package com.examples.thsaraiva.bowlingscorekt

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import com.examples.thsaraiva.bowlingscorekt.scoreList.repository.dataSource.BowlingScoreRoomDatabase
import com.examples.thsaraiva.bowlingscorekt.scoreList.repository.dataSource.Score
import com.examples.thsaraiva.bowlingscorekt.scoreList.repository.dataSource.ScoreDao
import org.junit.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 */
@RunWith(AndroidJUnit4::class)
class ScoreDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    companion object {
        private val SCORE1 = Score(score = "MY SCORE STRING 1", computedScore = 100)
        private val SCORE2 = Score(score = "MY SCORE STRING 2", computedScore = 200)
    }

    private lateinit var database: BowlingScoreRoomDatabase
    private lateinit var scoreDao: ScoreDao

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.examples.thsaraiva.roomwordsamplekt", appContext.packageName)
    }

    @Before
    fun initDatabase() {
        database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(), BowlingScoreRoomDatabase::class.java)
                .allowMainThreadQueries().build()
        scoreDao = database.scoreDao()
    }

    @After
    fun cleanUp() {
        database.close()
    }

    @Test
    fun testPreConditions() {
        Assert.assertNotNull(scoreDao)
    }

    @Test
    fun testRetrievingScoreListFromEmptyDb() {
        val allScores = scoreDao.getAllScores()
        assertTrue(allScores.isEmpty())
    }

    @Test
    fun testInsertingScoreInDb() {
        assertTrue(scoreDao.getAllScores().isEmpty())
        scoreDao.insert(SCORE1)
        val newScoreList = scoreDao.getAllScores()
        assertTrue(newScoreList.size == 1)
        assertEquals(SCORE1.computedScore, newScoreList[0].computedScore)
        assertEquals(SCORE1.score, newScoreList[0].score)
    }

    @Test
    fun testDeletingScoreFromDb() {
        assertTrue(scoreDao.getAllScores().isEmpty())
        scoreDao.insert(SCORE1)
        scoreDao.insert(SCORE2)
        assertTrue(scoreDao.getAllScores().size == 2)
        scoreDao.delete(SCORE1)
        Thread.sleep(2000)
        val newScoreList = scoreDao.getAllScores()
        assertTrue(newScoreList.size == 1)
        assertEquals(SCORE2.computedScore, newScoreList[0].computedScore)
        assertEquals(SCORE2.score, newScoreList[0].score)
    }

}
