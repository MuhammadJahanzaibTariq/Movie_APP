package com.example.android.moviesapp.response;

import com.example.android.moviesapp.models.Trailer;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TrailerResponse {
    @SerializedName("id")
    private int id_trailer;
    @SerializedName("results")
    private List<Trailer> result;

    public int getId_trailer() {
        return id_trailer;
    }

    public void setId_trailer(int id_trailer) {
        this.id_trailer = id_trailer;
    }

    public List<Trailer> getResult() {
        return result;
    }

    public void setResult(List<Trailer> result) {
        this.result = result;
    }
}
