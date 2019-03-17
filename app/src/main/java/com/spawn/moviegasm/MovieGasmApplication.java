package com.spawn.moviegasm;

import android.app.Application;

public class MovieGasmApplication extends Application {

    public static MovieGasmApplication movieGasmApplication;

    public static synchronized MovieGasmApplication getInstance() {

        if(movieGasmApplication == null){
            movieGasmApplication = new MovieGasmApplication();
        }
        return movieGasmApplication;
    }
}
