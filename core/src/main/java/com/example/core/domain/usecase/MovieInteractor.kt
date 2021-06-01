package com.example.core.domain.usecase

import com.example.core.data.Resource
import com.example.core.domain.model.Movie
import com.example.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow

class MovieInteractor(private val movieRepository: IMovieRepository) : MovieUseCase {

    override fun getPopularMovie(): Flow<Resource<List<Movie>>> = movieRepository.getPopularMovie()

    override fun getFavoriteMovie(): Flow<List<Movie>> = movieRepository.getFavMovie()

    override fun setFavoriteMovie(movie: Movie, state: Boolean) = movieRepository.setFavMovie(movie,state)

}