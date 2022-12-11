package com.example.retrofitpayapi.infrastructures.order.pay.kakao;

import com.example.retrofitpayapi.domain.order.OrderCommand;
import com.example.retrofitpayapi.domain.order.pay.PayInfo;
import com.example.retrofitpayapi.domain.order.pay.PayMethod;
import com.example.retrofitpayapi.infrastructures.RetrofitApiResToInfoMapper;
import com.example.retrofitpayapi.infrastructures.order.pay.PayApiCaller;
import com.example.retrofitpayapi.common.exception.FailPayException;
import com.example.retrofitpayapi.common.util.retrofit.RetrofitUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.InvalidParameterException;

@Slf4j
@RequiredArgsConstructor
@Component
public class KakaoPayApiCaller implements PayApiCaller {

    private final RetrofitKakaoPayApi retrofitKakaoPayApi;
    private final RetrofitUtils retrofitUtils;
    private final RetrofitApiResToInfoMapper mapper;

    private static final String CID = "TC0ONETIME";
    private static final String APPROVAL_URL = "http://localhost:8080/api/v1/orders/pay/kakao-pay/approval";
    private static final String CANCEL_URL = "http://localhost:8080/api/v1/orders/pay/cancel";
    private static final String FAIL_URL = "http://localhost:8080/api/v1/orders/pay/fail";

    @Override
    public boolean checkPayMethod(PayMethod payMethod) {
        return payMethod == PayMethod.KAKAO_PAY;
    }

    @Override
    public PayInfo.ReadyInfo pay(OrderCommand.PaymentReq command) {
        var call
                = retrofitKakaoPayApi.readyPay(CID, command.getOrderToken(), "partner_user_id",
                command.getItemName(), command.getItemCode(), command.getQuantity(), command.getTotalAmount(),
                0, APPROVAL_URL, CANCEL_URL, FAIL_URL);

        var response
                = retrofitUtils.responseSync(call)
                .orElseThrow(() -> new FailPayException("카카오페이 결제 요청에 실패했습니다."));


        HttpSession session = getCurrentRequest().getSession(true);
        session.setAttribute("tid", response.getTid());
        session.setAttribute("orderToken", command.getOrderToken());

        return mapper.of(response);
    }

    @Override
    public PayInfo.ApprovalInfo approvePayment(String payToken) {
        HttpSession session = getCurrentRequest().getSession();
        var tid = (String) session.getAttribute("tid");
        var orderToken = (String) session.getAttribute("orderToken");

        if(tid == null) throw new InvalidParameterException("tid 값이 null 입니다.");
        if(orderToken == null) throw new InvalidParameterException("orderToken 값이 null 입니다.");

        var call
                = retrofitKakaoPayApi.approvalPay(CID, tid, orderToken, "partnerUserId", payToken);

        var response
                = retrofitUtils.responseSync(call).orElseThrow(() -> new FailPayException("카카오페이 결제 승인 실패"));

        return PayInfo.ApprovalInfo.builder()
                .payMethod(PayMethod.KAKAO_PAY)
                .payToken(payToken)
                .orderToken(orderToken)
                .payTotalAmount(response.getAmount().getTotal())
                .build();
    }

    private HttpServletRequest getCurrentRequest() {
        var sra =  (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return sra.getRequest();
    }
}
