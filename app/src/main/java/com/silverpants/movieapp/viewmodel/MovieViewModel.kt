package com.silverpants.movieapp.viewmodel

import com.silverpants.movieapp.pojo.movie.Movie
import com.silverpants.movieapp.retrofit.RetroFitRepository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.silverpants.movieapp.FROZEN2
import com.silverpants.movieapp.JOKER

class MovieViewModel : ViewModel() {

    private var retroFitRepository = RetroFitRepository

    private var movieLiveData: LiveData<Movie> = retroFitRepository.getMovie(FROZEN2)

    fun getMovie(id: Int): LiveData<Movie> {
        movieLiveData = retroFitRepository.getMovie(id)
        return movieLiveData
    }
}
