package com.example.retrofitpayapi.application;

import com.example.retrofitpayapi.domain.order.OrderCommand;
import com.example.retrofitpayapi.domain.order.OrderService;
import com.example.retrofitpayapi.domain.order.pay.PayInfo;
import com.example.retrofitpayapi.domain.order.pay.PayMethod;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class PayFacade {

    private final OrderService orderService;

    public PayInfo.ReadyInfo pay(OrderCommand.PaymentReq command) {

        return orderService.payOrder(command);
    }

    public PayInfo.ApprovalInfo approvePay(String payToken, PayMethod payMethod) {

        orderService.approvePayment(payToken, payMethod);

        return null;
    }
}
