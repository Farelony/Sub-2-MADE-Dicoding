package com.example.core.data

import com.example.core.data.source.local.LocalDataSource
import com.example.core.data.source.remote.APIResponse
import com.example.core.data.source.remote.RemoteDataSource
import com.example.core.data.source.remote.response.MovieResponse
import com.example.core.domain.model.Movie
import com.example.core.domain.repository.IMovieRepository
import com.example.core.util.AppExecutor
import com.example.core.util.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource : LocalDataSource,
    private val appExecutors: AppExecutor
    ) : IMovieRepository {

    override fun getPopularMovie(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>(appExecutors){
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getPopularMovie().map { DataMapper.mapEntityToDomain(it) }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<APIResponse<List<MovieResponse>>> =
                remoteDataSource.getPopularMovie()

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieEntity = DataMapper.mapResponseToEntity(data)
                localDataSource.insertPopularMovie(movieEntity)
            }
        }.asLiveData()

    override fun getFavMovie(): Flow<List<Movie>> {
        return localDataSource.getFavMovie().map { DataMapper.mapEntityToDomain(it) }
    }

    override fun setFavMovie(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute{ localDataSource.updateMovies(movieEntity,state)}
    }
}