package com.example.core.di

import androidx.room.Room
import com.example.core.data.MovieRepository
import com.example.core.data.source.local.LocalDataSource
import com.example.core.data.source.local.room.MovieDatabase
import com.example.core.data.source.remote.RemoteDataSource
import com.example.core.data.source.remote.network.APIEndpoint
import com.example.core.domain.repository.IMovieRepository
import com.example.core.util.AppExecutor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val databaseModule = module{
    factory { get<MovieDatabase>().movieDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            MovieDatabase::class.java,
            "movie.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        val instance = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        instance.create(APIEndpoint::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutor() }
    single<IMovieRepository> {
        MovieRepository(
            get(),
            get(),
            get()
        )
    }
}