package com.rogo.example.core.di

import android.content.Context
import androidx.room.Room
import com.rogo.example.core.data.source.local.room.MovieDao
import com.rogo.example.core.data.source.local.room.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): MovieDatabase {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("dicoding".toCharArray())
        val factory = SupportFactory(passphrase)
        return Room.databaseBuilder(
            context,
            MovieDatabase::class.java,
            "movie.db"
        ).openHelperFactory(factory)
            .fallbackToDestructiveMigration()
            .build()
    }


    @Provides
    fun provideMovieDao(movieDatabase: MovieDatabase): MovieDao =
        movieDatabase.movieDao()
}