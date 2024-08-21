package com.rogo.example.core.data.source

import com.rogo.example.core.data.source.remote.network.ApiResponse
import kotlinx.coroutines.flow.*

abstract class NetworkBoundResource<ResultType, RequestType> {
    private val result: Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())
        when (val apiResponse = createCall().first()) {
            is ApiResponse.Success -> {
                emitAll(loadFromNetwork(apiResponse.data).map { resultType ->
                    Resource.Success(resultType)
                })
            }
            is ApiResponse.Error -> {
                emit(Resource.Error(apiResponse.errorMessage))
            }

            else -> {}
        }
    }

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    protected abstract fun loadFromNetwork(data: RequestType): Flow<ResultType>

    fun asFlow(): Flow<Resource<ResultType>> = result
}