package com.spawn.moviegasm.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.spawn.moviegasm.R;

public class SearchMovieListHolder extends RecyclerView.ViewHolder {

    public TextView title;
    public ImageView delete;

    public SearchMovieListHolder(View itemView) {
        super(itemView);

        title = (TextView) itemView.findViewById(R.id.history_text);
        delete = (ImageView) itemView.findViewById(R.id.delete);
    }
}
