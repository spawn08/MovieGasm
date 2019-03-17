package com.spawn.moviegasm.Utils;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;

import com.spawn.moviegasm.roomdb.MovieDatabase;
import com.spawn.moviegasm.roomdb.RoomDBModel;

import java.util.List;
import java.util.concurrent.ExecutionException;

/*
* This class is used for Room database initialization and access of the contents
* It has 2 moethods to get all the movies and delete a specific movie
* The constructor returns the Room Database instance
*
* */

public class UserService {

    private MovieDatabase db;

    public UserService(Context context) {
        db = Room.databaseBuilder(context,
                MovieDatabase.class, "movielist").build();
    }

    public MovieDatabase getDB() {
        return db;
    }

    public List<RoomDBModel> getAllMovies() throws ExecutionException, InterruptedException {
        return new GetUsersAsyncTask().execute().get();
    }

    public void deleteMovie(final String title) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                db.daoAccess().deleteMovieData(title);
            }
        });
    }


    private class GetUsersAsyncTask extends AsyncTask<Void, Void, List<RoomDBModel>> {
        @Override
        protected List<RoomDBModel> doInBackground(Void... url) {
            return db.daoAccess().getAllData();
        }
    }
}