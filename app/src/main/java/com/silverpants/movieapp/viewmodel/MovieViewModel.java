package com.silverpants.movieapp.viewmodel;

import android.util.Log;

import com.silverpants.movieapp.pojo.Result;
import com.silverpants.movieapp.retrofit.MovieRepository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;


public class MovieViewModel extends ViewModel {


    private LiveData<PagedList<Result>> discoverList;
    MovieRepository movieRepository;

    public MovieViewModel() {
        movieRepository = MovieRepository.getInstance();
    }

    public LiveData<PagedList<Result>> getDiscover() {
        if (discoverList == null) {
            discoverList = movieRepository.getDiscover();
        }
        return discoverList;
    }


    public LiveData<PagedList<Result>> getDiscover(int year, String sort_by) {
        if (discoverList == null) {
            discoverList = movieRepository.getDiscover(year, sort_by);
        }
        return discoverList;
    }

    public void dataInvalidate() {
        discoverList = null;
    }

}

