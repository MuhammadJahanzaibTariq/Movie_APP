package com.example.android.moviesapp.utils;

import com.example.android.moviesapp.models.MovieModel;
import com.example.android.moviesapp.response.MovieSearchResponse;
import com.example.android.moviesapp.response.TrailerResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieAPI {



    @GET("/3/search/movie")
    Call<MovieSearchResponse> SearchMovie(
            @Query("api_key") String key,
            @Query("query") String query,
            @Query("page") int page);



    @GET("/3/movie/{movie_id}?")
    Call<MovieModel> getMovie(
            @Path("movie_id") int movieid,
            @Query("api_key") String apikey
    );
 // SEARCH YOUTUBE VIDEOS
    @GET("/3/movie/{movie_id}/videos?")
    Call<TrailerResponse> getMoviewTrailer(
            @Path("movie_id") int id,
            @Query("api_key") String apikey
    );
    //SEARCH POPULAR MOVIES
    @GET("/3/movie/popular")
    Call<MovieSearchResponse> SearchPopularMovie(
            @Query("api_key") String key,
            @Query("page") int page);


}
