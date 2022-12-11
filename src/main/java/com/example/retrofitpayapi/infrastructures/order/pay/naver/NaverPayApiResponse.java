package com.example.retrofitpayapi.infrastructures.order.pay.naver;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class NaverPayApiResponse {

    @ToString
    @Builder
    @Getter
    public static class ReserveReqResponse {
        @SerializedName("code")
        private String code;

        @SerializedName("message")
        private String message;

        @SerializedName("body")
        private ReserveReqResponseBody body;
    }

    @ToString
    @Builder
    @Getter
    public static class ReserveReqResponseBody {

        @SerializedName("reserveId")
        private String reserveId;
    }

    @ToString
    @Builder
    @Getter
    public static class ApproveReqResponse {
        @SerializedName("code")
        private String code;

        @SerializedName("message")
        private String message;

        @SerializedName("body")
        private ApproveReqResponseBody body;
    }

    @ToString
    @Builder
    @Getter
    public static class ApproveReqResponseBody {

        @SerializedName("paymentId")
        private String paymentId;

        @SerializedName("detail")
        private Detail detail;
    }

    @ToString
    @Builder
    @Getter
    public static class Detail {

        @SerializedName("totalPayAmount")
        private Integer totalPayAmount;

        @SerializedName("primaryPayMeans")
        private String primaryPayMeans;

        @SerializedName("merchantPayKey")
        private String orderToken;
    }
}
