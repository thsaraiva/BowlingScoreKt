package com.examples.thsaraiva.roomwordsamplekt.wordList.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.examples.thsaraiva.roomwordsamplekt.R
import com.examples.thsaraiva.roomwordsamplekt.addNewWord.view.NewWordActivity
import com.examples.thsaraiva.roomwordsamplekt.wordList.di.DaggerWordListComponent
import com.examples.thsaraiva.roomwordsamplekt.wordList.di.WordListModule
import com.examples.thsaraiva.roomwordsamplekt.wordList.repository.dataSource.Word
import com.examples.thsaraiva.roomwordsamplekt.wordList.viewmodel.WordListViewModel
import kotlinx.android.synthetic.main.word_list_activity_layout.*
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast
import javax.inject.Inject


class WordListActivity : AppCompatActivity() {
    companion object {
        private const val NEW_WORD_ACTIVITY_REQUEST_CODE = 1
    }

    @Inject
    lateinit var wordViewModel: WordListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.word_list_activity_layout)
        setSupportActionBar(toolbar)

        val wordListComponent = DaggerWordListComponent.builder()
                .wordListModule(WordListModule(this))
                .build()

        wordListComponent.inject(this)

        wordViewModel.getAllWords().observe(this, Observer { (wordsRecyclerView.adapter as WordListAdapter).setWords(it) })

        wordsRecyclerView.layoutManager = LinearLayoutManager(this)
        wordsRecyclerView.adapter = WordListAdapter()

        fab.setOnClickListener {
            startActivityForResult(Intent(this@WordListActivity, NewWordActivity::class.java), NEW_WORD_ACTIVITY_REQUEST_CODE)
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
        } else {
            longToast(R.string.empty_not_saved)
        }
    }
}
