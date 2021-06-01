package com.example.core.util

import com.example.core.data.source.local.entity.MovieEntity
import com.example.core.data.source.remote.response.MovieResponse
import com.example.core.domain.model.Movie

object DataMapper {
    fun mapResponseToEntity(input: List<MovieResponse>) : List<MovieEntity>{
        val movies = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                id = it.id,
                image = it.image,
                overview = it.overview,
                poster = it.poster,
                release_date = it.release_date,
                title = it.title,
                vote_average = it.vote_average,
            )
            movies.add(movie)
        }
        return movies
    }

    fun mapEntityToDomain(input : List<MovieEntity>) : List<Movie> =
        input.map{
            Movie(
                id = it.id,
                image = it.image,
                overview = it.overview,
                poster = it.poster,
                release_date = it.release_date,
                title = it.title,
                vote_average = it.vote_average,
                isFav = it.isFav
            )
        }

    fun mapDomainToEntity(input : Movie) = MovieEntity(
        id = input.id,
        image = input.image,
        overview = input.overview,
        poster = input.poster,
        release_date = input.release_date,
        title = input.title,
        vote_average = input.vote_average,
        isFav = input.isFav
    )
}