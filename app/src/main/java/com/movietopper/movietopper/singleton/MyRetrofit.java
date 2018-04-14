package com.movietopper.movietopper.singleton;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Enam on 3/30/2018.
 */

public class MyRetrofit {
    private static Retrofit retrofitInstance = null;
    private static String BASE_URL = "http://download.movietopper.net/";

    private MyRetrofit() {

    }

    public static Retrofit getRetrofitInstance() {
        if (retrofitInstance == null) {
            Retrofit.Builder retrofitBuilder = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(new Gson()));
            retrofitInstance = retrofitBuilder.build();
        }
        return retrofitInstance;
    }


}
