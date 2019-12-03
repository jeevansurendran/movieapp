package com.silverpants.movieapp.datafactory;


import com.silverpants.movieapp.pojo.Result;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

public class MovieDataSourceFactory extends DataSource.Factory<Integer, Result> {
    MovieDataSource movieDataSource;
    MutableLiveData<MovieDataSource> sourceMutableLiveData = new MutableLiveData<>();

    public MovieDataSourceFactory() {
        movieDataSource = new MovieDataSource();
    }

    public MovieDataSourceFactory(int year, String sort_by) {
        movieDataSource = new MovieDataSource(year, sort_by);
    }

    @NonNull
    @Override
    public DataSource<Integer, Result> create() {
        sourceMutableLiveData.postValue(movieDataSource);
        return movieDataSource;
    }
}
