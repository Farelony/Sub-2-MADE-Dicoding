package com.example.core.domain.repository

import com.example.core.data.Resource
import com.example.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    fun getPopularMovie() : Flow<Resource<List<Movie>>>
    fun getFavMovie() : Flow<List<Movie>>
    fun setFavMovie(movie: Movie, state : Boolean)
}