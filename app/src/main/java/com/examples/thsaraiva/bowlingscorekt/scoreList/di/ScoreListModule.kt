package com.examples.thsaraiva.bowlingscorekt.scoreList.di

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import com.examples.thsaraiva.bowlingscorekt.scoreList.repository.ScoreListRepository
import com.examples.thsaraiva.bowlingscorekt.scoreList.repository.dataSource.BowlingScoreRoomDatabase
import com.examples.thsaraiva.bowlingscorekt.scoreList.repository.dataSource.ScoreDao
import com.examples.thsaraiva.bowlingscorekt.scoreList.view.ScoreListActivity
import com.examples.thsaraiva.bowlingscorekt.scoreList.viewmodel.ScoreListViewModel
import com.examples.thsaraiva.bowlingscorekt.scoreList.viewmodel.ScoreViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class ScoreListModule(private val scoreListActivity: ScoreListActivity) {

    @Provides
    @Named("ApplicationContext")
    fun providesApplicationContext(): Context {
        return scoreListActivity.applicationContext
    }

    @Provides
    fun providesScoreRoomDatabase(@Named("ApplicationContext") ctx: Context): BowlingScoreRoomDatabase {
        return BowlingScoreRoomDatabase.getInstance(ctx)
    }

    @Provides
    fun providesDataSource(database: BowlingScoreRoomDatabase): ScoreDao {
        return database.scoreDao()
    }

    @Provides
    fun providesScoreListRepository(dataSource: ScoreDao): ScoreListRepository {
        return ScoreListRepository(dataSource)
    }

    @Provides
    fun providesScoreViewModelFactory(repository: ScoreListRepository): ScoreViewModelFactory {
        return ScoreViewModelFactory(repository)
    }

    @Provides
    fun providesWordViewModel(viewModelFactory: ScoreViewModelFactory): ScoreListViewModel {
        return ViewModelProviders.of(scoreListActivity, viewModelFactory).get(ScoreListViewModel::class.java)
    }

}