package com.rogo.example.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int?,
    @ColumnInfo(name = "image")
    val image: String?,
    @ColumnInfo(name = "title")
    val title: String?,
    @ColumnInfo(name = "overview")
    val overview: String?,
    @ColumnInfo(name = "movieImage")
    val movieImage: String?,
    @ColumnInfo(name = "isFavorite")
    val isFavorite: Boolean?,
)