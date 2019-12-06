package com.silverpants.movieapp.viewmodel;

import com.silverpants.movieapp.Keys;
import com.silverpants.movieapp.pojo.Movie;
import com.silverpants.movieapp.retrofit.MovieRepository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class MovieDetailsViewModel extends ViewModel {
    private LiveData<Movie> movieLiveData;

    MovieRepository movieRepository;

    public MovieDetailsViewModel() {
        movieRepository = MovieRepository.getInstance();
    }

    public LiveData<Movie> getMovie() {
        if (movieLiveData == null) {
            movieLiveData = movieRepository.getMovie(Keys.ENDGAME);
        }
        return movieLiveData;
    }
}
