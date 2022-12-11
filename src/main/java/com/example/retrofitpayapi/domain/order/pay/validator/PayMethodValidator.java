package com.example.retrofitpayapi.domain.order.pay.validator;

import com.example.retrofitpayapi.domain.order.OrderCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PayMethodValidator implements PayValidator {

    @Override
    public void validate(OrderCommand.PaymentReq request) {
        log.info("PayMethodValidator 로직 실행...");
        log.info("payMethod={}", request.getPayMethod());
    }
}
