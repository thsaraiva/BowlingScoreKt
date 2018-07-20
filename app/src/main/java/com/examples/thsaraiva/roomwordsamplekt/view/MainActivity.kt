package com.examples.thsaraiva.roomwordsamplekt.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.examples.thsaraiva.roomwordsamplekt.R
import com.examples.thsaraiva.roomwordsamplekt.data.Word
import com.examples.thsaraiva.roomwordsamplekt.data.repository.WordRepository
import com.examples.thsaraiva.roomwordsamplekt.viewmodel.WordViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast


class MainActivity : AppCompatActivity(), WordRepository.GetWordsCallback {
    companion object {
        private const val NEW_WORD_ACTIVITY_REQUEST_CODE = 1
    }

    private lateinit var wordViewModel: WordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        wordViewModel = ViewModelProviders.of(this).get(WordViewModel::class.java)

        wordsRecyclerView.layoutManager = LinearLayoutManager(this)
        wordsRecyclerView.adapter = WordListAdapter()
        wordViewModel.getAllWords(this)

        fab.setOnClickListener {
            startActivityForResult(Intent(this@MainActivity, NewWordActivity::class.java), NEW_WORD_ACTIVITY_REQUEST_CODE)
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
        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val result = data!!.getStringExtra(NewWordActivity.NEW_WORD)
            val newWord = Word(word = result)
            wordViewModel.insert(newWord)
            toast(R.string.word_saved)
            wordViewModel.getAllWords(this)
        } else {
            longToast(R.string.empty_not_saved)
        }
    }

    override fun onResult(wordList: List<Word>) {
        (wordsRecyclerView.adapter as WordListAdapter).setWords(wordList)
    }
}
