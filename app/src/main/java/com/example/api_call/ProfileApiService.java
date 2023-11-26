package com.example.api_call;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface ProfileApiService {

    @POST("Account/UpdatePassword")
    Call<ChangePasswordResponse> ChangePasswordResponse(@Body HashMap<String, String> hashMap);

    @POST("ProfileServices/UserProfile")
    Call<GetUserProfileDetails> getUserProfileDetails(@Body HashMap<String, String> hashMap);

    @POST
    Call<OtpSentResponse> getForgotTpin(@Url String str);

    @POST("Account/UpdatePassword")
    Call<ChangePasswordResponse> ChangeTpinResponse(@Body HashMap<String, String> hashMap);

}
