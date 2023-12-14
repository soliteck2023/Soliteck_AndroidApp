package com.example.api_call;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHandler2 {

    private static Retrofit getretofit2(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


        OkHttpClient okHttpClient = new OkHttpClient
                .Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();

        Retrofit retrofitnew = new Retrofit.Builder()
                .baseUrl("https://apihub.moneyart.in/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        return retrofitnew;
    }
    public static ServiceCallApi getnewService(){
        ServiceCallApi userService = getretofit2().create(ServiceCallApi.class);
        return userService;
    }
}
