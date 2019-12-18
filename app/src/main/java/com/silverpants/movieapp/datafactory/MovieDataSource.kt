package com.silverpants.movieapp.datafactory

import com.silverpants.movieapp.pojo.discover.Discover
import com.silverpants.movieapp.pojo.discover.Result
import com.silverpants.movieapp.retrofit.RetroFitRepository
import com.silverpants.movieapp.retrofit.RetroFitApi
import androidx.paging.PageKeyedDataSource
import com.silverpants.movieapp.SORT_BY_VALUES
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDataSource(var year: Int = 2019,var sort_by: String = SORT_BY_VALUES[0]) : PageKeyedDataSource<Int, Result>() {
    private val service: RetroFitApi = RetroFitRepository.retroFitApi


    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Result>) {
        val discover: Call<Discover> = service.getDiscover(1, year, sort_by)
        discover.enqueue(object : Callback<Discover> {
            override fun onResponse(call: Call<Discover>, response: Response<Discover>) {
                val ls = response.body()!!.results
                callback.onResult(ls!!, null, 2)
            }

            override fun onFailure(call: Call<Discover>, t: Throwable) {}
        })


    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Result>) {
        //not required

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Result>) {
        val discover: Call<Discover> = service.getDiscover(params.key, year, sort_by)
        discover.enqueue(object : Callback<Discover> {
            override fun onResponse(call: Call<Discover>, response: Response<Discover>) {
                val ls = response.body()!!.results
                callback.onResult(ls!!, params.key + 1)
            }

            override fun onFailure(call: Call<Discover>, t: Throwable) {

            }
        })

    }
}
