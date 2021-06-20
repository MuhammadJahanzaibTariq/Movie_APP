package com.example.android.moviesapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class MovieModel implements Parcelable {
    private String title;
    private String poster_path;
    private String release_date;
    @SerializedName("overview")
    private String movie_overview;
    private float vote_average;
    @SerializedName("id")
    private int movie_id;
    private int runtime;

    public MovieModel() {

    }

    public MovieModel(String title, String poster_path, String release_date, String movie_overview, float vote_average, int movie_id, int runtime) {
        this.title = title;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.movie_overview = movie_overview;
        this.vote_average = vote_average;
        this.movie_id = movie_id;
        this.runtime=runtime;
    }

    protected MovieModel(Parcel in) {
        title = in.readString();
        poster_path = in.readString();
        release_date = in.readString();
        movie_overview = in.readString();
        vote_average = in.readFloat();
        movie_id = in.readInt();
    }

    public static final Creator<MovieModel> CREATOR = new Creator<MovieModel>() {
        @Override
        public MovieModel createFromParcel(Parcel in) {
            return new MovieModel(in);
        }

        @Override
        public MovieModel[] newArray(int size) {
            return new MovieModel[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getMovie_overview() {
        return movie_overview;
    }

    public float getVote_average() {
        return vote_average;
    }

    public int getMovie_id() {
        return movie_id;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(poster_path);
        dest.writeString(release_date);
        dest.writeString(movie_overview);
        dest.writeFloat(vote_average);
        dest.writeInt(movie_id);
    }
}


