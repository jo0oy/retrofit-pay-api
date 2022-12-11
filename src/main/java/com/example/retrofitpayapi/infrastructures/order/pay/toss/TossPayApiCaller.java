package com.example.retrofitpayapi.infrastructures.order.pay.toss;

import com.example.retrofitpayapi.domain.order.OrderCommand;
import com.example.retrofitpayapi.domain.order.pay.PayInfo;
import com.example.retrofitpayapi.domain.order.pay.PayMethod;
import com.example.retrofitpayapi.infrastructures.order.pay.PayApiCaller;
import com.example.retrofitpayapi.common.util.retrofit.RetrofitUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class TossPayApiCaller implements PayApiCaller {

    private static final String APPROVAL_URL = "http://localhost:8080/api/v1/orders/pay/toss-pay/approval";
    private static final String CANCEL_URL = "http://localhost:8080/api/v1/orders/pay/cancel";

    private final RetrofitTossPayApi retrofitTossPayApi;
    private final RetrofitUtils retrofitUtils;

    @Override
    public boolean checkPayMethod(PayMethod payMethod) {
        return payMethod == PayMethod.TOSS_PAY;
    }

    @Override
    public PayInfo.ReadyInfo pay(OrderCommand.PaymentReq command) {

        return null;
    }

    @Override
    public PayInfo.ApprovalInfo approvePayment(String payToken) {
        return null;
    }
}
