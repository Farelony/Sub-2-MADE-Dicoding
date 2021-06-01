package com.example.core.data.source.remote

import android.util.Log
import com.example.core.data.source.remote.network.APIEndpoint
import com.example.core.data.source.remote.response.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiEndPoint : APIEndpoint){

    companion object{
        const val TOKEN = "1583162b609c35f0e7ca4b216dc0616e"
    }

    fun getPopularMovie() : Flow<APIResponse<List<MovieResponse>>> {
        return flow{
            try {
                val response =apiEndPoint.getPopularMovie(TOKEN)
                val dataArray = response.results
                if(dataArray.isNotEmpty()){
                    emit(APIResponse.Success(response.results as List<MovieResponse>))
                }else{
                    emit(APIResponse.Empty)
                }
            }catch (e : Exception){
                emit(APIResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}