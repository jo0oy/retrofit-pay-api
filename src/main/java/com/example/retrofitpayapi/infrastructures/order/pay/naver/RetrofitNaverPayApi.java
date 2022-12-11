package com.example.retrofitpayapi.infrastructures.order.pay.naver;

import retrofit2.Call;
import retrofit2.http.*;

public interface RetrofitNaverPayApi {

    @POST("naverpay/payments/v2/reserve")
    @Headers({
            "Content-Type: application/json"
    })
    Call<NaverPayApiResponse.ReserveReqResponse> reservePay(@Body NaverPayApiCommand.ReserveReq command);


    @FormUrlEncoded
    @POST("naverpay/payments/v2/apply/payment")
    Call<NaverPayApiResponse.ApproveReqResponse> approvePay(@Field("paymentId") String paymentId);
}
