package com.examples.thsaraiva.roomwordsamplekt.wordList.di

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import com.examples.thsaraiva.roomwordsamplekt.wordList.repository.WordListRepository
import com.examples.thsaraiva.roomwordsamplekt.wordList.repository.dataSource.WordDao
import com.examples.thsaraiva.roomwordsamplekt.wordList.repository.dataSource.WordRoomDatabase
import com.examples.thsaraiva.roomwordsamplekt.wordList.view.WordListActivity
import com.examples.thsaraiva.roomwordsamplekt.wordList.viewmodel.WordListViewModel
import com.examples.thsaraiva.roomwordsamplekt.wordList.viewmodel.WordViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class WordListModule(private val wordListActivity: WordListActivity) {

    @Provides
    @Named("ApplicationContext")
    fun providesApplicationContext(): Context {
        return wordListActivity.applicationContext
    }

    @Provides
    fun providesWordRoomDatabase(@Named("ApplicationContext") ctx: Context): WordRoomDatabase {
        return WordRoomDatabase.getInstance(ctx)
    }

    @Provides
    fun providesDataSource(database: WordRoomDatabase): WordDao {
        return database.wordDao()
    }

    @Provides
    fun providesWordListRepository(dataSource: WordDao): WordListRepository {
        return WordListRepository(dataSource)
    }

    @Provides
    fun providesWordViewModelFactory(repository: WordListRepository): WordViewModelFactory {
        return WordViewModelFactory(repository)
    }

    @Provides
    fun providesWordViewModel(viewModelFactory: WordViewModelFactory): WordListViewModel {
        return ViewModelProviders.of(wordListActivity, viewModelFactory).get(WordListViewModel::class.java)
    }

}