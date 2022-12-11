package com.example.retrofitpayapi.infrastructures.order.pay.toss;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class TossPayApiResponse {

    @ToString
    @Builder
    @Getter
    public static class ReadyPayResponse {

        @SerializedName("code")
        private Integer code;

        @SerializedName("checkoutPage")
        private String checkoutPage;

        @SerializedName("payToken")
        private String payToken;

        @SerializedName("msg")
        private String msg;

        @SerializedName("errorCode")
        private String errorCode;
    }

    @ToString
    @Builder
    @Getter
    public static class ApprovePayResponse {

        @SerializedName("code")
        private Integer code;

        @SerializedName("orderNo")
        private String orderToken;

        @SerializedName("payToken")
        private String payToken;

        @SerializedName("paidAmount")
        private Integer paidAmount;

        @SerializedName("cardCompanyName")
        private String cardCompanyName;

        @SerializedName("spreadOut")
        private String installMonth; // 할부 개월수

        @SerializedName("msg")
        private String msg;

        @SerializedName("errorCode")
        private String errorCode;
    }
}
