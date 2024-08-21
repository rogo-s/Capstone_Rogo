package com.rogo.example.core.domain.usecase

import com.rogo.example.core.data.source.Resource
import com.rogo.example.core.data.source.repository.MovieRepository
import com.rogo.example.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieInteractor @Inject constructor(private val movieRepository: MovieRepository) : MovieUseCase {
    override fun getNowMoviePlaying(): Flow<Resource<List<Movie>>> = movieRepository.getMovieNowPlaying()
    override fun getMoviePopular(): Flow<Resource<List<Movie>>> = movieRepository.getMoviePopular()
    override fun getMovieTopRated(): Flow<Resource<List<Movie>>> = movieRepository.getMovieTopRated()
    override suspend fun insert(movie: Movie) = movieRepository.insertMovieToDB(movie)
    override fun getAllFavorite(): Flow<List<Movie>> = movieRepository.getAllFavoriteMovieFromDB()
    override suspend fun delete(movie: Movie) = movieRepository.deleteMovieFromDB(movie)
    override fun getFavoriteState(id: Int): Flow<Boolean> = movieRepository.getFavoriteStateMovieFromDB(id)
}