package com.example.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    var id : Int = 0,
    var image : String = "",
    var overview : String = "",
    var poster : String = "",
    var release_date : String = "",
    var title : String = "",
    var vote_average : Double = 0.0,
    var isFav : Boolean = false
) : Parcelable
