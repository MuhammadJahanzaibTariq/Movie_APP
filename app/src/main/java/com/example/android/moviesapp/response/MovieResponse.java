package com.example.android.moviesapp.response;

import com.example.android.moviesapp.models.MovieModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//THIS CLASS IS FOR SINGLE Search

public class MovieResponse {
    @SerializedName("results")
    @Expose
    private MovieModel movieModel;

    public MovieModel getModel() {
        return movieModel;
    }

    @Override
    public String toString() {
        return "MovieResponse{" +
                "movieModel=" + movieModel +
                '}';
    }
}
