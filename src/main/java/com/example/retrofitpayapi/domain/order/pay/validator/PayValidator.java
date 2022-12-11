package com.example.retrofitpayapi.domain.order.pay.validator;

import com.example.retrofitpayapi.domain.order.OrderCommand;

public interface PayValidator {
    void validate(OrderCommand.PaymentReq request);
}
