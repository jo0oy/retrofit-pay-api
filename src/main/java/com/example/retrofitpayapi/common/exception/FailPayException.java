package com.example.retrofitpayapi.common.exception;

public class FailPayException extends RuntimeException {

    public FailPayException() {
        super();
    }

    public FailPayException(String message) {
        super(message);
    }
}
