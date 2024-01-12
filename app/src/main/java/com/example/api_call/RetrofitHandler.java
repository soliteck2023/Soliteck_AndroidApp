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
//                .baseUrl("http://royalblueapi.soliteck.com/api/")
                .baseUrl("http://testapi.soliteck.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();



        return retrofit;

    }

    private static Retrofit getRetrofit2(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient
                .Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();

        Retrofit retrofit2 = new Retrofit.Builder()
//                .baseUrl("http://royalblueapi.soliteck.com/")
//                .baseUrl("http://192.168.1.3:13196/")
                .baseUrl("http://192.168.1.23:13196/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();



        return retrofit2;

    }


    public static ApiInterface getService(){
        ApiInterface userService = getRetrofit().create(ApiInterface.class);
        return userService;
    }

    public static ApiInterface getService2(){
        ApiInterface userService2 = getRetrofit2().create(ApiInterface.class);
        return userService2;
    }


//    public static ApiInterface getnewclient(){
//        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://api.soliteck.com/api/").addConverterFactory(GsonConverterFactory.create()).build();
//        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
//        return apiInterface;
//
//    }
}
