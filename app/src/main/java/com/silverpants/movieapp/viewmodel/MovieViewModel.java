package com.silverpants.movieapp.viewmodel;

import com.silverpants.movieapp.datafactory.MovieDataSourceFactory;
import com.silverpants.movieapp.pojo.Result;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import androidx.paging.PagedList.Config;


public class MovieViewModel extends ViewModel {
    private LiveData<PagedList<Result>> discoverList;
    Executor executor;
    MovieDataSourceFactory dataSourceFactory;

    public LiveData<PagedList<Result>> getDiscover() {
        dataSourceFactory = new MovieDataSourceFactory();
        Config config = new Config.Builder()
                .setPageSize(20)
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(30)
                .setPrefetchDistance(20)
                .build();
        executor = Executors.newFixedThreadPool(5);
        discoverList = new LivePagedListBuilder<>(dataSourceFactory, config).setFetchExecutor(executor).build();
        return discoverList;
    }


    public LiveData<PagedList<Result>> getDiscover(int year, String sort_by) {
        dataSourceFactory = new MovieDataSourceFactory(year, sort_by);
        Config config = new Config.Builder()
                .setPageSize(20)
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(30)
                .setPrefetchDistance(20)
                .build();
        executor = Executors.newFixedThreadPool(5);
        discoverList = new LivePagedListBuilder<>(dataSourceFactory, config).setFetchExecutor(executor).build();
        return discoverList;
    }

}

