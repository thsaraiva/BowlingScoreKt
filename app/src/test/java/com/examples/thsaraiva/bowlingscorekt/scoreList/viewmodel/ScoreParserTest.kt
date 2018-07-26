package test

import main.ScoreParser
import main.ScoreParserReverse
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ScoreParserTest {

    private lateinit var scoreParser: ScoreParser
    private lateinit var scoreParserReverse: ScoreParserReverse

    @Before
    fun beforeTest() {
        scoreParser = ScoreParser()
        scoreParserReverse = ScoreParserReverse()
    }

    @Test
    fun parsingRandomScoreGame() {
        val framesResults = "X|7/|9-|X|-8|8/|-6|X|X|X||81"
        val expectedResult = 167
        val finalScore = scoreParser.getScore(framesResults)
        assertEquals(expectedResult, finalScore)
    }

    @Test
    fun parsingOnlyOnesScoreGame() {
        val framesResults = "11|11|11|11|11|11|11|11|11|11||"
        val expectedResult = 20
        val finalScore = scoreParser.getScore(framesResults)
        assertEquals(expectedResult, finalScore)
    }

    @Test
    fun parsingRandomScore2Game() {
        val framesResults = "X|7/|X|X|-8|8/|-6|X|5/|2/||2"
        val expectedResult = 146
        val finalScore = scoreParser.getScore(framesResults)
        assertEquals(expectedResult, finalScore)
    }

    @Test
    fun parsingOnlyStrikesGame() {
        val framesResults = "X|X|X|X|X|X|X|X|X|X||XX"
        val expectedResult = 300
        val finalScore = scoreParser.getScore(framesResults)
        assertEquals(expectedResult, finalScore)
    }

    @Test
    fun parsingOnlyNineAndMissesGame() {
        val framesResults = "9-|9-|9-|9-|9-|9-|9-|9-|9-|9-||"
        val expectedResult = 90
        val finalScore = scoreParser.getScore(framesResults)
        assertEquals(expectedResult, finalScore)
    }

    @Test
    fun parsingOnlySparesGame() {
        val framesResults = "5/|5/|5/|5/|5/|5/|5/|5/|5/|5/||5"
        val expectedResult = 150
        val finalScore = scoreParser.getScore(framesResults)
        assertEquals(expectedResult, finalScore)
    }

    @Test
    fun parsingOnlySparesGameNoExtras() {
        val framesResults = "5/|5/|5/|5/|5/|5/|5/|5/|5/|5-||"
        val expectedResult = 140
        val finalScore = scoreParser.getScore(framesResults)
        assertEquals(expectedResult, finalScore)
    }

    @Test
    fun parsingNoStrikesNoSparesNoMissesGame() {
        val framesResults = "12|34|53|81|61|22|52|71|24|15||"
        val expectedResult = 65
        val finalScore = scoreParser.getScore(framesResults)
        assertEquals(expectedResult, finalScore)
    }

    @Test
    fun parsingNoStrikesNoSparesWithMissesGame() {
        val framesResults = "1-|34|5-|81|61|22|52|-1|24|15||"
        val expectedResult = 53
        val finalScore = scoreParser.getScore(framesResults)
        assertEquals(expectedResult, finalScore)
    }

    //################ REVERSE ALGORITHM

    @Test
    fun parsingRandomScoreGame_Reverse() {
        val framesResults = "X|7/|9-|X|-8|8/|-6|X|X|X||81"
        val expectedResult = 167
        val finalScore = scoreParserReverse.getScore(framesResults)
        assertEquals(expectedResult, finalScore)
    }

    @Test
    fun parsingOnlyOnesScoreGame_Reverse() {
        val framesResults = "11|11|11|11|11|11|11|11|11|11||"
        val expectedResult = 20
        val finalScore = scoreParserReverse.getScore(framesResults)
        assertEquals(expectedResult, finalScore)
    }

    @Test
    fun parsingRandomScore2Game_Reverse() {
        val framesResults = "X|7/|X|X|-8|8/|-6|X|5/|2/||2"
        val expectedResult = 146
        val finalScore = scoreParserReverse.getScore(framesResults)
        assertEquals(expectedResult, finalScore)
    }

    @Test
    fun parsingOnlyStrikesGame_Reverse() {
        val framesResults = "X|X|X|X|X|X|X|X|X|X||XX"
        val expectedResult = 300
        val finalScore = scoreParserReverse.getScore(framesResults)
        assertEquals(expectedResult, finalScore)
    }

    @Test
    fun parsingOnlyNineAndMissesGame_Reverse() {
        val framesResults = "9-|9-|9-|9-|9-|9-|9-|9-|9-|9-||"
        val expectedResult = 90
        val finalScore = scoreParserReverse.getScore(framesResults)
        assertEquals(expectedResult, finalScore)
    }

    @Test
    fun parsingOnlySparesGame_Reverse() {
        val framesResults = "5/|5/|5/|5/|5/|5/|5/|5/|5/|5/||5"
        val expectedResult = 150
        val finalScore = scoreParserReverse.getScore(framesResults)
        assertEquals(expectedResult, finalScore)
    }

    @Test
    fun parsingOnlySparesGameNoExtras_Reverse() {
        val framesResults = "5/|5/|5/|5/|5/|5/|5/|5/|5/|5-||"
        val expectedResult = 140
        val finalScore = scoreParserReverse.getScore(framesResults)
        assertEquals(expectedResult, finalScore)
    }

    @Test
    fun parsingNoStrikesNoSparesNoMissesGame_Reverse() {
        val framesResults = "12|34|53|81|61|22|52|71|24|15||"
        val expectedResult = 65
        val finalScore = scoreParserReverse.getScore(framesResults)
        assertEquals(expectedResult, finalScore)
    }

    @Test
    fun parsingNoStrikesNoSparesWithMissesGame_Reverse() {
        val framesResults = "1-|34|5-|81|61|22|52|-1|24|15||"
        val expectedResult = 53
        val finalScore = scoreParserReverse.getScore(framesResults)
        assertEquals(expectedResult, finalScore)
    }

}
