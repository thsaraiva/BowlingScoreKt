package com.examples.thsaraiva.roomwordsamplekt.addNewWord.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.examples.thsaraiva.roomwordsamplekt.R
import kotlinx.android.synthetic.main.add_word_activity.*

class NewWordActivity : AppCompatActivity() {
    companion object {
        const val NEW_WORD = "new_word"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_word_activity)

        button_save.setOnClickListener {

            if (edit_word.text.isEmpty())
                setResult(RESULT_CANCELED)
            else {
                val resultIntent = Intent()
                resultIntent.putExtra(NEW_WORD, edit_word.text.toString())
                setResult(Activity.RESULT_OK, resultIntent)
            }
            finish()
        }
    }

}