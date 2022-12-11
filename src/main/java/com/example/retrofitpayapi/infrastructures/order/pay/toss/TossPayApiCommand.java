package com.example.retrofitpayapi.infrastructures.order.pay.toss;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class TossPayApiCommand {

    @ToString
    @Builder
    @Getter
    public static class ReadyPayReq {

        @SerializedName("apiKey")
        private String apiKey;

        @SerializedName("orderNo")
        private String orderNo;

        @SerializedName("productDesc")
        private String productDesc;

        @SerializedName("retUrl")
        private String retUrl;

        @SerializedName("retCancelUrl")
        private String retCancelUrl;

        @SerializedName("autoExecute")
        private boolean autoExecute;

        @SerializedName("amount")
        private Integer amount;

        @SerializedName("amountTaxFree")
        private Integer amountTaxFree; // 비과세금액
    }

    @ToString
    @Builder
    @Getter
    public static class ApprovePayReq {

        @SerializedName("apiKey")
        private String apiKey;

        @SerializedName("payToken")
        private String payToken;
    }

}
