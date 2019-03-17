package com.spawn.moviegasm;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.spawn.moviegasm.Utils.DotProgressBar;

public class SplashScreen extends AppCompatActivity {

    private Context context;
    private DotProgressBar dotProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        context = this;
        dotProgressBar = (DotProgressBar) findViewById(R.id.dotted_progress);

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(context, BaseMovieActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2500);

    }
}
