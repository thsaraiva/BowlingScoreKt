package com.examples.thsaraiva.bowlingscorekt.scoreList.di

import com.examples.thsaraiva.bowlingscorekt.scoreList.view.ScoreListActivity
import dagger.Component

@Component(modules = [(ScoreListModule::class)])
interface ScoreListComponent {

    fun inject(scoreListActivity: ScoreListActivity)

}