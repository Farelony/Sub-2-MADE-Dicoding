package com.example.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    var id : Int = 0,
    @SerializedName("backdrop_path")
    var image : String = "",
    var overview : String = "",
    @SerializedName("poster_path")
    var poster : String = "",
    var release_date : String = "",
    var title : String = "",
    var vote_average : Double = 0.0
)
