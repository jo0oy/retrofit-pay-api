package com.example.retrofitpayapi.infrastructures.order.pay.naver;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class NaverPayApiCommand {

    @ToString
    @Builder
    @Getter
    public static class ReserveReq {
        private String modelVersion;
        private String merchantPayKey;
        private String productName;
        private Integer productCount;
        private Integer totalPayAmount;
        private Integer taxScopeAmount; // 과세 대상 금액. 과세 대상 금액 + 면세 대상 금액 = 총 결제 금액
        private Integer taxExScopeAmount; // 면세 대상 금액. 과세 대상 금액 + 면세 대상 금액 = 총 결제 금액
        private String returnUrl;
    }
}
