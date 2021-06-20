package com.example.android.moviesapp.response;

import com.example.android.moviesapp.models.MovieModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

//THIS WILL SEARH FOR POPULAR MOVIES
public class MovieSearchResponse {

    @SerializedName("total_results")
    private int Total_count;

    @SerializedName("results")
    @Expose
    private List<MovieModel> movieModel;

    public int getTotal_count() {
        return Total_count;
    }

    public List<MovieModel> getMovieModel() {
        return movieModel;
    }

    @Override
    public String toString() {
        return "MovieSearchResponse{" +
                "Total_count=" + Total_count +
                ", movieModel=" + movieModel +
                '}';
    }
}
