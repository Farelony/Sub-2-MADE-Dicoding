package com.example.core.data.source.remote.network

import com.example.core.data.source.remote.response.ListMovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface APIEndpoint {

    @GET("movie/popular")
    suspend fun getPopularMovie(
        @Query("api_key") apiKey : String
    ) : ListMovieResponse

}