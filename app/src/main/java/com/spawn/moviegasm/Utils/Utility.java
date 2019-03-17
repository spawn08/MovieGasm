package com.spawn.moviegasm.Utils;

import android.content.Context;

import org.json.JSONObject;

import java.io.InputStream;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Utility {

    public static Utility instance;
    public Retrofit baseRetrofit, searchRetrofit;

    public static Utility getInstance() {

        if (instance == null) {
            instance = new Utility();
        }

        return instance;
    }

    public Retrofit getClientBase(String baseUrl) {

        if (baseRetrofit == null) {
            baseRetrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return baseRetrofit;

    }

    public Retrofit getClientSearch(String baseUrl) {

        if (searchRetrofit == null) {
            searchRetrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return searchRetrofit;

    }


    public String loadJSONFromAsset(String name, Context context) {
        String json = null;
        JSONObject obj = null;

        try {
            InputStream is = context.getAssets().open(name);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

}
