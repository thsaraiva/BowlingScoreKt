package com.examples.thsaraiva.bowlingscorekt.scoreList.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.examples.thsaraiva.bowlingscorekt.R
import com.examples.thsaraiva.bowlingscorekt.scoreList.repository.dataSource.Score

class ScoreListAdapter : RecyclerView.Adapter<ScoreListAdapter.WordItemViewHolder>() {

    private var wordList: List<Score> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreListAdapter.WordItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.score_list_item_layout, parent, false)
        return ScoreListAdapter.WordItemViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return wordList.count()
    }

    override fun onBindViewHolder(holder: ScoreListAdapter.WordItemViewHolder, position: Int) {
        holder.bind(wordList[position])
    }

    fun setWords(newWordList: List<Score>) {
        this.wordList = newWordList
        notifyDataSetChanged()
    }

    class WordItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private var scoreString: TextView = view.findViewById(R.id.score_string)
        private var computedScore: TextView = view.findViewById(R.id.computed_score)

        fun bind(score: Score) {
            with(scoreString) {
                text = score.score
                visibility = View.VISIBLE
            }
            computedScore.text = score.computedScore.toString()
        }
    }
}