package com.example.retrofitpayapi.infrastructures.order.pay.toss;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RetrofitTossPayApi {

    @POST("api/v2/payments")
    @Headers("Content-Type: application/json")
    Call<TossPayApiResponse.ReadyPayResponse> readyPay(@Body TossPayApiCommand.ReadyPayReq request);


    @POST("api/v2/execute")
    @Headers("Content-Type: application/json")
    Call<TossPayApiResponse.ApprovePayResponse> approvePay(@Body TossPayApiCommand.ApprovePayReq request);
}
