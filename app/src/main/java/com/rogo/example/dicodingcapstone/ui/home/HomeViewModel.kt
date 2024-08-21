package com.rogo.example.dicodingcapstone.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rogo.example.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val movieUseCase: MovieUseCase) : ViewModel() {
    val getMovieNow = movieUseCase.getNowMoviePlaying().asLiveData()
    val getMoviePopular = movieUseCase.getMoviePopular().asLiveData()
    val getMovieTopRated = movieUseCase.getMovieTopRated().asLiveData()
}