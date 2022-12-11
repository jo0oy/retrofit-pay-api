package com.example.retrofitpayapi.domain.order.pay;

import com.example.retrofitpayapi.domain.order.OrderCommand;

public interface PayProcessor {
    PayInfo.ReadyInfo pay(OrderCommand.PaymentReq request);

    PayInfo.ApprovalInfo approvePay(String token, PayMethod payMethod);
}
