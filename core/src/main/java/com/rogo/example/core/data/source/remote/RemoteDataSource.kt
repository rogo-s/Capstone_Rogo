package com.rogo.example.core.data.source.remote

import android.util.Log
import com.rogo.example.core.data.source.remote.network.ApiResponse
import com.rogo.example.core.data.source.remote.network.ApiService
import com.rogo.example.core.data.source.remote.response.MovieResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.Dispatchers
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {
    suspend fun getMovieNowPlaying(): Flow<ApiResponse<MovieResponse>> {
        return flow {
            try {
                val response = apiService.getMovieNowPlaying()
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getMoviePopular(): Flow<ApiResponse<MovieResponse>> {
        return flow {
            try {
                val response = apiService.getMoviePopular()
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getMovieTopRated(): Flow<ApiResponse<MovieResponse>> {
        return flow {
            try {
                val response = apiService.getMovieTopRated()
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

}