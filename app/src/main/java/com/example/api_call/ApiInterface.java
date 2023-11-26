package com.example.api_call;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface ApiInterface {
    @Headers("Content-Type:application/json")
    @POST("UserAuth/Login")
    Call<LoginResponse> getLoginInfo(@Body HashMap<String, String> hashMap);


    @Headers("Content-Type:application/json")
    @POST("StoreDeviceid/StoreDeviceId")
    Call<DeviceIdResponse> getStoreDeviceId(@Body HashMap<String, String> hashMap);

    @Headers("Content-Type:application/json")
    @POST()
    Call<OtpSentResponse> forgotPasswordMethod(@Url String str);

    @POST("Version/GetVersion")
    Call<SoftVersionResponse> AppVersionCheck(@Body HashMap<String, String> hashMap);


    @Headers("Content-Type:application/json")
    @POST("Balance/Balance")
    Call<GetBalance> getUserBalance(@Body HashMap<String, String> hashMap);

    @Headers("Content-Type:application/json")
    @POST("Payment/GetPaymentMode")
    Call<PaymentModeResponse> GetPaymentMode(@Body HashMap<String, String> hashMap);


    @Headers("Content-Type:application/json")
    @POST("Payment/GetPaymentRequestHistory")
    Call<PaymentRequestHistoryResponse> GetPaymentRequestHistory(@Body HashMap<String, String> hashMap);



}
