package com.silverpants.movieapp.retrofit;

import android.util.Log;

import com.silverpants.movieapp.datafactory.MovieDataSourceFactory;
import com.silverpants.movieapp.pojo.Movie;
import com.silverpants.movieapp.pojo.Result;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetroFitRepository {

    private static RetroFitRepository retroFitRepository;
    private RetroFitApi retroFitApi;

    public RetroFitApi getRetroFitApi() {
        return retroFitApi;
    }

    MovieDataSourceFactory dataSourceFactory;
    Executor executor;

    public static RetroFitRepository getInstance() {

        if (retroFitRepository == null) {
            synchronized (RetroFitRepository.class) {
                retroFitRepository = new RetroFitRepository();
            }
        }
        return retroFitRepository;
    }

    private RetroFitRepository() {
        retroFitApi = RetroFitService.getRetrofit().create(RetroFitApi.class);
    }


    public LiveData<PagedList<Result>> getDiscover() {
        dataSourceFactory = new MovieDataSourceFactory();
        PagedList.Config config = new PagedList.Config.Builder()
                .setPageSize(20)
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(30)
                .setPrefetchDistance(20)
                .build();
        executor = Executors.newFixedThreadPool(5);
        LiveData<PagedList<Result>> discoverList = new LivePagedListBuilder<>(dataSourceFactory, config).setFetchExecutor(executor).build();

        Log.d("BLAH", "yooooooo: 1" + discoverList.toString());
        return discoverList;
    }

    public LiveData<PagedList<Result>> getDiscover(int year, String sort_by) {
        dataSourceFactory = new MovieDataSourceFactory(year, sort_by);
        PagedList.Config config = new PagedList.Config.Builder()
                .setPageSize(20)
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(30)
                .setPrefetchDistance(20)
                .build();
        executor = Executors.newFixedThreadPool(5);
        LiveData<PagedList<Result>> discoverList = new LivePagedListBuilder<>(dataSourceFactory, config).setFetchExecutor(executor).build();

        Log.d("BLAH", "yooooooo: 2" + discoverList.toString());
        return discoverList;
    }

    public LiveData<Movie> getMovie(int movie_id) {
        MutableLiveData<Movie> movieMutableLiveData = new MutableLiveData<>();

        Call<Movie> movie = retroFitApi.getMovie(movie_id);
        movie.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                Log.d("BLAH", "onResponse: bro");
                movieMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Log.d("BLAH", "onFailure: "+"wtf yo");

            }
        });
        return movieMutableLiveData;

    }

}