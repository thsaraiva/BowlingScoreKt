package com.examples.thsaraiva.bowlingscorekt.scoreList.repository.dataSource

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface ScoreDao {

    @Query("SELECT * FROM ${Score.TABLE_NAME} order by ${Score.COMPUTED_SCORE_COLUMN_NAME} ASC")
    fun getAllScores(): List<Score>

    @Insert(onConflict = REPLACE)
    fun insert(score: Score)

    @Query("DELETE FROM ${Score.TABLE_NAME}")
    fun deleteAll()

    @Delete
    fun delete(score: Score)

    @Update
    fun update(score: Score)
}
