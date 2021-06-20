package com.example.android.moviesapp.Repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.android.moviesapp.models.MovieModel;
import com.example.android.moviesapp.request.MovieApiCall;

import java.util.List;

public class MovieRepository {
    private static MovieRepository instance;
    private MovieApiCall movieApiCall;
    String query1;
    int pagenumber1;


    private MovieRepository() {
        movieApiCall = MovieApiCall.getInstance();
    }

    public LiveData<List<MovieModel>> getMovie() {
        return movieApiCall.getMovie();
    }

    public LiveData<List<MovieModel>> getpopMovie() {
        return movieApiCall.getMoviepop();
    }

    public static MovieRepository getInstance() {
        if (instance == null) {
            instance = new MovieRepository();
        }
        return instance;
    }

    public void SearchMovieApi(String query, int pagenumber) {
        query1 = query;
        pagenumber1 = pagenumber;
        movieApiCall.SearchMovieApi(query, pagenumber);
    }

    public void SearchpopMovie(int pagenumber) {
        pagenumber1 = pagenumber;
        movieApiCall.SearchPopMovie(pagenumber);
    }

    public void SearchNextpage() {
        SearchMovieApi(query1, pagenumber1 + 1);
    }

    public void SearchNextpoppage() {
        SearchpopMovie(pagenumber1 + 1);
    }


}


