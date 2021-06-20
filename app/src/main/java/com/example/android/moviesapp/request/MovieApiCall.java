package com.example.android.moviesapp.request;


import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.android.moviesapp.AppExecutor;
import com.example.android.moviesapp.MainActivity;
import com.example.android.moviesapp.Movie_detail;
import com.example.android.moviesapp.Repository.MovieRepository;
import com.example.android.moviesapp.models.MovieModel;
import com.example.android.moviesapp.response.MovieSearchResponse;
import com.example.android.moviesapp.utils.Credentials;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

public class MovieApiCall {
    AlertDialog.Builder builder;
    private MutableLiveData<List<MovieModel>> mMovie;
    private static MovieApiCall instance;
    //Retrieve movie runnable
    private RetrieveMovieRunnable retrieveMovieRunnable;

    // LIVE DATA FOR POPULAR SEARCH
    private MutableLiveData<List<MovieModel>> mMoviePopular;
    private RetrieveMovieRunnablepop retrieveMovieRunnablepop;


    private MovieApiCall() {

        mMovie = new MutableLiveData<>();
        mMoviePopular = new MutableLiveData<>();
    }

    public LiveData<List<MovieModel>> getMovie() {
        return mMovie;
    }

    public LiveData<List<MovieModel>> getMoviepop() {
        return mMoviePopular;
    }

    public static MovieApiCall getInstance() {


        if (instance == null) {
            instance = new MovieApiCall();
        }
        return instance;
    }


    public void SearchMovieApi(String query, int pageNumber) {

        if (retrieveMovieRunnable != null) {
            retrieveMovieRunnable = null;
        }
        retrieveMovieRunnable = new RetrieveMovieRunnable(query, pageNumber);

        final Future mhandler = AppExecutor.getInstance().networkIO().submit(retrieveMovieRunnable);

        AppExecutor.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                mhandler.cancel(true);

            }
        }, 5000, TimeUnit.MILLISECONDS);


    }

    // FOR POPULAR MOVIES
    public void SearchPopMovie(int pageNumber) {

        if (retrieveMovieRunnablepop != null) {
            retrieveMovieRunnablepop = null;
        }
        retrieveMovieRunnablepop = new RetrieveMovieRunnablepop(pageNumber);

        final Future mhandler2 = AppExecutor.getInstance().networkIO().submit(retrieveMovieRunnablepop);

        AppExecutor.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                mhandler2.cancel(true);

            }
        }, 5000, TimeUnit.MILLISECONDS);


    }

    private class RetrieveMovieRunnable implements Runnable {
        private String query;
        private int pageNumber;
        boolean cancelRequest;

        public RetrieveMovieRunnable(String query, int pageNumber) {
            this.query = query;
            this.pageNumber = pageNumber;
            this.cancelRequest = false;
        }

        @Override
        public void run() {


            try {
                Response response = getMovie(query, pageNumber).execute();

                if (cancelRequest) {
                    return;
                }
                if (response.code() == 200) {
                    List<MovieModel> list = new ArrayList<>(((MovieSearchResponse) response.body()).getMovieModel());

                    if (pageNumber == 1 && !list.isEmpty()) {

                        mMovie.postValue(list);
                    } else if(!list.isEmpty()){

                        List<MovieModel> currrentMovie = mMovie.getValue();
                        currrentMovie.addAll(list);
                        mMovie.postValue(list);
                    }else {
                        Log.i("Yag", "NO RESULT IS FOUND AGAINST YOUR QUERY :(");

                    }
                } else {

                    String error = response.errorBody().string().toString();
                    Log.v("Tag", "ERROR" + error);
                    mMovie.postValue(null);
                }
            } catch (IOException e) {
                e.printStackTrace();
                mMovie.postValue(null);
            }


        }
        // SEARCH METHOD

        private Call<MovieSearchResponse> getMovie(String query, int pageNumber) {
            Services services = new Services();
            return services.getMovieApi().SearchMovie(
                    Credentials.API_key,
                    query,
                    pageNumber
            );

        }

        private void CallRequest() {
            Log.v("Tag", "CACELLING SEARCH REQUEST");
            cancelRequest = true;
        }

    }

    // popular movies
    private class RetrieveMovieRunnablepop implements Runnable {
        private int pageNumber;
        boolean cancelRequest;

        public RetrieveMovieRunnablepop(int pageNumber) {

            this.pageNumber = pageNumber;
            this.cancelRequest = false;
        }

        @Override
        public void run() {


            try {
                Response response = getpop(pageNumber).execute();
                if (cancelRequest) {
                    return;
                }
                if (response.code() == 200) {
                    List<MovieModel> list = new ArrayList<>(((MovieSearchResponse) response.body()).getMovieModel());
                    if (pageNumber == 1) {
                        mMovie.postValue(list);
                    } else {
                        List<MovieModel> currrentMovie = mMovie.getValue();
                        currrentMovie.addAll(list);
                        mMovie.postValue(list);
                    }
                } else {
                    String error = response.errorBody().string().toString();
                    Log.v("Tag", "ERROR" + error);
                    mMovie.postValue(null);
                }
            } catch (IOException e) {
                e.printStackTrace();
                mMovie.postValue(null);
            }


        }
        // SEARCH METHOD

        private Call<MovieSearchResponse> getpop(int pageNumber) {
            Services services = new Services();
            return services.getMovieApi().SearchPopularMovie(
                    Credentials.API_key,
                    pageNumber
            );

        }

        private void CallRequest() {
            Log.v("Tag", "CACELLING SEARCH REQUEST");
            cancelRequest = true;
        }

    }
}
