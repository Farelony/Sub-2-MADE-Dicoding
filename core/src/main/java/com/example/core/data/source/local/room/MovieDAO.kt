package com.example.core.data.source.local.room

import androidx.room.*
import com.example.core.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDAO {

    @Query("SELECT * FROM movies")
    fun getMovies() : Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies : List<MovieEntity>)

    @Update
    fun updateMovie(movie : MovieEntity)

    @Query("SELECT * FROM movies WHERE isFav = 1")
    fun getFavMovie() : Flow<List<MovieEntity>>
}