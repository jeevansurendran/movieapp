package com.silverpants.movieapp.retrofit

import com.silverpants.movieapp.datafactory.MovieDataSourceFactory
import com.silverpants.movieapp.pojo.movie.Movie
import com.silverpants.movieapp.pojo.discover.Result

import java.util.concurrent.Executor
import java.util.concurrent.Executors

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object RetroFitRepository {
    val retroFitApi: RetroFitApi = RetroFitService.retrofit.create(RetroFitApi::class.java)


    val discoverList: LiveData<PagedList<Result>>
        get() {
            val dataSourceFactory = MovieDataSourceFactory()
            val config = PagedList.Config.Builder()
                    .setPageSize(20)
                    .setEnablePlaceholders(true)
                    .setInitialLoadSizeHint(30)
                    .setPrefetchDistance(20)
                    .build()
            val executor = Executors.newFixedThreadPool(5)
            return LivePagedListBuilder(dataSourceFactory, config).setFetchExecutor(executor).build()
        }

    fun getDiscoverList(year: Int, sort_by: String): LiveData<PagedList<Result>> {
        val dataSourceFactory = MovieDataSourceFactory(year, sort_by)
        val config = PagedList.Config.Builder()
                .setPageSize(20)
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(30)
                .setPrefetchDistance(20)
                .build()
        val executor = Executors.newFixedThreadPool(5)
        return LivePagedListBuilder(dataSourceFactory, config).setFetchExecutor(executor).build()
    }

    fun getMovie(movie_id: Int): LiveData<Movie> {
        val movieMutableLiveData = MutableLiveData<Movie>()

        val movie = retroFitApi.getMovie(movie_id)
        movie.enqueue(object : Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                movieMutableLiveData.value = response.body()
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
            }
        })
        return movieMutableLiveData

    }

}