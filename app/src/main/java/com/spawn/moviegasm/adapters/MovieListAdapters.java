package com.spawn.moviegasm.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.spawn.moviegasm.R;
import com.spawn.moviegasm.model.MovieList;
import com.spawn.moviegasm.model.MovieModel;
import com.spawn.moviegasm.viewholders.MovieListHolders;

import java.util.ArrayList;

public class MovieListAdapters extends RecyclerView.Adapter<MovieListHolders> {

    public MovieModel movieModel;
    public Context context;

    public MovieListAdapters(MovieModel movieModel, Context context) {
        this.movieModel = movieModel;
        this.context = context;
    }

    @NonNull
    @Override
    public MovieListHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movie_list_recycler, parent, false);

        MovieListHolders movieListHolders = new MovieListHolders(view);

        return movieListHolders;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieListHolders holder, int position) {
        ArrayList<MovieList> getMovieModel = movieModel.getMovieModels();
        RequestOptions requestOptions = new RequestOptions().placeholder(R.drawable.movie_poster);
        Glide.with(context)
                .load(getMovieModel.get(position)
                        .getPoster())
                .apply(requestOptions)
                .into(holder.moviePoster);
        holder.language.setText(getMovieModel.get(position).getLanguage());
        holder.title.setText(getMovieModel.get(position).getTitle());
        holder.director.setText(getMovieModel.get(position).getDirector());
        holder.genre.setText(getMovieModel.get(position).getGenre());
        holder.releaseDate.setText(getMovieModel.get(position).getReleased());
    }

    @Override
    public int getItemCount() {
        return movieModel.getMovieModels().size();
    }
}
