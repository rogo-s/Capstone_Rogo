package com.rogo.example.favorite.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rogo.example.core.domain.usecase.MovieUseCase
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(movieUseCase: MovieUseCase): ViewModel() {
    val getFavorite = movieUseCase.getAllFavorite().asLiveData()
}