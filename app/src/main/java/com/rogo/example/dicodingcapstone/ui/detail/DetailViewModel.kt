package com.rogo.example.dicodingcapstone.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.rogo.example.core.domain.model.Movie
import com.rogo.example.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val movieUseCase: MovieUseCase) : ViewModel() {
    fun insertFavorite(movie: Movie) = viewModelScope.launch {
        movieUseCase.insert(movie)
    }

    fun deleteFavorite(movie: Movie) = viewModelScope.launch {
        movieUseCase.delete(movie)
    }

    fun getFavoriteState(id: Int) = movieUseCase.getFavoriteState(id).asLiveData()
}