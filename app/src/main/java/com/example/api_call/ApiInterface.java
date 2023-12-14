package com.example.api_call;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
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


    @Headers("Content-Type:application/json")
    @POST("Recharge/GetMobileOperatorName")
    Call<FetchOperator> getMobileOPT(@Body HashMap<String, String> hashMap);

    @FormUrlEncoded
    @POST("Recharge/GetMobileSpecialPlan")
    Call<MobilePlansResponse> getMobileSpecialPlan(@Field("UserName") String str, @Field("Password") String str2, @Field("OperatorId") String str3, @Field("Number") String str4);

    @POST("Recharge/GetOperator")
    Call<OperatorResponse> getAllOperators();

    @POST("Payment/GetBankList")
    Call<BankListbaseResponse> GetBankList(@Body HashMap<String, String> hashMap);

    @FormUrlEncoded
    @POST("Recharge/GetDTHOpt")
    Call<OperatorWithCircleResponse> getDTHOPT(@Field("UserName") String str, @Field("Password") String str2, @Field("Number") String str3);

    @POST("Recharge/Recharge")
    Call<RechargeConfirmResponse> getRechargeResponse(@Body HashMap<String, String> hashMap);


    @POST("DMT/ValidateRemitter")
    Call<ValidateRemitter> getValidate(@Body HashMap<String, String> hashMap);

    @POST("Transaction/TransactionReport")
    Call<TransactionReportBase> GetTransactionReport(@Body HashMap<String, String> hashMap);

    @POST("Transaction/LedgerReport")
    Call<TransactionReportBase> GetLedgerReport(@Body HashMap<String, String> hashMap);

    @POST("Transaction/MyEarning")
    Call<MyEarningReportBase> GetMyEarning(@Body HashMap<String, String> hashMap);

    @POST("Payment/GetNetworkPaymentReceivedReport")
    Call<PaymentReceivedResponse> GetPaymentReceived(@Body HashMap<String, String> hashMap);

    @POST("Transaction/LatestTransationReport")
    Call<GetLatestReportBase> getLatestReport(@Body HashMap<String, String> hashMap);

    @POST("reports/StatementReport")
    Call<GetStatementReport> getStatementReport(@Body HashMap<String, String> hashMap);

    @POST("Payment/PaymentRequest")
    Call<RequestPaymentResponse> GetPaymentRequest(@Body HashMap<String, String> hashMap);


    @FormUrlEncoded
    @POST("Recharge/GetDthCustInfo")
    Call<DthCustInfo> getInfoDetails(@Field("UserName") String str, @Field("Password") String str2, @Field("OperatorName") String str3, @Field("Number") String str4);

    @FormUrlEncoded
    @POST("Recharge/GetDthCustInfo")
    Call<DTHInfoResponse> getDthCustInfo(@Field("UserName") String str, @Field("Password") String str2, @Field("OperatorName") String str3, @Field("Number") String str4);

    @POST("DMT/OTP")
    Call<OTPResponse> getOtp(@Body HashMap<String, String> hashMap);

    @POST("DMT/AccountVerification")
    Call<MAccVerify> getVerify(@Body HashMap<String, String> hashMap);

    @POST("Beneficiary/RemoveBeneficiary")
    Call<MRemoveBene> removeBene(@Body HashMap<String, String> hashMap);

    @POST("Beneficiary/ConfirmRemoveBeneficiary")
    Call<MConfirmRemoveBene> removeConfirmBene(@Body HashMap<String, String> hashMap);

    @POST("DMT/FundTransfer")
    Call<List<MTransferFund>> makeTransfer(@Body HashMap<String, String> hashMap);

    @POST("DMT/GetBankList")
    Call<List<MBankListResponse>> getBanks(@Body HashMap<String, String> hashMap);

    @POST("Beneficiary/AddBeneficiary")
    Call<MBeneficiary> addBeneficiary(@Body HashMap<String, String> hashMap);


    @FormUrlEncoded
    @POST("Recharge/GetMobilePlan")
    Call<MobilePlanResponse> getMobilePlan(@Field("UserName") String str, @Field("Password") String str2, @Field("OperatorName") String str3, @Field("Circle") String str4);
}
