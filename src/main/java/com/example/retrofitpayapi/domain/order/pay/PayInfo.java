package com.example.retrofitpayapi.domain.order.pay;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class PayInfo {

    @Getter
    @Builder
    @ToString
    public static class ReadyInfo {
        private String nextRedirectPcUrl;
        private String nextRedirectMobileWebUrl;
        private String nextRedirectAppUrl;
    }

    @Getter
    @Builder
    @ToString
    public static class ApprovalInfo {
        private String payToken;
        private String orderToken;
        private Integer payTotalAmount;
        private PayMethod payMethod;
        private String cardCorpName;
        private String installMonth;
    }
}
