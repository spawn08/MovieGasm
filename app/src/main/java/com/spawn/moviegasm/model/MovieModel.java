package com.spawn.moviegasm.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MovieModel {

    @SerializedName("version")
    public String version;

    @SerializedName("movie_list")
    public ArrayList<MovieList> movieModels;

    @SerializedName("message")
    public String message;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public ArrayList<MovieList> getMovieModels() {
        return movieModels;
    }

    public void setMovieModels(ArrayList<MovieList> movieModels) {
        this.movieModels = movieModels;
    }

    public String getMessage() {
        return message;
    }
}
