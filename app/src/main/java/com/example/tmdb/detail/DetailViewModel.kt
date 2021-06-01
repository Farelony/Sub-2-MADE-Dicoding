package com.example.tmdb.detail

import androidx.lifecycle.ViewModel
import com.example.core.domain.model.Movie
import com.example.core.domain.usecase.MovieUseCase

class DetailViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {
    fun setFavMovie(movie : Movie, state : Boolean){
        val newState = !state
        movieUseCase.setFavoriteMovie(movie,newState)
    }
}