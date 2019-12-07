package com.silverpants.movieapp.datafactory;


import com.silverpants.movieapp.Keys;
import com.silverpants.movieapp.pojo.Discover;
import com.silverpants.movieapp.pojo.Result;
import com.silverpants.movieapp.retrofit.RetroFitRepository;
import com.silverpants.movieapp.retrofit.RetroFitApi;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDataSource extends PageKeyedDataSource<Integer, Result> {

    private int year;
    private String sort_by;
    private RetroFitApi service;

    public MovieDataSource() {
        this(2019, Keys.SORT_BY_VALUES[0]);
    }

    public MovieDataSource(int year, String sort_by) {
        this.year = year;
        this.sort_by = sort_by;
        service = RetroFitRepository.getInstance().getRetroFitApi();
    }


    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Result> callback) {
        Call<Discover> discover;
        discover = service.getDiscover(1, year, sort_by);
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
        //not required

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Result> callback) {
        Call<Discover> discover;
        discover = service.getDiscover(params.key, year, sort_by);
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
