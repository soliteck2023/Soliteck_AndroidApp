package com.example.api_call;

import com.ContactUsResponse;

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

    @POST("UserService/MyCommission")
    Call<Commision> getCommissionMargin(@Body HashMap<String, String> hashMap);

    @Headers("Content-Type:application/json")
    @POST("Balance/Balance")
    Call<GetBalance> getUserBalance(@Body HashMap<String, String> hashMap);

    @Headers("Content-Type:application/json")
    @POST("Payment/GetPaymentMode")
    Call<PaymentModeResponse> GetPaymentMode(@Body HashMap<String, String> hashMap);


    @Headers("Content-Type:application/json")
    @POST("Payment/GetPaymentRequestHistory")
    Call<PaymentRequestHistoryResponse> GetPaymentRequestHistory(@Body HashMap<String, String> hashMap);


    @POST("Recharge/GetMobileOperatorName")
    Call<FetchOperator> getMobileOPT(@Body HashMap<String, String> hashMap);


    @POST("ApiSupportServices/RaiseComplain")
    Call<RaiseComplainResponse> RaiseComplain(@Body HashMap<String, String> hashMap);

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

    @POST("SDGenerateAuthToken")
    Call<TokenResponse> getSDGenerateAuthToken(@Body HashMap<String, String> hashMap);


    @POST("DMT/ValidateRemitter")
    Call<ValidateRemitter> getValidate(@Body HashMap<String, String> hashMap);

    @POST("Transaction/TransactionReport")
    Call<TransactionReportBase> GetTransactionReport(@Body HashMap<String, String> hashMap);


    @Headers("Content-Type:application/json")
    @POST("api/DailyNews/OTPONSMS")
        //Transaction/LedgerReport
    Call<OtpSentResponse> getsendOtp(@Body HashMap<String, String> hashMap);

    @POST("Transaction/LedgerReport")
        //Transaction/LedgerReport
    Call<TransactionReportBase> GetLedgerReport(@Body HashMap<String, String> hashMap);


    @POST("Transaction/MyEarning")
    Call<MyEarningReportBase> GetMyEarning(@Body HashMap<String, String> hashMap);

    @POST("MOBGetOperatorList")
    Call<PaymentModeResponse> GetopratorEarning(@Body HashMap<String, String> hashMap);

    @POST("MOBNetBalReceived")
    Call<PaymentReceivedResponse> GetPaymentReceived(@Body HashMap<String, String> hashMap);


    @POST("MOBPendingTxnReport")
    Call<PaymentReceivedResponse> Getpendingreport(@Body HashMap<String, String> hashMap);

    @POST("MOBDailyNews")
    Call<NewsResponse> getnews(@Body HashMap<String, String> hashMap);


    @POST("MOBLedgerReport")
        //Transaction/LedgerReport
    Call<LedgerReportBase> GetLedgerReportnew(@Body HashMap<String, String> hashMap);

    @POST("MOBAEPSWalletSettelement")
        //Transaction/LedgerReport
    Call<SettlementBase> AEPSWalletSettelement(@Body HashMap<String, String> hashMap);

    @POST("MOBViewTransaction")
        //Transaction/LedgerReport
    Call<viewPaymentResponse> GetReceiptReport(@Body HashMap<String, String> hashMap);
    @POST("MOBAEPSTxnReport")
        //Transaction/LedgerReport
    Call<viewAEPSResponse> GetReceiptReport2(@Body HashMap<String, String> hashMap);


    @POST("MOBComissionReport")
    Call<Commision> getCommissionMargin2(@Body HashMap<String, String> hashMap);

    @POST("MOBComplaintReport")
    Call<Compaint> getcompaintMargin2(@Body HashMap<String, String> hashMap);


    @POST("MOBAEPSTxnReport")
    Call<cashoutbaseResponse> Getcashout_txnReceived(@Body HashMap<String, String> hashMap);

    @POST("MOBAEPSLedgerReport")
    Call<ledgercashoutbaseResponse> Getcashout_2ndtxnReceived(@Body HashMap<String, String> hashMap);

    @POST("MOBBankServiceChargelist")
    Call<newBankListbaseResponse> GetnewBankList(@Body HashMap<String, String> hashMap);

    @POST("MOBForgotPassword")
    Call<OtpSentResponse> newforgotPassword(@Body HashMap<String, String> hashMap);

    @POST("Transaction/LatestTransationReport")
    Call<GetLatestReportBase> getLatestReport(@Body HashMap<String, String> hashMap);

    @POST("reports/StatementReport")
    Call<GetStatementReport> getStatementReport(@Body HashMap<String, String> hashMap);

    @POST("Payment/PaymentRequest")
    Call<RequestPaymentResponse> GetPaymentRequest(@Body HashMap<String, String> hashMap);

    @POST("MOBUploadFiles")
    Call<RequestPaymentResponse> getuplodeimage(@Body HashMap<String, String> hashMap);
//                                                   @Field ("DeviceId")String deviceid,
//                                                 @Field ("Token")String token,
//                                                 @Field ("UniqueCode")String uniqueCode,
//                                                @Field("Base64Encode") String image,
//                                                @Field ("Flag") String imgFlag
//                                                );

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

    @POST("Account/UpdatePassword")
    Call<ChangePasswordResponse> ChangePasswordResponse(@Body HashMap<String, String> hashMap);

    @POST("Account/UpdatePassword")
    Call<ChangePasswordResponse> ChangeTpinResponse(@Body HashMap<String, String> hashMap);

    @POST("MOBAggrementCertificate")
    Call<AggrementCertificateclass> AggrementCertificatemethod(@Body HashMap<String, String> hashMap);
    @POST("DMT/RemitterRegistration")
    Call<RemitterRegResponse> getRemitterRegistered(@Body HashMap<String, String> hashMap);


    @POST("MOBRaiseComplaint")
    Call<ComplaintResponse > Getcomplaintraise(@Body HashMap<String, String> hashMap);

    @POST("ContactUs/ContactUs")
    Call<ContactUsResponse> getEnquiry(@Body HashMap<String, String> hashMap);

    //send otp to mobile number Api
    @POST("MOBOtpVerification")
    Call<BalTransferResponse> GetMOBOtp(@Body HashMap<String, String> hashMap);

    //verify mobile number OTP API
    @POST("MOBVerifyOtp")
    Call<OtpverifyResponse> otpSendmethod(@Body HashMap<String, String> hashMap);


    @POST("MOBComplaintTypeList")
    Call<compaint_typeList> compaint_typeList(@Body HashMap<String, String> hashMap);

}
