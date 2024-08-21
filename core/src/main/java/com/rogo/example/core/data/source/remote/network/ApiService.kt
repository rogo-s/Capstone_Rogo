package com.rogo.example.core.data.source.remote.network

import com.rogo.example.core.data.source.remote.response.MovieResponse
import retrofit2.http.GET

interface ApiService {

    @GET("now_playing?api_key=$api_key")
    suspend fun getMovieNowPlaying(): MovieResponse

    @GET("popular?api_key=$api_key")
    suspend fun getMoviePopular(): MovieResponse

    @GET("top_rated?api_key=$api_key")
    suspend fun getMovieTopRated(): MovieResponse

    companion object {
        private const val api_key = "13ab7ff7b9f6a561e9ca3c8e9e707f0e"
    }
}