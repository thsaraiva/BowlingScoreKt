package com.examples.thsaraiva.bowlingscorekt.addNewScore.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.examples.thsaraiva.bowlingscorekt.R
import com.examples.thsaraiva.bowlingscorekt.addNewScore.model.NewScore
import com.jakewharton.rxbinding.widget.RxTextView
import kotlinx.android.synthetic.main.add_score_activity.*
import main.ScoreParserArray

class NewScoreActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        private const val MINIMUM_VALID_GAME_STRING_LENGTH = 22
        const val NEW_SCORE = "new_score"
    }

    private var lastComputedScore: NewScore? = null

    private val scoreParser = ScoreParserArray()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_score_activity)

        RxTextView.textChanges(scoreEditText)
                .map {
                    it.toString().count() >= MINIMUM_VALID_GAME_STRING_LENGTH
                }
                .distinctUntilChanged()
                .doOnNext { calculateBtn.isEnabled = it }
                .subscribe()

        saveBtn.setOnClickListener {
            with(scoreEditText.text.toString()) {
                if (this.isEmpty())
                    setResult(RESULT_CANCELED)
                else {
                    val resultIntent = Intent()
                    resultIntent.putExtra(NEW_SCORE, lastComputedScore)
                    setResult(Activity.RESULT_OK, resultIntent)
                }
            }
            finish()
        }

        calculateBtn.setOnClickListener {
            val scoreString = scoreEditText.text.toString()
            if (!scoreString.isEmpty()) {
                val calculatedScore = scoreParser.getScore(scoreString)
                lastComputedScore = NewScore(scoreString, calculatedScore)
                resultScoreLabel.text = getString(R.string.total_score_label, calculatedScore)
                resultLayout.visibility = View.VISIBLE
            }
        }

        clearBtn.setOnClickListener {
            lastComputedScore = null
            scoreEditText.text?.clear()
            resultLayout.visibility = View.GONE
        }
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.key1 -> scoreEditText.append("1")
            R.id.key2 -> scoreEditText.append("2")
            R.id.key3 -> scoreEditText.append("3")
            R.id.key4 -> scoreEditText.append("4")
            R.id.key5 -> scoreEditText.append("5")
            R.id.key6 -> scoreEditText.append("6")
            R.id.key7 -> scoreEditText.append("7")
            R.id.key8 -> scoreEditText.append("8")
            R.id.key9 -> scoreEditText.append("9")
            R.id.keyStrike -> scoreEditText.append("X")
            R.id.keyMiss -> scoreEditText.append("-")
            R.id.keySpare -> scoreEditText.append("/")
            R.id.keyPipe -> scoreEditText.append("|")
            R.id.keyBack -> {
                var str = scoreEditText.text.toString()
                if (str.isNotEmpty()) {
                    scoreEditText.setText(str.substring(0, str.length - 1))
                    lastComputedScore = null
                    resultLayout.visibility = View.GONE
                }
            }
        }
    }
}