package com.example.api_call;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHandler {

    private static Retrofit getRetrofit(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient
                .Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.soliteck.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();



        return retrofit;

    }


    public static ApiInterface getService(){
        ApiInterface userService = getRetrofit().create(ApiInterface.class);
        return userService;
    }


//    public static ApiInterface getnewclient(){
//        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://api.soliteck.com/api/").addConverterFactory(GsonConverterFactory.create()).build();
//        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
//        return apiInterface;
//
//    }
}
