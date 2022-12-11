package com.example.retrofitpayapi.infrastructures.order.pay.kakao;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RetrofitKakaoPayApi {


    @FormUrlEncoded
    @POST("v1/payment/ready")
    Call<KaKaoPayApiResponse.ReadyResponse> readyPay(@Field("cid") String cid,
                                                     @Field("partner_order_id") String partnerOrderId,
                                                     @Field("partner_user_id") String partnerUserId,
                                                     @Field("item_name") String itemName,
                                                     @Field("item_code") String itemCode,
                                                     @Field("quantity") Integer quantity,
                                                     @Field("total_amount") Integer totalAmount,
                                                     @Field("tax_free_amount") Integer taxFreeAmount,
                                                     @Field("approval_url") String approvalUrl,
                                                     @Field("cancel_url") String cancelUrl,
                                                     @Field("fail_url") String failUrl);



    @FormUrlEncoded
    @POST("v1/payment/approve")
    Call<KaKaoPayApiResponse.ApprovalResponse> approvalPay(@Field("cid") String cid,
                                                           @Field("tid") String tid,
                                                           @Field("partner_order_id") String partnerOrderId,
                                                           @Field("partner_user_id") String partnerUserId,
                                                           @Field("pg_token") String pgToken);
}
