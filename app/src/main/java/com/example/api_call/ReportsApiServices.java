package com.example.api_call;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ReportsApiServices {
    @Headers("Content-Type:application/json")
    @POST("ApiSupportServices/UpdateChildPaymentRequest")
    Call<UpdatePaymentRequestResponse> UpdateChildPaymentRequest(@Body HashMap<String, String> hashMap);

}
