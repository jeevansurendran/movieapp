package com.silverpants.movieapp.viewmodel;

import com.silverpants.movieapp.pojo.movie.Movie;
import com.silverpants.movieapp.retrofit.RetroFitRepository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class MovieViewModel extends ViewModel {
    private LiveData<Movie> movieLiveData;

    RetroFitRepository retroFitRepository;

    public MovieViewModel() {
        retroFitRepository = RetroFitRepository.getInstance();
    }

    public LiveData<Movie> getMovie(int id) {
        if (movieLiveData == null) {
            movieLiveData = retroFitRepository.getMovie(id);
        }
        return movieLiveData;
    }
}
