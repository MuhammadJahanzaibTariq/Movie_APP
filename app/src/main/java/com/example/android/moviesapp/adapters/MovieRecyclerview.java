package com.example.android.moviesapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.android.moviesapp.R;
import com.example.android.moviesapp.models.MovieModel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MovieRecyclerview  extends RecyclerView.Adapter<MovieRecyclerview.ViewHolder> {
    public static List<MovieModel> movieModels;
    Context context;
    ItemSelected activity;

    public interface ItemSelected{
        void onItemClicked(int index);
    }


    public MovieRecyclerview( Context context) {
        activity= (ItemSelected) context;
        this.context = context;
    }

    public void setMovieModels(List<MovieModel> movieModels) {
        this.movieModels = movieModels;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieRecyclerview.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycledesigne,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieRecyclerview.ViewHolder holder, int position) {
        holder.itemView.setTag(movieModels.get(position));
        holder.title.setText(movieModels.get(position).getTitle());
        holder.release.setText(movieModels.get(position).getRelease_date());
        holder.duration.setText(String.valueOf(movieModels.get(position).getMovie_id()));
        holder.ratingBar.setRating((int) (movieModels.get(position).getVote_average())/2);

        Glide.with(holder.itemView.getContext())
                .load("https://image.tmdb.org/t/p/w500/"+movieModels.get(position)
                        .getPoster_path()).into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        if(movieModels!=null)
        {return movieModels.size();}
        else
        {
            return 0;
        }
    }
    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView title,release,duration;
        ImageView imageView;
        RatingBar ratingBar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
           release=itemView.findViewById(R.id.catagory);
            duration=itemView.findViewById(R.id.duration);
            imageView=itemView.findViewById(R.id.imageView);
            ratingBar=itemView.findViewById(R.id.ratingBar);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.onItemClicked(movieModels.indexOf((MovieModel) (itemView.getTag())));
                }
            });
        }
    }
}
