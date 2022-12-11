package com.example.retrofitpayapi.infrastructures.order.pay.naver;

import com.example.retrofitpayapi.domain.order.OrderCommand;
import com.example.retrofitpayapi.domain.order.pay.PayInfo;
import com.example.retrofitpayapi.domain.order.pay.PayMethod;
import com.example.retrofitpayapi.infrastructures.order.pay.PayApiCaller;
import com.example.retrofitpayapi.common.exception.FailPayException;
import com.example.retrofitpayapi.common.util.retrofit.RetrofitUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RequiredArgsConstructor
@Component
public class NaverPayApiCaller implements PayApiCaller {

    private static final String APPROVAL_URL = "http://localhost:8080/api/v1/orders/pay/naver-pay/approval";
    private static final String CANCEL_URL = "http://localhost:8080/api/v1/orders/pay/cancel";
    private static final String FAIL_URL = "http://localhost:8080/api/v1/orders/pay/fail";

    private final RetrofitNaverPayApi retrofitNaverPayApi;
    private final RetrofitUtils retrofitUtils;

    @Override
    public boolean checkPayMethod(PayMethod payMethod) {
        return payMethod == PayMethod.NAVER_PAY;
    }

    @Override
    public PayInfo.ReadyInfo pay(OrderCommand.PaymentReq command) {

        var request = NaverPayApiCommand.ReserveReq.builder()
                .modelVersion("2")
                .merchantPayKey(command.getOrderToken())
                .productCount(command.getQuantity())
                .productName(command.getItemName())
                .returnUrl(APPROVAL_URL)
                .totalPayAmount(command.getTotalAmount())
                .taxScopeAmount(command.getTotalAmount() - command.getTaxFreeAmount())
                .taxExScopeAmount(command.getTaxFreeAmount())
                .build();

        var call = retrofitNaverPayApi.reservePay(request);

        var response
                = retrofitUtils.responseSync(call)
                .orElseThrow(() -> new FailPayException("네이버페이 결제 요청에 실패했습니다."));

        if (!response.getCode().equals("Success")) {
            log.error("네이버페이 결제 요청 실패 errorCode={}, errorMessage={}", response.getCode(), response.getMessage());
            throw new FailPayException("네이버페이 결제 요청 실패 error=" + response.getCode());
        }

        var nextRedirectPcUrl = "https://test-pay.naver.com/payments/" + response.getBody().getReserveId();
        var nextRedirectMobileWebUrl = "https://test-m.pay.naver.com/payments/" + response.getBody().getReserveId();

        return PayInfo.ReadyInfo.builder()
                .nextRedirectPcUrl(nextRedirectPcUrl)
                .nextRedirectMobileWebUrl(nextRedirectMobileWebUrl)
                .build();
    }

    @Override
    public PayInfo.ApprovalInfo approvePayment(String payToken) {

        if (!StringUtils.hasText(payToken)) {
            log.error("네이버페이 결제 승인 실패. payToken invalid");
            throw new FailPayException("네이버페이 결제 승인 실패. payToken invalid");
        }

        var call = retrofitNaverPayApi.approvePay(payToken);

        var response
                = retrofitUtils.responseSync(call)
                .orElseThrow(() -> new FailPayException("네이버페이 결제 승인에 실패했습니다."));

        if (!response.getCode().equals("Success")) {
            log.error("네이버페이 결제 승인 실패 errorCode={}, errorMessage={}", response.getCode(), response.getMessage());
            throw new FailPayException("네이버페이 결제 요청 실패 error=" + response.getCode());
        }

        return PayInfo.ApprovalInfo.builder()
                .payToken(response.getBody().getPaymentId())
                .payMethod(PayMethod.NAVER_PAY)
                .orderToken(response.getBody().getDetail().getOrderToken())
                .payTotalAmount(response.getBody().getDetail().getTotalPayAmount())
                .build();
    }

    private HttpServletRequest getCurrentRequest() {
        var sra =  (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return sra.getRequest();
    }
}
