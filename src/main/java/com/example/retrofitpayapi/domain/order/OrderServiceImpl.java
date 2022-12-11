package com.example.retrofitpayapi.domain.order;

import com.example.retrofitpayapi.domain.order.pay.PayInfo;
import com.example.retrofitpayapi.domain.order.pay.PayMethod;
import com.example.retrofitpayapi.domain.order.pay.PayProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final PayProcessor payProcessor;

    @Override
    public PayInfo.ReadyInfo payOrder(OrderCommand.PaymentReq command) {
        return payProcessor.pay(command);
    }

    @Override
    public String approvePayment(String payToken, PayMethod payMethod) {
        var approvalInfo = payProcessor.approvePay(payToken, payMethod);
        return null;
    }

}
