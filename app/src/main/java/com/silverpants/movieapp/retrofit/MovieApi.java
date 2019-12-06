package com.silverpants.movieapp.retrofit;

import com.silverpants.movieapp.Keys;
import com.silverpants.movieapp.pojo.Discover;
import com.silverpants.movieapp.pojo.Movie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApi {
    @GET("discover/movie?api_key=" + Keys.API_KEY)
    Call<Discover> getDiscover(@Query(Keys.PAGE_QUERY) int page_no, @Query(Keys.PRIMARY_RELEASE_YEAR_QUERY) int year, @Query(Keys.SORT_BY_QUERY) String sort_by);

    @GET("discover/movie?api_key=" + Keys.API_KEY + "&" + Keys.PRIMARY_RELEASE_YEAR_QUERY + "2019")
    Call<Discover> getDiscover(@Query(Keys.PAGE_QUERY) int page_no);

    @GET("movie/{movie_id}?api_key=" + Keys.API_KEY)
    Call<Movie> getMovie(@Path("movie_id") int id);
}
