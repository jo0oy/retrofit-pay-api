package com.example.retrofitpayapi.interfaces.dto;

import com.example.retrofitpayapi.domain.order.pay.PayMethod;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class OrderDto {
    @Getter
    @Builder
    @ToString
    public static class PaymentReq {
        private final String orderToken;
        private final PayMethod payMethod;
        private final String itemName;
        private final String itemCode;
        private final Integer quantity;
        private final Integer totalAmount;
        private final Integer taxFreeAmount;
    }
}
