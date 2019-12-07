package com.silverpants.movieapp.viewmodel;

import com.silverpants.movieapp.Keys;
import com.silverpants.movieapp.pojo.Movie;
import com.silverpants.movieapp.retrofit.RetroFitRepository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class MovieDetailsViewModel extends ViewModel {
    private LiveData<Movie> movieLiveData;

    RetroFitRepository retroFitRepository;

    public MovieDetailsViewModel() {
        retroFitRepository = RetroFitRepository.getInstance();
    }

    public LiveData<Movie> getMovie() {
        if (movieLiveData == null) {
            movieLiveData = retroFitRepository.getMovie(Keys.JOKER);
        }
        return movieLiveData;
    }
}
