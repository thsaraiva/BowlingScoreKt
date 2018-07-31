package com.examples.thsaraiva.bowlingscorekt.scoreList.repository.dataSource

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = Score.TABLE_NAME)
data class Score(@PrimaryKey @ColumnInfo(name = ID_COLUMN_NAME) var id: String = UUID.randomUUID().toString(),
                 @ColumnInfo(name = SCORE_STRING_COLUMN_NAME) var score: String,
                 @ColumnInfo(name = COMPUTED_SCORE_COLUMN_NAME) var computedScore: Int) {

    companion object {
        const val TABLE_NAME = "score_table"
        const val ID_COLUMN_NAME = "id"
        const val SCORE_STRING_COLUMN_NAME = "score_string"
        const val COMPUTED_SCORE_COLUMN_NAME = "computed_score"
    }

}