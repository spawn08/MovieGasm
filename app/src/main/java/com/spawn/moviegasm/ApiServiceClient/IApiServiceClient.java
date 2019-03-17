package com.spawn.moviegasm.ApiServiceClient;

import com.spawn.moviegasm.model.MovieList;
import com.spawn.moviegasm.model.MovieModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IApiServiceClient {

    @GET("/")
    Call<MovieList> getMovieModel(@Query("t") String t, @Query("apikey") String apiKey);

    @GET("/")
    Call<MovieList> getMovies(@Query("t") String t,
                                   @Query("apikey") String apiKey,
                                   @Query("movie") String movies);

    @GET("/")
    Call<MovieList> getSeries(@Query("t") String t,
                                   @Query("apikey") String apiKey,
                                   @Query("series") String series);

    @GET("/api/classify")
    Call<MovieModel> getMovieConfig(@Query("name") String name);
}
