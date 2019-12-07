package com.silverpants.movieapp.retrofit;

import com.silverpants.movieapp.Keys;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroFitService {
    private static Retrofit retrofit;

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            synchronized (RetroFitService.class) {
                if (retrofit == null) {
                    retrofit = new retrofit2.Retrofit.Builder()
                            .baseUrl(Keys.BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                }
            }
        }
        return retrofit;
    }

}
