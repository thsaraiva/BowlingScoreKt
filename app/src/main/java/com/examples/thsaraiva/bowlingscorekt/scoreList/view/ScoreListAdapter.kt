package com.examples.thsaraiva.bowlingscorekt.scoreList.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.examples.thsaraiva.bowlingscorekt.R
import com.examples.thsaraiva.bowlingscorekt.scoreList.repository.dataSource.Score

class ScoreListAdapter : RecyclerView.Adapter<ScoreListAdapter.WordItemViewHolder>() {

    private var wordList: List<Score> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreListAdapter.WordItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
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

        private var wordTextView: TextView = view.findViewById(R.id.word_textview)
        private var noResultsImageView: ImageView = view.findViewById(R.id.no_results_ic)

        fun bind(score: Score) {
            noResultsImageView.visibility = View.GONE
            wordTextView.text = score.score
            wordTextView.visibility = View.VISIBLE
        }

        fun showNoDataPlaceHolder() {
            wordTextView.visibility = View.GONE
            noResultsImageView.visibility = View.VISIBLE

        }

    }
}