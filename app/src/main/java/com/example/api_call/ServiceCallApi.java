package com.example.api_call;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ServiceCallApi {
    @POST("RechargePlan/MobileRPlandata")
    Call<MobileRPlanResponse> getMobilePlans(@Body HashMap<String, String> hashMap);


    @POST("RechargePlan/GetDTHPlanData")
    Call<MobileRPlanResponse> getDTHPlans(@Body HashMap<String, String> hashMap);

    @FormUrlEncoded
    @POST("Recharge/GetDthPlan")
    Call<DthBrowsPlanss> dthplan(@Field("UserName") String str, @Field("Password") String str2, @Field("OperatorName") String str3);
}
