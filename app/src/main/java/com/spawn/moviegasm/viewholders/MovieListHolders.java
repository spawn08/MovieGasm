package com.spawn.moviegasm.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.spawn.moviegasm.R;

public class MovieListHolders extends RecyclerView.ViewHolder {

    public ImageView moviePoster;
    public TextView title,releaseDate,director,language,genre;

    public MovieListHolders(View itemView) {
        super(itemView);

        moviePoster = (ImageView) itemView.findViewById(R.id.movie_poster);
        title = (TextView) itemView.findViewById(R.id.title);
        releaseDate = (TextView) itemView.findViewById(R.id.release_date);
        director = (TextView) itemView.findViewById(R.id.director);
        language = (TextView) itemView.findViewById(R.id.language);
        genre = (TextView) itemView.findViewById(R.id.genre);
    }
}
