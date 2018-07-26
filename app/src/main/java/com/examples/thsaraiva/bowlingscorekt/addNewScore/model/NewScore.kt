package com.examples.thsaraiva.bowlingscorekt.addNewScore.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewScore(val scoreString: String, val computedScore: Int) : Parcelable