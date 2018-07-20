package com.examples.thsaraiva.roomwordsamplekt.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.examples.thsaraiva.roomwordsamplekt.R
import com.examples.thsaraiva.roomwordsamplekt.data.Word

class WordListAdapter : RecyclerView.Adapter<WordListAdapter.WordItemViewHolder>() {

    private var wordList: List<Word> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordListAdapter.WordItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        return WordListAdapter.WordItemViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return wordList.count()
    }

    override fun onBindViewHolder(holder: WordListAdapter.WordItemViewHolder, position: Int) {
        holder.bind(wordList[position])
    }

    fun setWords(newWordList: List<Word>) {
        this.wordList = newWordList
        notifyDataSetChanged()
    }

    class WordItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private var wordTextView: TextView = view.findViewById(R.id.word_textview)
        private var noResultsImageView: ImageView = view.findViewById(R.id.no_results_ic)

        fun bind(word: Word) {
            noResultsImageView.visibility = View.GONE
            wordTextView.text = word.word
            wordTextView.visibility = View.VISIBLE
        }

        fun showNoDataPlaceHolder() {
            wordTextView.visibility = View.GONE
            noResultsImageView.visibility = View.VISIBLE

        }

    }
}