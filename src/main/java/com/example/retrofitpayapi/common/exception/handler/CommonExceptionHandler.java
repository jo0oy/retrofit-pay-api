package com.example.retrofitpayapi.common.exception.handler;

import com.example.retrofitpayapi.common.exception.FailPayException;
import com.example.retrofitpayapi.common.response.ResultResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(FailPayException.class)
    public ResponseEntity<?> exception(FailPayException exception) {
        log.error("{} , error-message={}", exception.toString(), exception.getMessage());

        return ResponseEntity.badRequest()
                .body(ResultResponse.fail(exception.getMessage(), null));
    }
}
