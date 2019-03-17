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
import com.spawn.moviegasm.Utils.UserService;
import com.spawn.moviegasm.roomdb.RoomDBModel;
import com.spawn.moviegasm.viewholders.MovieListHolders;
import com.spawn.moviegasm.viewholders.SearchMovieListHolder;

import java.util.List;

public class SearchMovieListAdapters extends RecyclerView.Adapter<SearchMovieListHolder> {

    public List<RoomDBModel> movieModel;
    public Context context;

    public SearchMovieListAdapters(List<RoomDBModel> movieModel, Context context) {
        this.movieModel = movieModel;
        this.context = context;
    }

    @NonNull
    @Override
    public SearchMovieListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_history_movie, parent, false);

        SearchMovieListHolder movieListHolders = new SearchMovieListHolder(view);

        return movieListHolders;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchMovieListHolder holder, final int position) {
        final RoomDBModel getMovieModel = movieModel.get(position);
        if (getMovieModel != null) {
            holder.title.setText(getMovieModel.getTitle());
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UserService userService = new UserService(context);
                    userService.deleteMovie(getMovieModel.getTitle());
                    movieModel.remove(position);
                    notifyDataSetChanged();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return movieModel.size();
    }
}
