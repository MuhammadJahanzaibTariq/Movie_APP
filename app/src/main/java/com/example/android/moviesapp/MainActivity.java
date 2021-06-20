package com.example.android.moviesapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.android.moviesapp.adapters.MovieRecyclerview;
import com.example.android.moviesapp.models.MovieModel;
import com.example.android.moviesapp.viewModel.MovieViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MovieRecyclerview.ItemSelected {

    MovieViewModel viewModel;
    public RecyclerView recyclerView;
    MovieRecyclerview movieRecyclerview;
    ProgressBar bar;
    LinearLayoutManager manager;
    boolean isScroll = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycle);
        bar = findViewById(R.id.progressBar);
        ActionBar bar2 = getSupportActionBar();
        bar2.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#062C3D")));

        //observer


        //View model

        viewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.
                        getInstance(this.getApplication()))
                .get(MovieViewModel.class);

        setRecyclerView();
        Observeanychange();
        Observepopchange();
        viewModel.SearchpopMovie(1);


    }

    private void Observepopchange() {
        viewModel.getpop().observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(@Nullable List<MovieModel> notes) {
                if (notes != null) {
                    for (MovieModel movieViewModel : notes) {
                        movieRecyclerview.setMovieModels(notes);
                    }
                } else {
                    Toast.makeText(MainActivity.this, "No result for your search", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void setRecyclerView() {
        manager = new LinearLayoutManager(this);
        movieRecyclerview = new MovieRecyclerview(this);
        recyclerView.setAdapter(movieRecyclerview);
        recyclerView.setLayoutManager(manager);


        //pagination
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!recyclerView.canScrollVertically(1)) {
                    bar.setVisibility(View.VISIBLE);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            bar.setVisibility(View.GONE);
                            if (isScroll) {
                                viewModel.Searchnextpopmoview();
                            } else {
                                viewModel.Searchnextmoview();
                            }
                        }
                    }, 5000);

                }

            }
        });
    }

    private void Observeanychange() {
        viewModel.getmMovie().observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(@Nullable List<MovieModel> notes) {
                if (notes != null) {
                    for (MovieModel movieViewModel : notes) {
                        movieRecyclerview.setMovieModels(notes);
                    }
                } else {
                    Toast.makeText(MainActivity.this, "No result for your search", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void searchmovieapi(String query, int pagenumber) {
        viewModel.SearchMovieApi(query, pagenumber);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                searchmovieapi(query, 1);
                isScroll = false;
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onItemClicked(int index) {


        Intent intent = new Intent(MainActivity.this, Movie_detail.class);
        intent.putExtra("Movie", index);
        startActivity(intent);
    }
}