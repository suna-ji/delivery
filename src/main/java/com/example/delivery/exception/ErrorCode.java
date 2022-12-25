package com.example.delivery.exception;

public enum ErrorCode {
    // Common
    REQUIRED_FIELD_EMPTY(1000),

    // User
    PASSWORD_PATTERN_NOT_MATCH(2000),
    LOGIN_FAIL(2001);



    // Delivery



    private final Integer errorCode;

    private ErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public Integer getErrorCode() {
        return errorCode;
    }
}
