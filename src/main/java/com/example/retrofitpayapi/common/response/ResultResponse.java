package com.example.retrofitpayapi.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.ZonedDateTime;

@Getter
@NoArgsConstructor
@ToString
public class ResultResponse<T> {

    private boolean success;

    private ZonedDateTime responseTime;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    private Errors<?> errors;

    @Builder
    public ResultResponse(final boolean success,
                          final ZonedDateTime responseTime,
                          final String message,
                          final T data,
                          final Errors<?> errors ) {
        this.success = success;
        this.responseTime = responseTime;
        this.message = message;
        this.data = data;
        this.errors = errors;
    }

    public static<T> ResultResponse<T> success(final T data) {
        return success(null, data);
    }

    public static<T> ResultResponse<T> success(final String message) {
        return success(message, null);
    }

    public static<T> ResultResponse<T> success(final String message, final T data) {
       return ResultResponse.<T>builder()
               .success(true)
               .responseTime(ZonedDateTime.now())
               .message(message)
               .data(data)
               .errors(null)
               .build();
    }

    public static <T> ResultResponse<T> fail(final Errors<?> errors) {
        return fail(null, errors);
    }

    public static<T> ResultResponse<T> fail(final String message, Errors<?> errors) {
        return ResultResponse.<T>builder()
                .success(false)
                .responseTime(ZonedDateTime.now())
                .message(message)
                .data(null)
                .errors(errors)
                .build();
    }



    @Getter
    @NoArgsConstructor
    public static class Errors<T> {
        private String errorMessage;
        private String errorCode;
        private T errorList;

        @Builder
        public Errors(final String errorMessage,
                      final String errorCode,
                      final T errorList) {
            this.errorMessage = errorMessage;
            this.errorCode = errorCode;
            this.errorList = errorList;
        }

        public static <T> Errors<T> of(String errorMessage, String errorCode) {
            return of(errorMessage, errorCode, null);
        }

        public static <T> Errors<T> of(String errorMessage, T errors) {
            return of(errorMessage, null, errors);
        }

        public static <T> Errors<T> of(String errorMessage, String errorCode, T errors) {
            return Errors.<T>builder()
                    .errorMessage(errorMessage)
                    .errorCode(errorCode)
                    .errorList(errors)
                    .build();
        }
    }
}

