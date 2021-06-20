package com.example.android.moviesapp.request;

import com.example.android.moviesapp.utils.Credentials;
import com.example.android.moviesapp.utils.MovieAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Services {

    private static Retrofit retrofitbuilder=new Retrofit.Builder()
            .baseUrl(Credentials.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    MovieAPI movieapi=retrofitbuilder.create(MovieAPI.class);

    public MovieAPI getMovieApi()
    {
        return movieapi;
    }


}
