package com.example.retrofitpayapi.infrastructures.order.pay;

import com.example.retrofitpayapi.domain.order.OrderCommand;
import com.example.retrofitpayapi.domain.order.pay.PayInfo;
import com.example.retrofitpayapi.domain.order.pay.PayMethod;

public interface PayApiCaller {

    boolean checkPayMethod(PayMethod payMethod);

    PayInfo.ReadyInfo pay(OrderCommand.PaymentReq command);

    PayInfo.ApprovalInfo approvePayment(String payToken);
}
