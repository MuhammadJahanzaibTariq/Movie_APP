package com.example.android.moviesapp.viewModel;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android.moviesapp.Repository.MovieRepository;
import com.example.android.moviesapp.models.MovieModel;

import java.util.List;

public class MovieViewModel extends ViewModel {
    //LIVE DATA
    private final MovieRepository movieRepository;

    //CONSTRUCTOR

    public MovieViewModel() {
        movieRepository = MovieRepository.getInstance();
    }

    //GETTER

    public LiveData<List<MovieModel>> getmMovie() {
        return movieRepository.getMovie();
    }

    public LiveData<List<MovieModel>> getpop() {
        return movieRepository.getpopMovie();
    }

    public void SearchMovieApi(String query, int pagenumber) {
        movieRepository.SearchMovieApi(query, pagenumber);

    }

    public void SearchpopMovie(int pagenumber) {
        movieRepository.SearchpopMovie(pagenumber);

    }

    public void Searchnextmoview() {
        movieRepository.SearchNextpage();
        ;
    }

    public void Searchnextpopmoview() {
        movieRepository.SearchNextpoppage();
        ;
    }

}
