package com.example.tmdb.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.domain.usecase.MovieUseCase

class MovieViewModel(movieUseCase : MovieUseCase) : ViewModel(){
    val latestMovie = movieUseCase.getPopularMovie().asLiveData()
}