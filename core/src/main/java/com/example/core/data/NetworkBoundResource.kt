package com.example.core.data

import com.example.core.data.source.remote.APIResponse
import com.example.core.util.AppExecutor
import kotlinx.coroutines.flow.*

abstract class NetworkBoundResource<ResultType,RequestType>(private val executor: AppExecutor) {

    private var result: Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())
        val dbSource = loadFromDB().first()
        if (shouldFetch(dbSource)) {
            emit(Resource.Loading())
            when (val apiResponse = createCall().first()) {
                is APIResponse.Success -> {
                    saveCallResult(apiResponse.data)
                    emitAll(loadFromDB().map { Resource.Success(it) })
                }
                is APIResponse.Empty -> {
                    emitAll(loadFromDB().map { Resource.Success(it) })
                }
                is APIResponse.Error -> {
                    onFetchFailed()
                    emit(Resource.Error<ResultType>(apiResponse.errorMessage))
                }
            }
        } else {
            emitAll(loadFromDB().map { Resource.Success(it) })
        }
    }

    protected open fun onFetchFailed() {}
    protected abstract fun loadFromDB() : Flow<ResultType>
    protected abstract fun shouldFetch(data : ResultType?) : Boolean
    protected abstract suspend fun createCall() : Flow<APIResponse<RequestType>>
    protected abstract suspend fun saveCallResult(data : RequestType)

    fun asLiveData() : Flow<Resource<ResultType>> = result
}