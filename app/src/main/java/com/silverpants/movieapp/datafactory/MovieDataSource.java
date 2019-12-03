package com.silverpants.movieapp.datafactory;


import com.silverpants.movieapp.Keys;
import com.silverpants.movieapp.pojo.Discover;
import com.silverpants.movieapp.pojo.Result;
import com.silverpants.movieapp.retrofit.MovieManager;
import com.silverpants.movieapp.retrofit.MovieService;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDataSource extends PageKeyedDataSource<Integer, Result> {

    private int year;
    private String sort_by;

    public MovieDataSource() {
        year = 2019;
        sort_by = Keys.SORT_BY_VALUES[0];
    }

    public MovieDataSource(int year, String sort_by) {
        this.year = year;
        this.sort_by = sort_by;
    }

    private MovieService service;

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Result> callback) {
        service = MovieManager.getRetrofit().create(MovieService.class);
        Call<Discover> discover;
        if (year != -1) {
            discover = service.getDiscover(1, year, sort_by);
        } else {
            discover = service.getDiscover(1);
        }
        discover.enqueue(new Callback<Discover>() {
            @Override
            public void onResponse(Call<Discover> call, Response<Discover> response) {
                List<Result> ls = response.body().getResults();
                callback.onResult(ls, null, 2);
            }

            @Override
            public void onFailure(Call<Discover> call, Throwable t) {
            }
        });


    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Result> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Result> callback) {
        Call<Discover> discover;
        if (year != -1) {
            discover = service.getDiscover(params.key, year,sort_by);
        } else {
            discover = service.getDiscover(params.key);
        }
        discover.enqueue(new Callback<Discover>() {
            @Override
            public void onResponse(Call<Discover> call, Response<Discover> response) {
                List<Result> ls = response.body().getResults();
                callback.onResult(ls, params.key + 1);
            }

            @Override
            public void onFailure(Call<Discover> call, Throwable t) {

            }
        });

    }
}
