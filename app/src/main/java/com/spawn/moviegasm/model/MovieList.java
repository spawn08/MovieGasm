package com.spawn.moviegasm.model;

import com.google.gson.annotations.SerializedName;

public class MovieList {

    @SerializedName("Title")
    public String title;

    @SerializedName("Year")
    public String year;

    @SerializedName("Released")
    public String released;

    @SerializedName("Runtime")
    public String runtime;

    @SerializedName("Genre")
    public String genre;

    @SerializedName("Director")
    public String director;

    @SerializedName("Writer")
    public String writer;

    @SerializedName("Actors")
    public String actors;

    @SerializedName("Plot")
    public String plot;

    @SerializedName("Language")
    public String language;

    @SerializedName("Poster")
    public String poster;

    @SerializedName("imdbRating")
    public String imdbRating;

    @SerializedName("imdbVotes")
    public String imdbVotes;

    @SerializedName("Production")
    public String production;

    @SerializedName("Response")
    public String response;

    @SerializedName("Error")
    public String error;

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getReleased() {
        return released;
    }

    public String getRuntime() {
        return runtime;
    }

    public String getGenre() {
        return genre;
    }

    public String getDirector() {
        return director;
    }

    public String getWriter() {
        return writer;
    }

    public String getActors() {
        return actors;
    }

    public String getPlot() {
        return plot;
    }

    public String getLanguage() {
        return language;
    }

    public String getPoster() {
        return poster;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public String getImdbVotes() {
        return imdbVotes;
    }

    public String getProduction() {
        return production;
    }

    public String getResponse() {
        return response;
    }

    public String getError() {
        return error;
    }
}
