package com.example.retrofitpayapi.interfaces.dto;

import com.example.retrofitpayapi.domain.order.pay.PayMethod;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class PayDto {

    @Getter
    @Builder
    @ToString
    public static class ReadyInfoResponse {
        private String nextRedirectPcUrl;
        private String nextRedirectMobileUrl;
    }

    @Getter
    @Builder
    @ToString
    public static class ReadyResponse {
        private String nextRedirectPcUrl;
        private String nextRedirectMobileWebUrl;
        private String nextRedirectAppUrl;
    }

    @Getter
    @Builder
    @ToString
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ApprovalResponse {
        private String payToken;
        private String orderToken;
        private Integer payTotalAmount;
        private PayMethod payMethod;
        private String cardCorpName;
        private String installMonth;
    }

}
