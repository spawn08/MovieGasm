package com.spawn.moviegasm;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import com.spawn.moviegasm.ApiServiceClient.IApiServiceClient;
import com.spawn.moviegasm.Utils.UserService;
import com.spawn.moviegasm.Utils.Utility;
import com.spawn.moviegasm.adapters.SearchMovieListAdapters;
import com.spawn.moviegasm.databinding.ActivitySearchBinding;
import com.spawn.moviegasm.model.MovieList;
import com.spawn.moviegasm.roomdb.MovieDatabase;
import com.spawn.moviegasm.roomdb.RoomDBModel;

import java.util.List;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/*
 * This activity class display the movie list from the OMDBAPI
 *
 * */

public class SearchActivity extends AppCompatActivity implements TextView.OnEditorActionListener, View.OnClickListener, TextWatcher {

    public final static String OMDB_URL = "https://www.omdbapi.com";
    public final static String API_KEY = "da16f753";
    public final static String MOVIES = "movie";
    public final static String ERROR = "Could not find movie";
    private ActivitySearchBinding activitySearchBinding;
    private Context context;
    public static final String DATABASE_NAME = "movielist";
    public MovieDatabase movieDatabase;
    public RoomDBModel roomDBModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        activitySearchBinding = DataBindingUtil.setContentView(this, R.layout.activity_search);
        activitySearchBinding.setSearch(this);
        activitySearchBinding.editQuery.setOnEditorActionListener(this);
        activitySearchBinding.backButton.setOnClickListener(this);
        activitySearchBinding.clear.setOnClickListener(this);
        activitySearchBinding.editQuery.addTextChangedListener(this);
        initDB();


    }

    private void initRecyclerData() {
        UserService userService = new UserService(context);
        try {
            List<RoomDBModel> roomDBModels = userService.getAllMovies();
            if (roomDBModels != null) {
                SearchMovieListAdapters searchMovieListAdapters = new SearchMovieListAdapters(roomDBModels, context);
                activitySearchBinding.searchRecycler.setLayoutManager(new LinearLayoutManager(context));
                activitySearchBinding.searchRecycler.setAdapter(searchMovieListAdapters);
                searchMovieListAdapters.notifyDataSetChanged();
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void initDB() {
        try {
            UserService userService = new UserService(context);
            movieDatabase = userService.getDB();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (i == EditorInfo.IME_ACTION_SEARCH) {
            final String query = activitySearchBinding.editQuery.getText().toString().trim();
            insertInDB(query);
            Retrofit retrofit = Utility.getInstance().getClientSearch(OMDB_URL);
            IApiServiceClient serviceClient = retrofit.create(IApiServiceClient.class);
            Call<MovieList> movieListCall = serviceClient.getMovies(query, API_KEY, MOVIES);
            movieListCall.enqueue(new Callback<MovieList>() {
                @Override
                public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                    if (response.body().getError() != null) {
                        Toast.makeText(context, ERROR, Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(context, response.body().toString(), Toast.LENGTH_LONG).show();
                    }

                }

                @Override
                public void onFailure(Call<MovieList> call, Throwable t) {
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
                    t.printStackTrace();
                }
            });
            return true;
        }
        return false;
    }

    private void insertInDB(String body) {
        roomDBModel = new RoomDBModel();
        roomDBModel.setTitle(body);

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                movieDatabase.daoAccess().addMovie(roomDBModel);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        initRecyclerData();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_button:
                finish();
                break;

            case R.id.clear:
                if (activitySearchBinding.editQuery.getText() != null ||
                        !activitySearchBinding.editQuery.getText().toString().isEmpty())
                    activitySearchBinding.editQuery.getText().clear();

                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (charSequence.toString().length() > 0) {
            activitySearchBinding.clear.setVisibility(View.VISIBLE);
        } else {
            activitySearchBinding.clear.setVisibility(View.GONE);
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (editable.toString().length() > 0)
            activitySearchBinding.clear.setVisibility(View.VISIBLE);
        else
            activitySearchBinding.clear.setVisibility(View.GONE);
    }
}
