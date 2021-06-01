package com.example.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("id")
    var id : Int = 0,
    @SerializedName("backdrop_path")
    var image : String = "",
    @SerializedName("overview")
    var overview : String = "",
    @SerializedName("poster_path")
    var poster : String = "",
    @SerializedName("release_date")
    var release_date : String = "",
    @SerializedName("title")
    var title : String = "",
    @SerializedName("vote_average")
    var vote_average : Double = 0.0
)
