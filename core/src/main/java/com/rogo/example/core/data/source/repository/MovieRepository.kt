package com.rogo.example.core.data.source.repository

import com.rogo.example.core.data.source.NetworkBoundResource
import com.rogo.example.core.data.source.Resource
import com.rogo.example.core.data.source.local.LocalDataSource
import com.rogo.example.core.data.source.remote.RemoteDataSource
import com.rogo.example.core.data.source.remote.network.ApiResponse
import com.rogo.example.core.data.source.remote.response.MovieResponse
import com.rogo.example.core.domain.model.Movie
import com.rogo.example.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

interface MovieRepository {
    fun getMovieNowPlaying(): Flow<Resource<List<Movie>>>
    fun getMoviePopular(): Flow<Resource<List<Movie>>>
    fun getMovieTopRated(): Flow<Resource<List<Movie>>>
    suspend fun insertMovieToDB(movie: Movie)
    fun getAllFavoriteMovieFromDB(): Flow<List<Movie>>
    suspend fun deleteMovieFromDB(movie: Movie)
    fun getFavoriteStateMovieFromDB(id: Int): Flow<Boolean>
}

@Singleton
class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : MovieRepository {
    override fun getMovieNowPlaying(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, MovieResponse>() {
            override suspend fun createCall(): Flow<ApiResponse<MovieResponse>> =
                remoteDataSource.getMovieNowPlaying()

            override fun loadFromNetwork(data: MovieResponse): Flow<List<Movie>> =
                DataMapper.mapListResponseToDomain(data.results)
        }.asFlow()

    override fun getMoviePopular(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, MovieResponse>() {
            override suspend fun createCall(): Flow<ApiResponse<MovieResponse>> =
                remoteDataSource.getMoviePopular()

            override fun loadFromNetwork(data: MovieResponse): Flow<List<Movie>> =
                DataMapper.mapListResponseToDomain(data.results)
        }.asFlow()

    override fun getMovieTopRated(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, MovieResponse>() {
            override suspend fun createCall(): Flow<ApiResponse<MovieResponse>> =
                remoteDataSource.getMovieTopRated()

            override fun loadFromNetwork(data: MovieResponse): Flow<List<Movie>> =
                DataMapper.mapListResponseToDomain(data.results)
        }.asFlow()

    override suspend fun insertMovieToDB(movie: Movie) =
        localDataSource.insert(DataMapper.mapDomainToEntity(movie))

    override fun getAllFavoriteMovieFromDB(): Flow<List<Movie>> =
        localDataSource.getAllFavorite().map {
            DataMapper.mapListEntityToDomain(it)
        }

    override suspend fun deleteMovieFromDB(movie: Movie) =
        localDataSource.delete(DataMapper.mapDomainToEntity(movie))

    override fun getFavoriteStateMovieFromDB(id: Int): Flow<Boolean> =
        localDataSource.getFavoriteState(id)

}