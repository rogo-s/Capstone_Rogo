package com.rogo.example.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.rogo.example.core.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert
    suspend fun insert(movieEntity: MovieEntity)

    @Query("SELECT * FROM movie")
    fun getAllFavorite(): Flow<List<MovieEntity>>

    @Delete
    suspend fun delete(movieEntity: MovieEntity)

    @Query("SELECT EXISTS(SELECT * FROM movie WHERE id = :id )")
    fun getFavoriteState(id: Int): Flow<Boolean>
}