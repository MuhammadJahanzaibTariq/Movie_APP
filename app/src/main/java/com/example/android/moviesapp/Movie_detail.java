package com.example.android.moviesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.android.moviesapp.adapters.MovieRecyclerview;
import com.example.android.moviesapp.models.MovieModel;
import com.example.android.moviesapp.models.Trailer;
import com.example.android.moviesapp.request.Services;
import com.example.android.moviesapp.response.MovieSearchResponse;
import com.example.android.moviesapp.response.TrailerResponse;
import com.example.android.moviesapp.utils.Credentials;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Movie_detail extends AppCompatActivity {
    ImageView imageView;
    TextView title,deccription;
    ImageButton btn;
    int n;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        imageView=findViewById(R.id.img1);
        title=findViewById(R.id.mtitle);
        deccription=findViewById(R.id.DESC);
        btn=findViewById(R.id.Trailer);
        Showdetail();


    }

    public void Showdetail()
    {

          n=getIntent().getIntExtra("Movie",0);
            title.setText(MovieRecyclerview.movieModels.get(n).getTitle());
            deccription.setText(MovieRecyclerview.movieModels.get(n).getMovie_overview());
        Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500/"+MovieRecyclerview.movieModels.get(n)
                        .getPoster_path()).into(imageView);
        loadjason();
    }

    public void loadjason()
    {
        try
        {

            Services services=new Services();
            Call<TrailerResponse> response=services.getMovieApi().getMoviewTrailer(MovieRecyclerview.movieModels.get(n).getMovie_id(),Credentials.API_key);

            response.enqueue(new Callback<TrailerResponse>() {
                @Override
                public void onResponse(Call<TrailerResponse> call, Response<TrailerResponse> response) {
                    Toast.makeText(Movie_detail.this, ""+response.code(), Toast.LENGTH_SHORT).show();
                    List<Trailer>trailers=new ArrayList<>((response.body()).getResult());
                  btn.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           if(!trailers.isEmpty()) {
                               if (trailers.get(0).getTrailer_key() != null) {
                                   Toast.makeText(Movie_detail.this, "" + n, Toast.LENGTH_SHORT).show();
                                   String video_id = trailers.get(0).getTrailer_key();
                                   Toast.makeText(Movie_detail.this, "" + video_id, Toast.LENGTH_SHORT).show();
                                   Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + video_id));
                                   startActivity(intent);
                               }
                           }
                           else
                           {
                               Toast.makeText(Movie_detail.this, "SORRY NO VIDEO IS AVAILABLE :(", Toast.LENGTH_LONG).show();
                           }
                       }
                   });

                }

                @Override
                public void onFailure(Call<TrailerResponse> call, Throwable t) {

                    Toast.makeText(Movie_detail.this, "Error fetching data", Toast.LENGTH_SHORT).show();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}