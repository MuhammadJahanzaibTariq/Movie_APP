package com.example.android.moviesapp.models;

import com.google.gson.annotations.SerializedName;

public class Trailer {
    @SerializedName("key")
    private String Trailer_key;
    @SerializedName("name")
    private String Trailer_name ;

    public Trailer(String trailer_key, String trailer_name) {
        Trailer_key = trailer_key;
        Trailer_name = trailer_name;
    }

    public String getTrailer_key() {
        return Trailer_key;
    }

    public void setTrailer_key(String trailer_key) {
        Trailer_key = trailer_key;
    }

    public String getTrailer_name() {
        return Trailer_name;
    }

    public void setTrailer_name(String trailer_name) {
        Trailer_name = trailer_name;
    }
}
