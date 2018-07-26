package com.examples.thsaraiva.bowlingscorekt.scoreList.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.examples.thsaraiva.bowlingscorekt.R
import com.examples.thsaraiva.bowlingscorekt.addNewScore.model.NewScore
import com.examples.thsaraiva.bowlingscorekt.addNewScore.view.NewScoreActivity
import com.examples.thsaraiva.bowlingscorekt.scoreList.di.DaggerScoreListComponent
import com.examples.thsaraiva.bowlingscorekt.scoreList.di.ScoreListModule
import com.examples.thsaraiva.bowlingscorekt.scoreList.repository.dataSource.Score
import com.examples.thsaraiva.bowlingscorekt.scoreList.viewmodel.ScoreListViewModel
import kotlinx.android.synthetic.main.score_list_activity_layout.*
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast
import javax.inject.Inject


class ScoreListActivity : AppCompatActivity() {
    companion object {
        private const val NEW_SCORE_ACTIVITY_REQUEST_CODE = 1
    }

    @Inject
    lateinit var scoreViewModel: ScoreListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.score_list_activity_layout)

        val scoreListComponent = DaggerScoreListComponent.builder()
                .scoreListModule(ScoreListModule(this))
                .build()

        scoreListComponent.inject(this)

        with(scoreViewModel) {
            scoreList.observe(this@ScoreListActivity, Observer {
                no_results_place_holder.visibility = GONE
                (scoresRecyclerView.adapter as ScoreListAdapter).setWords(it)
                scoresRecyclerView.visibility = VISIBLE
            })
            noDataAvailable.observe(this@ScoreListActivity, Observer {
                if (it) {
                    scoresRecyclerView.visibility = GONE
                    no_results_place_holder.visibility = VISIBLE
                }
            })
            getAllScores()
        }

        scoresRecyclerView.layoutManager = LinearLayoutManager(this)
        scoresRecyclerView.adapter = ScoreListAdapter()

        fab.setOnClickListener {
            startActivityForResult(Intent(this@ScoreListActivity, NewScoreActivity::class.java), NEW_SCORE_ACTIVITY_REQUEST_CODE)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == NEW_SCORE_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val result = data!!.getParcelableExtra(NewScoreActivity.NEW_SCORE) as NewScore
            val newWord = Score(score = result.scoreString, computedScore = result.computedScore)
            scoreViewModel.insert(newWord)
            toast(R.string.score_saved)
        } else {
            longToast(R.string.empty_not_saved)
        }
    }
}

