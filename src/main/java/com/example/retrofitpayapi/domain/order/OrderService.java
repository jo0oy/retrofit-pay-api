package com.example.retrofitpayapi.domain.order;

import com.example.retrofitpayapi.domain.order.pay.PayInfo;
import com.example.retrofitpayapi.domain.order.pay.PayMethod;

public interface OrderService {
    PayInfo.ReadyInfo payOrder(OrderCommand.PaymentReq command);

    String approvePayment(String payToken, PayMethod payMethod);
}
