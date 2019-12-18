package com.silverpants.movieapp.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import com.silverpants.movieapp.BASE_URL

object RetroFitService {
     var retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}
