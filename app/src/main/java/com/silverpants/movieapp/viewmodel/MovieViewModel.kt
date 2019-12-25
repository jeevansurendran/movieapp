package com.silverpants.movieapp.viewmodel

import android.util.Log
import com.silverpants.movieapp.pojo.movie.Movie
import com.silverpants.movieapp.retrofit.RetroFitRepository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.silverpants.movieapp.FROZEN2
import com.silverpants.movieapp.JOKER

class MovieViewModel : ViewModel() {

    private var retroFitRepository = RetroFitRepository

    private var movieLiveData: LiveData<Movie>? = null

    fun getMovie(id: Int): LiveData<Movie>? {
        if (movieLiveData == null) {
            movieLiveData = retroFitRepository.getMovie(id)
            Log.d("blah", "$movieLiveData")
        }
        return movieLiveData
    }
}
