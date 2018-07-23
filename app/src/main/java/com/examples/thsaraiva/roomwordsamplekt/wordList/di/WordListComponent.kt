package com.examples.thsaraiva.roomwordsamplekt.wordList.di

import com.examples.thsaraiva.roomwordsamplekt.wordList.view.WordListActivity
import dagger.Component

@Component(modules = [(WordListModule::class)])
interface WordListComponent {

    fun inject(wordListActivity: WordListActivity)

}