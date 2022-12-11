package com.example.retrofitpayapi.infrastructures.order.pay.kakao;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

public class KaKaoPayApiResponse {

    @Getter
    @Builder
    @ToString
    public static class ReadyResponse {
        @SerializedName("tid")
        private String tid;

        @SerializedName("tid")
        private String nextRedirectPcUrl;

        @SerializedName("tid")
        private String nextRedirectMobileUrl;

        @SerializedName("tid")
        private String nextRedirectAppUrl;
    }

    @Getter
    @Builder
    @ToString
    public static class Amount {
        // 전체 결제 금액
        private Integer total;
        // 비과세 금액
        private Integer taxFree;
        // 부가세 금액
        private Integer vat;
        // 사용할 포인트 금액
        private Integer point;
        // 할인 금액
        private Integer discount;
    }

    @Getter
    @Builder
    @ToString
    public static class CardInfo {
        // 매입 카드사 한글명
        private String purchaseCorp;
        // 매입 카드사 코드
        private String purchaseCorpCode;
        // 카드 발급사 한글명
        private String issuerCorp;
        // 카드 발급사 코드
        private String issuerCorpCode;
        // 카카오페이 매입사명
        private String kakaopayPurchaseCorp;
        // 카카오페이 매입사 코드
        private String kakaopayPurchaseCorpCode;
        // 카카오페이 발급사명
        private String kakaopayIssuerCorp;
        // 카카오페이 발급사 코드
        private String kakaopayIssuerCorpCode;
        // 카드 BIN
        private String bin;
        // 카드 타입
        private String cardType;
        // 할부 개월 수
        private String installMonth;
        // 카드사 승인번호
        private String approvedId;
        // 카드사 가맹점 번호
        private String cardMid;
        // 무이자할부 여부(Y/N)
        private String interestFreeInstall;
        // 카드 상품 코드
        private String cardItemCode;
    }

    @Getter
    @Builder
    @ToString
    public static class ApprovalResponse {
        private String aid;
        private String tid;
        private String cid;
        private String sid;
        private String partnerOrderId;
        private String partnerUserId;
        private String paymentMethodType;
        private String itemName;
        private String itemCode;
        private Integer quantity;
        private LocalDateTime createdAt;
        private LocalDateTime approvedAt;
        private String payload;
        private KaKaoPayApiResponse.Amount amount;
        private KaKaoPayApiResponse.CardInfo cardInfo;
    }
}
