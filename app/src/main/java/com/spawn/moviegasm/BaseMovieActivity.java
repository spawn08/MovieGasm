package com.spawn.moviegasm;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.spawn.moviegasm.ApiServiceClient.IApiServiceClient;
import com.spawn.moviegasm.Utils.Utility;
import com.spawn.moviegasm.adapters.MovieListAdapters;
import com.spawn.moviegasm.databinding.ActivityBaseMovieBinding;
import com.spawn.moviegasm.model.MovieModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class BaseMovieActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String BASE_URL = "YOUR-API-URL-FOR-FILE";
    private ActivityBaseMovieBinding activityBaseMovieBinding;
    private Context context;
    public MovieListAdapters movieListAdapters;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        activityBaseMovieBinding = DataBindingUtil.setContentView(this, R.layout.activity_base_movie);
        activityBaseMovieBinding.setSpawn(this);
        activityBaseMovieBinding.shimmerViewContainer.startShimmerAnimation();
        initListener();


    }

    /*
     * The below method loads the json file from server for dynamic content loading
     * The sample file can be found in assets folder with name movie_details.json
     * */
    private void initData() {
        try {
            Retrofit retrofit = Utility.getInstance().getClientBase(BASE_URL);

            IApiServiceClient serviceClient = retrofit.create(IApiServiceClient.class);

            Call<MovieModel> movieModelResponse = serviceClient.getMovieConfig("movie_list");

            movieModelResponse.enqueue(new Callback<MovieModel>() {
                @Override
                public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
                    if (response.body() == null) {
                        Log.d(getClass().getName(), "Loaded from asset");
                        String json = Utility.getInstance().loadJSONFromAsset("movie_details.json", context);

                        MovieModel movieModel = new Gson().fromJson(json, MovieModel.class);
                        MovieListAdapters movieListAdapters = new MovieListAdapters(movieModel, context);
                        activityBaseMovieBinding.movieRecycler.setLayoutManager(new LinearLayoutManager(context));
                        activityBaseMovieBinding.movieRecycler.setAdapter(movieListAdapters);

                        activityBaseMovieBinding.shimmerViewContainer.stopShimmerAnimation();
                        activityBaseMovieBinding.shimmerViewContainer.setVisibility(View.GONE);
                        activityBaseMovieBinding.movieRecycler.setVisibility(View.VISIBLE);
                    } else {
                        Log.d(getClass().getName(), "Loaded from Server");
                        MovieModel movieModel = response.body();
                        MovieListAdapters movieListAdapters = new MovieListAdapters(movieModel, context);
                        activityBaseMovieBinding.movieRecycler.setLayoutManager(new LinearLayoutManager(context));
                        activityBaseMovieBinding.movieRecycler.setAdapter(movieListAdapters);

                        activityBaseMovieBinding.shimmerViewContainer.stopShimmerAnimation();
                        activityBaseMovieBinding.shimmerViewContainer.setVisibility(View.GONE);
                        activityBaseMovieBinding.movieRecycler.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onFailure(Call<MovieModel> call, Throwable t) {
                    Log.d(getClass().getName(), t.getMessage());
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initListener() {
        activityBaseMovieBinding.actionBar.searchIcon.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.search_icon:
                Intent intent = new Intent(context, SearchActivity.class);
                startActivity(intent);
                break;
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }
}
