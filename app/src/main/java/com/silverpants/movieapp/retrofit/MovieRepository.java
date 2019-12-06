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

public class MovieRepository {

    private static MovieRepository movieRepository;
    private MovieApi movieApi;

    public MovieApi getMovieApi() {
        return movieApi;
    }

    MovieDataSourceFactory dataSourceFactory;
    Executor executor;

    public static MovieRepository getInstance() {

        if (movieRepository == null) {
            synchronized (MovieRepository.class) {
                movieRepository = new MovieRepository();
            }
        }
        return movieRepository;
    }

    private MovieRepository() {
        movieApi = MovieService.getRetrofit().create(MovieApi.class);
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
        Call<Movie> movie = movieApi.getMovie(movie_id);
        movie.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                movieMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                movieMutableLiveData.setValue(null);

            }
        });
        return movieMutableLiveData;

    }

}