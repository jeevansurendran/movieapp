package com.silverpants.movieapp.retrofit

import com.silverpants.movieapp.API_KEY
import com.silverpants.movieapp.PAGE_QUERY
import com.silverpants.movieapp.PRIMARY_RELEASE_YEAR_QUERY
import com.silverpants.movieapp.SORT_BY_QUERY
import com.silverpants.movieapp.pojo.discover.Discover
import com.silverpants.movieapp.pojo.movie.Movie

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetroFitApi {
    @GET("discover/movie?api_key=$API_KEY")
    fun getDiscover(@Query(PAGE_QUERY) page_no: Int, @Query(PRIMARY_RELEASE_YEAR_QUERY) year: Int, @Query(SORT_BY_QUERY) sort_by: String): Call<Discover>

    @GET("movie/{movie_id}?api_key=$API_KEY?")
    fun getMovie(@Path("movie_id") movie_id: Int): Call<Movie>
}
