package com.example.retrofitpayapi.interfaces.api;

import com.example.retrofitpayapi.application.PayFacade;
import com.example.retrofitpayapi.domain.order.pay.PayMethod;
import com.example.retrofitpayapi.common.exception.FailPayException;
import com.example.retrofitpayapi.common.response.ResultResponse;
import com.example.retrofitpayapi.interfaces.dto.OrderDto;
import com.example.retrofitpayapi.interfaces.dto.OrderDtoMapper;
import com.example.retrofitpayapi.interfaces.dto.PayDtoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
public class OrderPayApiController {

    private final PayFacade payFacade;
    private final OrderDtoMapper orderDtoMapper;
    private final PayDtoMapper payDtoMapper;

    @PostMapping("/api/v1/orders/pay")
    public ResponseEntity<?> pay(@RequestBody OrderDto.PaymentReq reqDto) {
        var readyInfo = payFacade.pay(orderDtoMapper.of(reqDto));
        var data = payDtoMapper.toDto(readyInfo);
        return ResponseEntity
                .ok(ResultResponse.success("카카오 페이 결제 준비 성공", data));
    }

    @GetMapping("/api/v1/orders/pay/kakao-pay/approval")
    public ResponseEntity<?> kakaoPayApproval(@RequestParam("pg_token") String pgToken) {

        log.info("카카오 페이 결제 승인 요청 로직 진행...");

        var approvalInfo = payFacade.approvePay(pgToken, PayMethod.KAKAO_PAY);
        var data = payDtoMapper.toDto(approvalInfo);

        return ResponseEntity.ok(ResultResponse.success("카카오 페이 결제 승인 완료", data));
    }

    @GetMapping("/api/v1/orders/pay/naver-pay/approval")
    public ResponseEntity<?> naverPayApproval(@RequestParam(value = "paymentId", required = false) String paymentId,
                                              @RequestParam(value = "reserveId", required = false) String reserveId,
                                              @RequestParam("resultCode") String resultCode,
                                              @RequestParam(value = "resultMessage", required = false) String resultMessage) {

        log.info("카카오 페이 결제 승인 요청 로직 진행...");

        if (resultCode.equals("Fail")) {
            log.error("네이버페이 결제 요청 실패 reserveId ={}, message={}", reserveId, resultMessage);
            throw new FailPayException(resultMessage);
        }

        var approvalInfo = payFacade.approvePay(paymentId, PayMethod.NAVER_PAY);
        var data = payDtoMapper.toDto(approvalInfo);

        return ResponseEntity.ok(ResultResponse.success("네이버 페이 결제 승인 완료", data));
    }

    @GetMapping("/api/v1/orders/pay/toss-pay/approval")
    public ResponseEntity<?> tossPayApproval(@RequestParam("orderNo") String orderNo,
                                             @RequestParam("status") String status,
                                             @RequestParam("payMethod") String payMethod) {

        log.info("토스 페이 결제 승인 요청 로직 진행...");

        if (!(status.equals("PAY_APPROVED") || status.equals("PAY_COMPLETED"))) {
            throw new FailPayException("토스페이 결제 요청이 실패했습니다.");
        }

        var approvalInfo = payFacade.approvePay(null, PayMethod.TOSS_PAY);
        var data = payDtoMapper.toDto(approvalInfo);

        return ResponseEntity.ok(ResultResponse.success("토스 페이 결제 승인 완료", data));
    }

    @PostMapping("/api/v1/orders/pay/cancel")
    public ResponseEntity<?> payCancel() {

        log.info("결제 취소 로직 진행...");

        return ResponseEntity.ok(ResultResponse.success("결제 취소 완료"));
    }

    @GetMapping("/api/v1/orders/pay/fail")
    public ResponseEntity<?> payFail() {

        log.info("카카오페이 결제 실패 로직 진행...");

        return ResponseEntity
                .ok(ResultResponse.fail(ResultResponse.Errors.of("카카오페이 결제 실패", "EC-001")));
    }
}
