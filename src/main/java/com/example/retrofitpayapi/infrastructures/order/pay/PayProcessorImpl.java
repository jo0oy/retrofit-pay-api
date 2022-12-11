package com.example.retrofitpayapi.infrastructures.order.pay;

import com.example.retrofitpayapi.domain.order.OrderCommand;
import com.example.retrofitpayapi.domain.order.pay.PayInfo;
import com.example.retrofitpayapi.domain.order.pay.PayMethod;
import com.example.retrofitpayapi.domain.order.pay.PayProcessor;
import com.example.retrofitpayapi.domain.order.pay.validator.PayValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.security.InvalidParameterException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class PayProcessorImpl implements PayProcessor {

    private final List<PayValidator> payValidatorList;
    private final List<PayApiCaller> payApiCallerList;

    @Override
    public PayInfo.ReadyInfo pay(OrderCommand.PaymentReq command) {
        payValidatorList.forEach(paymentValidator
                -> paymentValidator.validate( command));

        var payApiCaller = getPayApiCaller(command.getPayMethod());
        var readyInfo = payApiCaller.pay(command);

        return readyInfo;
    }

    @Override
    public PayInfo.ApprovalInfo approvePay(String payToken, PayMethod payMethod) {
        var payApiCaller = getPayApiCaller(payMethod);
        payApiCaller.approvePayment(payToken);

        return null;
    }

    private PayApiCaller getPayApiCaller(PayMethod payMethod) {
        return payApiCallerList.stream()
                .filter(payApiCaller
                        -> payApiCaller.checkPayMethod(payMethod))
                .findFirst()
                .orElseThrow(InvalidParameterException::new);
    }
}
