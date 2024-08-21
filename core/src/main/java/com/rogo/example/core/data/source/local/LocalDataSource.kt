package com.rogo.example.core.data.source.local

import com.rogo.example.core.data.source.local.entity.MovieEntity
import com.rogo.example.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val movieDao: MovieDao) {
    suspend fun insert(movieEntity: MovieEntity) = movieDao.insert(movieEntity)

    fun getAllFavorite(): Flow<List<MovieEntity>> = movieDao.getAllFavorite()

    suspend fun delete(movieEntity: MovieEntity) = movieDao.delete(movieEntity)

    fun getFavoriteState(id: Int): Flow<Boolean> = movieDao.getFavoriteState(id)
}