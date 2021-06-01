package com.example.core.data.source.local

import com.example.core.data.source.local.entity.MovieEntity
import com.example.core.data.source.local.room.MovieDAO
import kotlinx.coroutines.flow.Flow

class LocalDataSource (private val movieDAO: MovieDAO) {

    fun getPopularMovie() : Flow<List<MovieEntity>> = movieDAO.getMovies()
    suspend fun insertPopularMovie(movies : List<MovieEntity>) = movieDAO.insertMovies(movies)
    fun updateMovies(movie : MovieEntity, isFav : Boolean){
        movie.isFav = isFav
        movieDAO.updateMovie(movie)
    }
    fun getFavMovie() : Flow<List<MovieEntity>> = movieDAO.getFavMovie()
}